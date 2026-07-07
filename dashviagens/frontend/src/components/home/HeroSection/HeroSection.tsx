import { Link } from "react-router-dom";
import { useExchangeRates } from "../../../hooks/useExchangeRates";
import "./HeroSection.css";

export function HeroSection() {
  const { data } = useExchangeRates();

  const stats = data ? [
    { code: "USD", value: data.rates["USD"], label: "Dólar" },
    { code: "EUR", value: data.rates["EUR"], label: "Euro" },
    { code: "GBP", value: data.rates["GBP"], label: "Libra" },
  ] : [];

  return (
    <section className="hero">
      <div className="hero__bg-grid" aria-hidden />

      <div className="hero__inner">
        <div className="hero__badge">
          <span className="hero__badge-dot" />
          Cotações atualizadas diariamente
        </div>

        <h1 className="hero__title">
          Planeje sua viagem com
          <span className="hero__title-highlight"> inteligência</span>
        </h1>

        <p className="hero__sub">
          Câmbio em tempo real, destinos turísticos e calculadora de custo
          tudo que você precisa para planejar a viagem dos seus sonhos.
        </p>

        <div className="hero__actions">
          <Link to="/countries" className="hero__btn-primary">
            Explorar destinos
          </Link>
          <Link to="/exchange" className="hero__btn-secondary">
            Ver câmbio ao vivo
          </Link>
        </div>

        {stats.length > 0 && (
          <div className="hero__rates">
            {stats.map(s => (
              <div key={s.code} className="hero__rate">
                <span className="hero__rate-code">{s.code}</span>
                <span className="hero__rate-value">
                  R$ {Number(s.value).toLocaleString("pt-BR", {
                    minimumFractionDigits: 2,
                    maximumFractionDigits: 3,
                  })}
                </span>
                <span className="hero__rate-label">{s.label}</span>
              </div>
            ))}
            <div className="hero__rate hero__rate--link">
              <Link to="/exchange">Ver todas →</Link>
            </div>
          </div>
        )}
      </div>
    </section>
  );
}
