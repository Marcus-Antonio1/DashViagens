import { useState } from "react";
import { useExchangeRates } from "../../hooks/useExchangeRates";
import { ExchangeCard } from "../../components/exchange/ExchangeCard/ExchangeCard";
import { convertCurrency } from "../../api/exchangeApi";
import "./ExchangePage.css";

const ALL_CURRENCIES = ["USD","EUR","GBP","JPY","CAD","CHF","ARS","CLP"];

export function ExchangePage() {
  const { data, isLoading, error } = useExchangeRates();

  const [amount, setAmount]   = useState("5000");
  const [from, setFrom]       = useState("BRL");
  const [to, setTo]           = useState("USD");
  const [result, setResult]   = useState<number | null>(null);
  const [converting, setConverting] = useState(false);

  async function handleConvert(e: React.FormEvent) {
    e.preventDefault();
    if (!amount) return;
    setConverting(true);
    try {
      const res = await convertCurrency(Number(amount), from, to);
      setResult(res.result);
    } finally {
      setConverting(false);
    }
  }

  return (
    <div className="exchange-page">
      <div className="container">
        <div className="page-header">
          <h1 className="page-title">Câmbio em tempo real</h1>
          {data && (
            <p className="page-sub">
              Atualizado em {new Date(data.date).toLocaleDateString("pt-BR")} · Frankfurter API
            </p>
          )}
        </div>

        {isLoading && (
          <div className="exchange-page__grid">
            {ALL_CURRENCIES.map(c => <div key={c} className="exchange-card exchange-card--skeleton" />)}
          </div>
        )}

        {error && <p className="exchange-page__error">Erro ao carregar cotações.</p>}

        {data && (
          <div className="exchange-page__grid">
            {ALL_CURRENCIES.map(code => {
              const value = data.rates[code];
              return value ? <ExchangeCard key={code} currency={code} value={Number(value)} /> : null;
            })}
          </div>
        )}

        <div className="converter">
          <p className="converter__label">Conversor de moedas</p>
          <form className="converter__form" onSubmit={handleConvert}>
            <div className="converter__group">
              <label className="converter__field-label">Valor</label>
              <input
                className="converter__input"
                type="number"
                min="0.01"
                step="0.01"
                value={amount}
                onChange={e => setAmount(e.target.value)}
                placeholder="5000"
              />
            </div>
            <div className="converter__group">
              <label className="converter__field-label">De</label>
              <select className="converter__select" value={from} onChange={e => setFrom(e.target.value)}>
                <option value="BRL">BRL</option>
                {ALL_CURRENCIES.map(c => <option key={c} value={c}>{c}</option>)}
              </select>
            </div>
            <span className="converter__arrow">→</span>
            <div className="converter__group">
              <label className="converter__field-label">Para</label>
              <select className="converter__select" value={to} onChange={e => setTo(e.target.value)}>
                {ALL_CURRENCIES.map(c => <option key={c} value={c}>{c}</option>)}
                <option value="BRL">BRL</option>
              </select>
            </div>
            <button className="converter__btn" type="submit" disabled={converting}>
              {converting ? "..." : "Converter"}
            </button>
          </form>
          {result !== null && (
            <div className="converter__result">
              {Number(amount).toLocaleString("pt-BR")} {from} =&nbsp;
              <strong>
                {result.toLocaleString("pt-BR", { minimumFractionDigits: 2 })} {to}
              </strong>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}
