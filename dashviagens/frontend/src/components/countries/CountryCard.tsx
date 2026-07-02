import "./CountryCard.css";
import type { Country } from "../../types/Country";

interface CountryCardProps {
  country: Country;
}

export function CountryCard({ country }: CountryCardProps) {
  return (
    <div className="country-card">
      <h3>{country.name}</h3>

      <p>
        <strong>Capital:</strong> {country.capital}
      </p>

      <p>
        <strong>Moeda:</strong> {country.currencyCode}
      </p>

      <p>
        <strong>Idioma:</strong> {country.language}
      </p>

      <p>
        <strong>Melhor época:</strong> {country.bestSeason}
      </p>
    </div>
  );
}