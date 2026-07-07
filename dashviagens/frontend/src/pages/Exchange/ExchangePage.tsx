import { useState } from "react";
import { useExchangeRates } from "../../hooks/useExchangeRates";
import { ExchangeCard } from "../../components/exchange/ExchangeCard/ExchangeCard";
import { convertCurrency } from "../../api/exchangeApi";
import "./ExchangePage.css";

const FEATURED   = ["USD", "EUR", "GBP", "JPY"];
const SECONDARY  = ["CAD", "CHF", "ARS", "CLP"];
const FLAGS: Record<string, string> = {
  CAD: "🇨🇦", CHF: "🇨🇭", ARS: "🇦🇷", CLP: "🇨🇱",
};
const NAMES: Record<string, string> = {
  CAD: "Dólar Canadense", CHF: "Franco Suíço",
  ARS: "Peso Argentino",  CLP: "Peso Chileno",
};
const ALL = ["USD","EUR","GBP","JPY","CAD","CHF","ARS","CLP"];

const fmt = (v: number, digits = 4) =>
  v.toLocaleString("pt-BR", { minimumFractionDigits: 2, maximumFractionDigits: digits });

export function ExchangePage() {
  const { data, isLoading } = useExchangeRates();

  const [amount,    setAmount]    = useState("5000");
  const [from,      setFrom]      = useState("BRL");
  const [to,        setTo]        = useState("USD");
  const [result,    setResult]    = useState<number | null>(null);
  const [converting, setConverting] = useState(false);

  async function handleConvert(e: React.FormEvent) {
    e.preventDefault();
    if (!amount || Number(amount) <= 0) return;
    setConverting(true);
    try {
      const res = await convertCurrency(Number(amount), from, to);
      setResult(res.result);
    } finally {
      setConverting(false);
    }
  }

  const date = data?.date
    ? new Date(data.date + "T12:00:00").toLocaleDateString("pt-BR", {
        day: "2-digit", month: "long", year: "numeric"
      })
    : null;

  return (
    <div className="exchange-page">
      <div className="exchange-container">

        {/* Header */}
        <div className="exchange-header">
          <div className="exchange-header__left">
            <p className="exchange-header__eyebrow">Câmbio em tempo real</p>
            <h1 className="exchange-header__title">Cotações do dia</h1>
            {date && <p className="exchange-header__sub">Atualizado em {date} · Frankfurter API</p>}
          </div>
          <div className="exchange-header__badge">
            <span className="exchange-header__badge-dot" />
            Ao vivo
          </div>
        </div>

        {/* Grid principal */}
        {isLoading ? (
          <div className="exchange-grid">
            {FEATURED.map(c => <div key={c} className="exchange-skeleton" />)}
          </div>
        ) : (
          <div className="exchange-grid">
            {FEATURED.map(code => {
              const value = data?.rates[code];
              return value ? (
                <ExchangeCard key={code} currency={code} value={Number(value)} />
              ) : null;
            })}
          </div>
        )}

        {/* Moedas secundárias */}
        {data && (
          <>
            <p className="exchange-section-title">Outras moedas</p>
            <div className="exchange-secondary-grid">
              {SECONDARY.map(code => {
                const value = data.rates[code];
                return value ? (
                  <div key={code} className="exchange-secondary-card">
                    <span className="exchange-secondary-card__flag">{FLAGS[code]}</span>
                    <div>
                      <div className="exchange-secondary-card__code">{code}</div>
                      <div style={{ fontSize: 10, color: "var(--color-ink-5)" }}>{NAMES[code]}</div>
                    </div>
                    <span className="exchange-secondary-card__value">
                      R$ {fmt(Number(value))}
                    </span>
                  </div>
                ) : null;
              })}
            </div>
          </>
        )}

        {/* Conversor */}
        <div className="exchange-converter">
          <h2 className="exchange-converter__title">Conversor de moedas</h2>
          <p className="exchange-converter__sub">Converta entre qualquer par de moedas suportado</p>

          <form className="exchange-converter__form" onSubmit={handleConvert}>
            <div className="exchange-converter__group">
              <label className="exchange-converter__label">Valor</label>
              <input
                className="exchange-converter__input"
                type="number"
                min="0.01"
                step="0.01"
                value={amount}
                onChange={e => { setAmount(e.target.value); setResult(null); }}
                placeholder="5000"
              />
            </div>

            <div className="exchange-converter__group">
              <label className="exchange-converter__label">De</label>
              <select
                className="exchange-converter__select"
                value={from}
                onChange={e => { setFrom(e.target.value); setResult(null); }}
              >
                <option value="BRL">BRL</option>
                {ALL.map(c => <option key={c} value={c}>{c}</option>)}
              </select>
            </div>

            <span className="exchange-converter__arrow">→</span>

            <div className="exchange-converter__group">
              <label className="exchange-converter__label">Para</label>
              <select
                className="exchange-converter__select"
                value={to}
                onChange={e => { setTo(e.target.value); setResult(null); }}
              >
                {ALL.map(c => <option key={c} value={c}>{c}</option>)}
                <option value="BRL">BRL</option>
              </select>
            </div>

            <button className="exchange-converter__btn" type="submit" disabled={converting}>
              {converting ? "Calculando..." : "Converter"}
            </button>
          </form>

          {result !== null && (
            <div className="exchange-converter__result">
              <span className="exchange-converter__result-from">
                {fmt(Number(amount), 2)} {from}
              </span>
              <span className="exchange-converter__result-arrow">=</span>
              <span className="exchange-converter__result-to">
                {fmt(result, 2)}
              </span>
              <span className="exchange-converter__result-code">{to}</span>
            </div>
          )}
        </div>

      </div>
    </div>
  );
}
