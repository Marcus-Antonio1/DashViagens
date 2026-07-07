import "./CountryCard.css";
import type { Country } from "../../types/Country";
import { useNavigate } from "react-router-dom";

// Paletas por continente/região baseado no código do país
const PALETTES: Record<string, string> = {
  JPN: "linear-gradient(145deg, #1a1a2e 0%, #16213e 60%, #0f3460 100%)",
  ITA: "linear-gradient(145deg, #1c2b4a 0%, #0d3b6e 100%)",
  FRA: "linear-gradient(145deg, #0d1b2a 0%, #1b2838 60%, #2d4a6b 100%)",
  PRT: "linear-gradient(145deg, #1a2a1a 0%, #0d2b0d 100%)",
  ESP: "linear-gradient(145deg, #2a1a0d 0%, #4a2a0d 100%)",
  USA: "linear-gradient(145deg, #0d1b2a 0%, #1a2a3a 60%, #0d2b4a 100%)",
  CAN: "linear-gradient(145deg, #1a0d0d 0%, #2a1a1a 100%)",
  ARG: "linear-gradient(145deg, #0d1a2a 0%, #0d2a3a 100%)",
  CHL: "linear-gradient(145deg, #0d1a2a 0%, #1a2a0d 100%)",
  GRC: "linear-gradient(145deg, #0d1f3a 0%, #1a3a5c 100%)",
  THA: "linear-gradient(145deg, #1a0d2a 0%, #2a1a0d 100%)",
  DEU: "linear-gradient(145deg, #1a1a1a 0%, #2a2a2a 100%)",
  GBR: "linear-gradient(145deg, #0d1a2a 0%, #1a0d2a 100%)",
  AUS: "linear-gradient(145deg, #1a0d0d 0%, #2a1a0d 100%)",
  MEX: "linear-gradient(145deg, #1a1a0d 0%, #0d2a1a 100%)",
  NLD: "linear-gradient(145deg, #0d1a2a 0%, #1a2a0d 100%)",
  CHE: "linear-gradient(145deg, #1a1a2a 0%, #0d1a2a 100%)",
};

const DEFAULT_PALETTE = "linear-gradient(145deg, #0F172A 0%, #1E293B 100%)";

function getFlag(code: string) {
  return code.toUpperCase().replace(/./g, c =>
    String.fromCodePoint(c.charCodeAt(0) + 127397)
  );
}

interface CountryCardProps { country: Country; }

export function CountryCard({ country }: CountryCardProps) {
  const navigate = useNavigate();
  const bg = PALETTES[country.code] ?? DEFAULT_PALETTE;

  return (
    <div
      className="country-card"
      onClick={() => navigate(`/attractions?countryCode=${country.code}`)}
      role="button"
      tabIndex={0}
      onKeyDown={e => e.key === "Enter" && navigate(`/attractions?countryCode=${country.code}`)}
      aria-label={`Ver atrações de ${country.name}`}
    >
      {/* Hero com gradiente + flag grande */}
      <div className="country-card__hero" style={{ background: bg }}>
        <div className="country-card__flag-bg">{getFlag(country.code)}</div>
        <div className="country-card__overlay" />
        <div className="country-card__hero-content">
          <span className="country-card__flag">{getFlag(country.code)}</span>
          <div className="country-card__hero-right">
            {country.currencyCode && (
              <span className="country-card__currency">{country.currencyCode}</span>
            )}
          </div>
        </div>
      </div>

      {/* Body */}
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

      {/* Indicador de hover */}
      <div className="country-card__arrow">→</div>
    </div>
  );
}
