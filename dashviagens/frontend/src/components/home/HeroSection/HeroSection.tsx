import "./HeroSection.css";

export function HeroSection() {
  return (
    <section className="hero">
      <div className="hero__content">
        <h1>DashViagens</h1>

        <p>
          Planeje sua próxima viagem utilizando informações em tempo real sobre
          câmbio, países, atrações turísticas e estimativa de custos.
        </p>

        <div className="hero__actions">
          <button>Explorar países</button>
          <button>Calcular viagem</button>
        </div>
      </div>
    </section>
  );
}