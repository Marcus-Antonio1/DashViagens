import { Container } from "../ui";

export function Navbar() {
  return (
    <header
      style={{
        borderBottom: "1px solid var(--border)",
        background: "var(--surface)",
      }}
    >
      <Container>
        <div
          style={{
            height: "72px",
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
          }}
        >
          <h2>🌍 DashViagens</h2>

          <nav
            style={{
              display: "flex",
              gap: "24px",
            }}
          >
            <a href="#">Início</a>
            <a href="#">Países</a>
            <a href="#">Câmbio</a>
            <a href="#">Calculadora</a>
          </nav>
        </div>
      </Container>
    </header>
  );
}