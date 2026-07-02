import { ExchangeCard } from "../ExchangeCard/ExchangeCard";
import { useExchangeRates } from "../../../hooks/useExchangeRates";

export function ExchangeSection() {
  const { data, isLoading, error } = useExchangeRates();

  if (isLoading) {
    return <h2>Carregando cotações...</h2>;
  }

  if (error || !data) {
    return <h2>Erro ao carregar as cotações.</h2>;
  }

  const usd = data.rates["USD"] ? 1 / data.rates["USD"] : 0;
  const eur = data.rates["EUR"] ? 1 / data.rates["EUR"] : 0;
  const gbp = data.rates["GBP"] ? 1 / data.rates["GBP"] : 0;

  return (
    <section>
      <h2>Cotações do dia</h2>

      <div
        style={{
          display: "flex",
          gap: "20px",
          flexWrap: "wrap",
        }}
      >
        <ExchangeCard currency="USD" value={usd} />
        <ExchangeCard currency="EUR" value={eur} />
        <ExchangeCard currency="GBP" value={gbp} />
      </div>
    </section>
  );
}