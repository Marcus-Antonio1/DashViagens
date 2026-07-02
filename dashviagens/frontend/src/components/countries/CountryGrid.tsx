import "./CountryGrid.css";
import type { Country } from "../../types/Country";
import { CountryCard } from "./CountryCard";

interface CountryGridProps {
  countries: Country[];
}

export function CountryGrid({ countries }: CountryGridProps) {
  return (
    <div className="country-grid">
      {countries.map((country) => (
        <CountryCard
          key={country.code}
          country={country}
        />
      ))}
    </div>
  );
}