import "./CountryCard.css";
import type { Country } from "../../types/Country";
import { useNavigate } from "react-router-dom";

const FALLBACK_GRADIENTS: Record<string, string> = {
  JPN: "linear-gradient(145deg,#1a1a2e,#0f3460)",
  ITA: "linear-gradient(145deg,#1c2b4a,#0d3b6e)",
  FRA: "linear-gradient(145deg,#0d1b2a,#2d4a6b)",
  PRT: "linear-gradient(145deg,#1a2a1a,#0d2b0d)",
  ESP: "linear-gradient(145deg,#2a1a0d,#4a2a0d)",
  USA: "linear-gradient(145deg,#0d1b2a,#0d2b4a)",
  CAN: "linear-gradient(145deg,#1a0d0d,#2a1a1a)",
  ARG: "linear-gradient(145deg,#0d1a2a,#0d2a3a)",
  CHL: "linear-gradient(145deg,#0d1a2a,#1a2a0d)",
  GRC: "linear-gradient(145deg,#0d1f3a,#1a3a5c)",
  THA: "linear-gradient(145deg,#1a0d2a,#2a1a0d)",
  DEU: "linear-gradient(145deg,#1a1a1a,#2a2a2a)",
  GBR: "linear-gradient(145deg,#0d1a2a,#1a0d2a)",
  AUS: "linear-gradient(145deg,#1a0d0d,#2a1a0d)",
  MEX: "linear-gradient(145deg,#1a1a0d,#0d2a1a)",
  NLD: "linear-gradient(145deg,#0d1a2a,#1a2a0d)",
  CHE: "linear-gradient(145deg,#1a1a2a,#0d1a2a)",
};

function getFlag(code: string) {
  return code.toUpperCase().replace(/./g, c =>
    String.fromCodePoint(c.charCodeAt(0) + 127397)
  );
}

interface CountryCardProps { country: Country; }

export function CountryCard({ country }: CountryCardProps) {
  const navigate = useNavigate();

  const heroStyle = country.imageUrl
    ? { backgroundImage: `url(${country.imageUrl})`, backgroundSize: "cover", backgroundPosition: "center" }
    : { background: FALLBACK_GRADIENTS[country.code] ?? "linear-gradient(145deg,#0F172A,#1E293B)" };

  return (
    <div
      className="country-card"
      onClick={() => navigate(`/attractions?countryCode=${country.code}`)}
      role="button"
      tabIndex={0}
      onKeyDown={e => e.key === "Enter" && navigate(`/attractions?countryCode=${country.code}`)}
      aria-label={`Ver atrações de ${country.name}`}
    >
      <div className="country-card__hero" style={heroStyle}>
        <div className="country-card__flag-bg">{getFlag(country.code)}</div>
        <div className="country-card__overlay" />
        <div className="country-card__hero-content">
          <span className="country-card__flag">{getFlag(country.code)}</span>
          {country.currencyCode && (
            <span className="country-card__currency">{country.currencyCode}</span>
          )}
        </div>
      </div>

      <div className="country-card__body">
        <div className="country-card__name">{country.name}</div>
        <div className="country-card__meta">
          {country.capital && <span>{country.capital}</span>}
          {country.capital && country.timezone && <span className="country-card__dot">·</span>}
          {country.timezone && <span>{country.timezone}</span>}
        </div>
        <div className="country-card__footer">
          {country.bestSeason && (
            <span className="badge badge-blue">{country.bestSeason}</span>
          )}
          {country.language && (
            <span className="badge badge-slate">{country.language}</span>
          )}
        </div>
      </div>

      <div className="country-card__arrow">→</div>
    </div>
  );
}
