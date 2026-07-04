import "./CountryCard.css";
import type { Country } from "../../types/Country";
import { useNavigate } from "react-router-dom";

const BG_COLORS = [
  "#0F172A","#1A1A2E","#1C2B4A","#0D2137","#1A2A1A",
  "#2A1A1A","#1E293B","#0F2027","#1A1F35","#0D1B2A",
];

function getBg(code: string) {
  let h = 0;
  for (const c of code) h = (h * 31 + c.charCodeAt(0)) & 0xffff;
  return BG_COLORS[h % BG_COLORS.length];
}

interface CountryCardProps { country: Country; }

export function CountryCard({ country }: CountryCardProps) {
  const navigate = useNavigate();

  return (
    <div
      className="country-card"
      onClick={() => navigate(`/attractions?countryCode=${country.code}`)}
      role="button"
      tabIndex={0}
      onKeyDown={e => e.key === "Enter" && navigate(`/attractions?countryCode=${country.code}`)}
    >
      <div className="country-card__hero" style={{ background: getBg(country.code ?? "XX") }}>
        <span className="country-card__flag">
          {country.code
            ? country.code.toUpperCase().replace(/./g, c =>
                String.fromCodePoint(c.charCodeAt(0) + 127397)
              )
            : "🌍"}
        </span>
      </div>
      <div className="country-card__body">
        <div className="country-card__name">{country.name}</div>
        <div className="country-card__meta">
          {country.currencyCode} · {country.timezone ?? "—"}
        </div>
        <div className="country-card__badges">
          {country.bestSeason && (
            <span className="badge badge-blue">{country.bestSeason}</span>
          )}
          {country.capital && (
            <span className="badge badge-slate">{country.capital}</span>
          )}
        </div>
      </div>
    </div>
  );
}
