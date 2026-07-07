import "./ExchangeCard.css";

const CURRENCY_META: Record<string, { name: string; flag: string }> = {
  USD: { name: "Dólar americano",  flag: "🇺🇸" },
  EUR: { name: "Euro",             flag: "🇪🇺" },
  GBP: { name: "Libra esterlina",  flag: "🇬🇧" },
  JPY: { name: "Iene japonês",     flag: "🇯🇵" },
  CAD: { name: "Dólar canadense",  flag: "🇨🇦" },
  CHF: { name: "Franco suíço",     flag: "🇨🇭" },
  ARS: { name: "Peso argentino",   flag: "🇦🇷" },
  CLP: { name: "Peso chileno",     flag: "🇨🇱" },
};

// Barras decorativas — sem dados históricos reais, representam variação visual
const MOCK_BARS = [55, 62, 50, 70, 60, 80, 75];

interface ExchangeCardProps {
  currency: string;
  value: number;
  change?: number;
}

export function ExchangeCard({ currency, value, change }: ExchangeCardProps) {
  const meta   = CURRENCY_META[currency];
  const isUp   = change !== undefined && change > 0;
  const isDown = change !== undefined && change < 0;

  const maxBar = Math.max(...MOCK_BARS);

  return (
    <div className="exchange-card">
      <div className="exchange-card__header">
        <div className="exchange-card__flag">{meta?.flag ?? "🌐"}</div>
        <div>
          <div className="exchange-card__code">{currency}</div>
          <div className="exchange-card__name">{meta?.name ?? currency}</div>
        </div>
      </div>

      <div className="exchange-card__value">
        R$ {value.toLocaleString("pt-BR", {
          minimumFractionDigits: 2,
          maximumFractionDigits: 4,
        })}
      </div>

      {change !== undefined && (
        <div className={`exchange-card__change ${isUp ? "up" : isDown ? "down" : "flat"}`}>
          {isUp ? "▲" : isDown ? "▼" : "–"}&nbsp;
          {Math.abs(change).toFixed(2)}%
          <span className="exchange-card__change-label">24h</span>
        </div>
      )}

      <div className="exchange-card__chart">
        {MOCK_BARS.map((h, i) => (
          <div
            key={i}
            className={`exchange-card__bar ${i === MOCK_BARS.length - 1 ? (isUp ? "up" : isDown ? "down" : "") : ""}`}
            style={{ height: `${(h / maxBar) * 100}%` }}
          />
        ))}
      </div>
    </div>
  );
}
