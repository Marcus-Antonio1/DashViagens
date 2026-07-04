import { useEffect, useRef, useState } from "react";
import { useSearchParams, Link } from "react-router-dom";
import { useCountries } from "../../hooks/useCountries";
import { api } from "../../api/axios";
import "./AttractionsPage.css";

interface Attraction {
  id: number; countryCode: string; name: string;
  description: string; imageUrl: string;
  latitude: number; longitude: number;
}

export function AttractionsPage() {
  const [params]    = useSearchParams();
  const code        = params.get("countryCode") ?? "";
  const { data: countries } = useCountries();
  const country     = countries?.find(c => c.code === code);

  const [attractions, setAttractions] = useState<Attraction[]>([]);
  const [loading, setLoading]         = useState(false);
  const [error, setError]             = useState(false);
  const mapRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    if (!code) return;
    setLoading(true); setError(false);
    api.get(`/attractions?countryCode=${code}`)
      .then(r => setAttractions(r.data))
      .catch(() => setError(true))
      .finally(() => setLoading(false));
  }, [code]);

  // Leaflet — carregado dinamicamente para não quebrar o bundle
  useEffect(() => {
    if (!mapRef.current || attractions.length === 0) return;

    const L = (window as any).L;
    if (!L) return;

    const center = attractions[0];
    const map = L.map(mapRef.current).setView([center.latitude, center.longitude], 6);

    L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
      attribution: "© OpenStreetMap contributors"
    }).addTo(map);

    attractions.forEach(a => {
      if (a.latitude && a.longitude) {
        L.marker([a.latitude, a.longitude])
          .addTo(map)
          .bindPopup(`<strong>${a.name}</strong>`);
      }
    });

    return () => { map.remove(); };
  }, [attractions]);

  return (
    <div className="attractions-page">
      <div className="attractions-container">
        <Link to="/countries" className="attractions-back">← Países</Link>

        {country && (
          <div className="attractions-hero">
            <span className="attractions-hero__flag">
              {code.toUpperCase().replace(/./g, c =>
                String.fromCodePoint(c.charCodeAt(0) + 127397)
              )}
            </span>
            <div>
              <h1 className="attractions-hero__name">{country.name}</h1>
              <p className="attractions-hero__meta">
                {country.capital} · {country.language} · {country.currencyCode}
              </p>
            </div>
          </div>
        )}

        {!code && (
          <div className="attractions-empty">
            <p>Selecione um país na página de <Link to="/countries">Países</Link>.</p>
          </div>
        )}

        {loading && <p className="attractions-loading">Carregando atrações...</p>}
        {error   && <p className="attractions-error">Erro ao carregar atrações.</p>}

        {attractions.length > 0 && (
          <>
            {/* Mapa Leaflet — requer CDN no index.html */}
            <div ref={mapRef} className="attractions-map" id="leaflet-map" />

            <h2 className="attractions-section-title">Pontos turísticos</h2>
            <div className="attractions-list">
              {attractions.map((a, i) => (
                <div key={a.id} className="attraction-item">
                  <div className="attraction-item__num">{i + 1}</div>
                  <div>
                    <div className="attraction-item__name">{a.name}</div>
                    {a.description && (
                      <div className="attraction-item__desc">{a.description}</div>
                    )}
                  </div>
                </div>
              ))}
            </div>
          </>
        )}

        {!loading && !error && code && attractions.length === 0 && (
          <div className="attractions-empty">
            <p>Nenhuma atração cadastrada para este país ainda.</p>
          </div>
        )}
      </div>
    </div>
  );
}
