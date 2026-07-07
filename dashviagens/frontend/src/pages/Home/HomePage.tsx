import { Link } from "react-router-dom";
import { HeroSection }     from "../../components/home/HeroSection/HeroSection";
import { ExchangeSection } from "../../components/exchange/ExchangeSection/ExchangeSection";
import { useCountries }    from "../../hooks/useCountries";
import { CountryCard }     from "../../components/countries/CountryCard";
import "./HomePage.css";

export function HomePage() {
  const { data: countries } = useCountries();
  const featured = countries?.slice(0, 3) ?? [];

  return (
    <>
      <HeroSection />

      <div className="home-container">
        <ExchangeSection />

        {featured.length > 0 && (
          <section className="home-section">
            <div className="home-section__header">
              <div>
                <p className="home-section__eyebrow">Destinos em destaque</p>
                <h2 className="home-section__title">Explore o mundo</h2>
              </div>
              <Link to="/countries" className="home-section__link">
                Ver todos os países →
              </Link>
            </div>
            <div className="home-featured-grid">
              {featured.map(c => (
                <CountryCard key={c.id} country={c} />
              ))}
            </div>
          </section>
        )}

        <section className="home-cta">
          <div className="home-cta__icon">🧮</div>
          <div className="home-cta__content">
            <h2 className="home-cta__title">Quanto vai custar sua viagem ?</h2>
            <p className="home-cta__sub">
              Informe o destino, os dias e o orçamento assim estimamos
              passagem, hotel, alimentação, transporte e passeios.
            </p>
          </div>
          <Link to="/budget" className="home-cta__btn">
            Abrir calculadora
          </Link>
        </section>
      </div>
    </>
  );
}
