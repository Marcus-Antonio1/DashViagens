import { ExchangeCard } from "../ExchangeCard/ExchangeCard";

import { useExchangeRates } from "../../../hooks/useExchangeRates";

import "./ExchangeSection.css";

export function ExchangeSection() {

const { data, isLoading, error } = useExchangeRates();

if (isLoading) {

return <p>Carregando cotações...</p>;

}

if (error || !data || !data.rates) {

return <p>Erro ao carregar cotações.</p>;

}

const usd = data.rates["USD"] ? 1 / data.rates["USD"] : 0;

const eur = data.rates["EUR"] ? 1 / data.rates["EUR"] : 0;

const gbp = data.rates["GBP"] ? 1 / data.rates["GBP"] : 0;

return (

<section className="exchange-section">

<h2>Cotações do dia</h2>

<div className="exchange-grid">

<ExchangeCard currency="USD" value={usd} />

<ExchangeCard currency="EUR" value={eur} />

<ExchangeCard currency="GBP" value={gbp} />

</div>

</section>

);

}