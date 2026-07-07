import { useEffect, useRef, useState } from "react";
import { useSearchParams, Link } from "react-router-dom";
import { useCountries } from "../../hooks/useCountries";
import { api } from "../../api/axios";
import "./AttractionsPage.css";

interface Attraction {
  id: number;
  countryCode: string;
  name: string;
  description: string;
  imageUrl: string;
  latitude: number;
  longitude: number;
}

function getFlag(code: string) {
  return code.toUpperCase().replace(/./g, c =>
    String.fromCodePoint(c.charCodeAt(0) + 127397)
  );
}

export function AttractionsPage() {
  const [params]               = useSearchParams();
  const code                   = params.get("countryCode") ?? "";
  const { data: countries }    = useCountries();
  const country                = countries?.find(c => c.code === code);
  const [attractions, setAttractions] = useState<Attraction[]>([]);
  const [loading, setLoading]  = useState(false);
  const [error, setError]      = useState(false);
  const mapRef                 = useRef<HTMLDivElement>(null);
  const mapInstanceRef         = useRef<any>(null);

  useEffect(() => {
    if (!code) return;
    setLoading(true);
    setError(false);
    setAttractions([]);
    api.get(`/attractions?countryCode=${code}`)
      .then(r => setAttractions(r.data))
      .catch(() => setError(true))
      .finally(() => setLoading(false));
  }, [code]);

  useEffect(() => {
    if (!mapRef.current || attractions.length === 0) return;
    const L = (window as any).L;
    if (!L) return;

    // Destruir mapa anterior
    if (mapInstanceRef.current) {
      mapInstanceRef.current.remove();
      mapInstanceRef.current = null;
    }

    const valid = attractions.filter(a => a.latitude && a.longitude);
    if (valid.length === 0) return;

    const center = valid[0];
    const map = L.map(mapRef.current, { zoomControl: true }).setView(
      [center.latitude, center.longitude], 5
    );
    mapInstanceRef.current = map;

    L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
      attribution: "© OpenStreetMap",
      maxZoom: 18,
    }).addTo(map);

    const icon = L.divIcon({
      className: "",
      html: `<div style="
        width:10px;height:10px;background:#38BDF8;
        border:2px solid white;border-radius:50%;
        box-shadow:0 2px 6px rgba(0,0,0,0.3)
      "></div>`,
      iconSize: [10, 10],
      iconAnchor: [5, 5],
    });

    valid.forEach(a => {
      L.marker([a.latitude, a.longitude], { icon })
        .addTo(map)
        .bindPopup(`<strong style="font-family:sans-serif;font-size:13px">${a.name}</strong>`);
    });

    return () => {
      if (mapInstanceRef.current) {
        mapInstanceRef.current.remove();
        mapInstanceRef.current = null;
      }
    };
  }, [attractions]);

  if (!code) {
    return (
      <div className="attractions-page">
        <div className="attractions-container">
          <div className="attractions-select-wrapper">
            <div className="attractions-select-icon">🗺️</div>
            <p className="attractions-select-title">Escolha um destino</p>
            <p className="attractions-select-sub">
              Selecione um país para ver os pontos turísticos e o mapa interativo.
            </p>
            <Link to="/countries" className="attractions-select-link">
              Ver todos os países
            </Link>
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="attractions-page">
      <div className="attractions-container">

        <Link to="/countries" className="attractions-back">
          ← Países
        </Link>

        {country && (
          <div className="attractions-hero">
            <div className="attractions-hero__left">
              <span className="attractions-hero__flag">{getFlag(code)}</span>
              <div>
                <h1 className="attractions-hero__name">{country.name}</h1>
                <div className="attractions-hero__meta">
                  {country.capital && <span>{country.capital}</span>}
                  {country.capital && country.language && (
                    <span className="attractions-hero__meta-sep">·</span>
                  )}
                  {country.language && <span>{country.language}</span>}
                  {country.currencyCode && (
                    <>
                      <span className="attractions-hero__meta-sep">·</span>
                      <span>{country.currencyCode}</span>
                    </>
                  )}
                </div>
                <div className="attractions-hero__badges">
                  {country.bestSeason && (
                    <span className="attractions-hero__badge attractions-hero__badge--season">
                      {country.bestSeason}
                    </span>
                  )}
                  {country.timezone && (
                    <span className="attractions-hero__badge attractions-hero__badge--tz">
                      {country.timezone}
                    </span>
                  )}
                </div>
              </div>
            </div>
          </div>
        )}

        {loading && <p className="attractions-loading">Carregando atrações...</p>}
        {error   && <p className="attractions-error">Erro ao carregar atrações.</p>}

        {!loading && !error && attractions.length > 0 && (
          <>
            <div ref={mapRef} className="attractions-map" />

            <div className="attractions-section-header">
              <p className="attractions-section-title">Pontos turísticos</p>
              <span className="attractions-section-count">
                {attractions.length} {attractions.length === 1 ? "local" : "locais"}
              </span>
            </div>

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
