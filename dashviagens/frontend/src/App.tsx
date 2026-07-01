import { MainLayout } from "./components/layout";
import { Button, Card, Container } from "./components/ui";

function App() {
  return (
    <MainLayout>
      <Container>
        <div
          style={{
            marginTop: "80px",
            display: "flex",
            flexDirection: "column",
            gap: "24px",
          }}
        >
          <h1>Planeje sua próxima viagem</h1>

          <Card>
            <h2>Bem-vindo ao DashViagens</h2>

            <p>
              Consulte cotações em tempo real, descubra destinos incríveis e
              planeje seu orçamento de viagem.
            </p>

            <br />

            <Button>Explorar destinos</Button>
          </Card>
        </div>
      </Container>
    </MainLayout>
  );
}

export default App;