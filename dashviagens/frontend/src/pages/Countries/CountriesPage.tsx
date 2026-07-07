import { useState } from "react";
import { useCountries } from "../../hooks/useCountries";
import { CountryCard } from "../../components/countries/CountryCard";
import "./CountriesPage.css";

export function CountriesPage() {
  const { data, isLoading, error } = useCountries();
  const [search, setSearch] = useState("");

  const filtered = data?.filter(c =>
    c.name.toLowerCase().includes(search.toLowerCase()) ||
    c.code?.toLowerCase().includes(search.toLowerCase()) ||
    c.capital?.toLowerCase().includes(search.toLowerCase())
  ) ?? [];

  return (
    <div className="countries-page">
      <div className="countries-container">

        <div className="countries-header">
          <p className="countries-header__eyebrow">Destinos turísticos</p>
          <h1 className="countries-header__title">Explorar países</h1>
          <p className="countries-header__sub">
            Câmbio, custo médio e melhores épocas para viajar
          </p>
        </div>

        <div className="countries-search">
          <span className="countries-search__icon">
            <svg width="15" height="15" viewBox="0 0 24 24" fill="none"
              stroke="currentColor" strokeWidth="2">
              <circle cx="11" cy="11" r="8"/>
              <path d="m21 21-4.35-4.35"/>
            </svg>
          </span>
          <input
            type="text"
            placeholder="Buscar por país, código ou capital..."
            value={search}
            onChange={e => setSearch(e.target.value)}
          />
        </div>

        {!isLoading && !error && (
          <p className="countries-count">
            {filtered.length} {filtered.length === 1 ? "destino encontrado" : "destinos encontrados"}
          </p>
        )}

        {isLoading && (
          <div className="countries-grid">
            {Array.from({ length: 6 }).map((_, i) => (
              <div key={i} className="country-card-skeleton" />
            ))}
          </div>
        )}

        {error && (
          <div className="countries-error">
            Erro ao carregar os países. Verifique se o backend está rodando.
          </div>
        )}

        {!isLoading && !error && filtered.length === 0 && (
          <div className="countries-empty">
            <div className="countries-empty__icon">🔍</div>
            <p className="countries-empty__title">Nenhum país encontrado</p>
            <p className="countries-empty__sub">Tente buscar por outro termo</p>
          </div>
        )}

        {!isLoading && !error && filtered.length > 0 && (
          <div className="countries-grid">
            {filtered.map(c => <CountryCard key={c.id} country={c} />)}
          </div>
        )}

      </div>
    </div>
  );
}
