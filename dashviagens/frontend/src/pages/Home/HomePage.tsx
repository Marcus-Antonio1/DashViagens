import { MainLayout } from "../../components/layout/MainLayout";
import { Button, Card, Container } from "../../components/ui";

export function HomePage() {
  return (
    <MainLayout>
      <Container>
        <div style={{ marginTop: "80px" }}>
          <h1>Planeje sua próxima viagem</h1>

          <p>
            Consulte cotações em tempo real, descubra destinos incríveis e
            planeje seu orçamento de viagem.
          </p>

          <div
            style={{
              display: "flex",
              gap: "16px",
              marginTop: "32px",
            }}
          >
            <Button>Explorar países</Button>

            <Button>Ver câmbio</Button>
          </div>

          <div
            style={{
              display: "grid",
              gridTemplateColumns: "repeat(3,1fr)",
              gap: "20px",
              marginTop: "60px",
            }}
          >
            <Card>
              <h2>195+</h2>
              <p>Países disponíveis</p>
            </Card>

            <Card>
              <h2>170+</h2>
              <p>Moedas monitoradas</p>
            </Card>

            <Card>
              <h2>24h</h2>
              <p>Cotações atualizadas</p>
            </Card>
          </div>
        </div>
      </Container>
    </MainLayout>
  );
}