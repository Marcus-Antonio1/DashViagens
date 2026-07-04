import type { Country } from "../../types/Country";
import { CountryCard } from "./CountryCard";
import "./CountryGrid.css";

interface CountryGridProps { countries: Country[]; }

export function CountryGrid({ countries }: CountryGridProps) {
  if (countries.length === 0) {
    return (
      <div className="country-grid__empty">
        <p>Nenhum país encontrado.</p>
      </div>
    );
  }

  return (
    <div className="country-grid">
      {countries.map(c => <CountryCard key={c.id} country={c} />)}
    </div>
  );
}
