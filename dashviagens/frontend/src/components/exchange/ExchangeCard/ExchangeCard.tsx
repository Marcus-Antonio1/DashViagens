import "./ExchangeCard.css";

interface ExchangeCardProps {
  currency: string;
  value: number;
}

export function ExchangeCard({
  currency,
  value,
}: ExchangeCardProps) {
  return (
    <div className="exchange-card">
      <h3>{currency}</h3>

      <span className="exchange-card__value">
        R$ {value.toFixed(2)}
      </span>
    </div>
  );
}