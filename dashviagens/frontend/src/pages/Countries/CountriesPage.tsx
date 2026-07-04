import { useState } from "react";
import { useCountries } from "../../hooks/useCountries";
import { CountryGrid } from "../../components/countries";
import "./CountriesPage.css";

export function CountriesPage() {
  const { data, isLoading, error } = useCountries();
  const [search, setSearch] = useState("");

  const filtered = data?.filter(c =>
    c.name.toLowerCase().includes(search.toLowerCase()) ||
    c.code?.toLowerCase().includes(search.toLowerCase())
  ) ?? [];

  return (
    <div className="countries-page">
      <div className="countries-container">
        <div className="countries-page__header">
          <h1 className="page-title">Explorar destinos</h1>
          <p className="page-sub">Câmbio, custo médio e melhores épocas para viajar</p>
        </div>

        <div className="countries-page__search">
          <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2">
            <circle cx="11" cy="11" r="8"/><path d="m21 21-4.35-4.35"/>
          </svg>
          <input
            type="text"
            placeholder="Buscar país ou código..."
            value={search}
            onChange={e => setSearch(e.target.value)}
          />
        </div>

        {isLoading && (
          <div className="countries-page__grid">
            {Array.from({ length: 6 }).map((_, i) => (
              <div key={i} className="country-card-skeleton" />
            ))}
          </div>
        )}

        {error && (
          <div className="countries-page__error">Erro ao carregar países.</div>
        )}

        {!isLoading && !error && (
          <>
            <p className="countries-page__count">
              {filtered.length} {filtered.length === 1 ? "país encontrado" : "países encontrados"}
            </p>
            <CountryGrid countries={filtered} />
          </>
        )}
      </div>
    </div>
  );
}
