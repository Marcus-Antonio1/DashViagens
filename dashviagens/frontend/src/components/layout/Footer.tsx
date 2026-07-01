import { Container } from "../ui";

export function Footer() {
  return (
    <footer
      style={{
        marginTop: "80px",
        borderTop: "1px solid var(--border)",
        padding: "32px 0",
      }}
    >
      <Container>
        <p>© 2026 DashViagens</p>
    </Container>
    </footer>
  );
}