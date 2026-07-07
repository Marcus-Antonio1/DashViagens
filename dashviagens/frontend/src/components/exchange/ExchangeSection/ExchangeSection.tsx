import { Link } from "react-router-dom";
import { ExchangeCard } from "../ExchangeCard/ExchangeCard";
import { useExchangeRates } from "../../../hooks/useExchangeRates";
import "./ExchangeSection.css";

const FEATURED = ["USD", "EUR", "GBP", "JPY"];

export function ExchangeSection() {
  const { data, isLoading, error } = useExchangeRates();

  const date = data?.date
    ? new Date(data.date + "T12:00:00").toLocaleDateString("pt-BR", {
        day: "2-digit", month: "long", year: "numeric",
      })
    : null;

  return (
    <section className="exchange-section">
      <div className="exchange-section__header">
        <div>
          <p className="exchange-section__eyebrow">Câmbio em tempo real</p>
          <h2 className="exchange-section__title">Cotações do dia</h2>
          {date && <p className="exchange-section__sub">Atualizado em {date}</p>}
        </div>
        <Link to="/exchange" className="exchange-section__link">
          Ver todas as moedas →
        </Link>
      </div>

      {isLoading && (
        <div className="exchange-section__grid">
          {FEATURED.map(c => <div key={c} className="exchange-card exchange-card--skeleton" />)}
        </div>
      )}

      {error && (
        <div className="exchange-section__error">
          Erro ao carregar cotações. Verifique a conexão com o backend.
        </div>
      )}

      {data && (
        <div className="exchange-section__grid">
          {FEATURED.map(code => {
            const value = data.rates[code];
            return value ? (
              <ExchangeCard key={code} currency={code} value={Number(value)} />
            ) : null;
          })}
        </div>
      )}
    </section>
  );
}
