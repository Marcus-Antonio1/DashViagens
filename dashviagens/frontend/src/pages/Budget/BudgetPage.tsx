import { useState } from "react";
import { useCountries } from "../../hooks/useCountries";
import { api } from "../../api/axios";
import "./BudgetPage.css";

interface BudgetResult {
  countryCode: string;
  days: number;
  estimatedFlight: number;
  estimatedHotel: number;
  estimatedFood: number;
  estimatedTransport: number;
  estimatedActivities: number;
  totalEstimated: number;
  totalBudget: number;
  remaining: number;
  withinBudget: boolean;
}

const fmt = (v: number) =>
  v.toLocaleString("pt-BR", { style: "currency", currency: "BRL" });

const ITEMS = [
  { key: "estimatedFlight",     icon: "✈️", label: "Passagem estimada"  },
  { key: "estimatedHotel",      icon: "🏨", label: "Hotel"              },
  { key: "estimatedFood",       icon: "🍽️", label: "Alimentação"        },
  { key: "estimatedTransport",  icon: "🚌", label: "Transporte"         },
  { key: "estimatedActivities", icon: "📸", label: "Passeios"           },
];

export function BudgetPage() {
  const { data: countries } = useCountries();

  const [countryCode, setCountryCode] = useState("");
  const [days,        setDays]        = useState("10");
  const [budget,      setBudget]      = useState("15000");
  const [result,      setResult]      = useState<BudgetResult | null>(null);
  const [loading,     setLoading]     = useState(false);
  const [error,       setError]       = useState("");

  async function handleSubmit(e: React.FormEvent) {
    e.preventDefault();
    if (!countryCode) { setError("Selecione um destino."); return; }
    setError(""); setLoading(true);
    try {
      const res = await api.post("/costs/estimate", {
        countryCode,
        days: Number(days),
        totalBudget: Number(budget),
      });
      setResult(res.data);
    } catch {
      setError("Erro ao calcular. Verifique se o país possui custo médio cadastrado.");
    } finally {
      setLoading(false);
    }
  }

  const selectedCountry = countries?.find(c => c.code === countryCode);

  return (
    <div className="budget-page">
      <div className="budget-container">

        <div className="budget-header">
          <p className="budget-header__eyebrow">Planejamento financeiro</p>
          <h1 className="budget-header__title">Calculadora de viagem</h1>
          <p className="budget-header__sub">
            Estime o custo total com base no destino, duração e orçamento disponível
          </p>
        </div>

        <div className="budget-layout">

          {/* Formulário */}
          <div className="budget-form-card">
            <div className="budget-form-card__header">
              <p className="budget-form-card__title">Parâmetros da viagem</p>
              <p className="budget-form-card__sub">Preencha os dados para calcular</p>
            </div>
            <div className="budget-divider" />
            <div className="budget-form-body">
              <form onSubmit={handleSubmit}>
                <div className="budget-form-group">
                  <label className="budget-label">Destino</label>
                  <select
                    className="budget-select"
                    value={countryCode}
                    onChange={e => { setCountryCode(e.target.value); setResult(null); }}
                  >
                    <option value="">Selecione um país...</option>
                    {countries?.map(c => (
                      <option key={c.code} value={c.code}>{c.name}</option>
                    ))}
                  </select>
                </div>

                <div className="budget-form-row">
                  <div className="budget-form-group">
                    <label className="budget-label">Duração (dias)</label>
                    <input
                      className="budget-input"
                      type="number" min="1" max="365"
                      value={days}
                      onChange={e => { setDays(e.target.value); setResult(null); }}
                    />
                  </div>
                  <div className="budget-form-group">
                    <label className="budget-label">Orçamento (R$)</label>
                    <input
                      className="budget-input"
                      type="number" min="1"
                      value={budget}
                      onChange={e => { setBudget(e.target.value); setResult(null); }}
                    />
                  </div>
                </div>

                {error && <p className="budget-error">{error}</p>}

                <button className="budget-btn" type="submit" disabled={loading}>
                  {loading ? "Calculando..." : "Calcular orçamento"}
                </button>
              </form>
            </div>
          </div>

          {/* Resultado */}
          {result ? (
            <div className="budget-result">
              <div className="budget-result__header">
                <div>
                  <p className="budget-result__label">Estimativa para</p>
                  <p className="budget-result__destination">
                    {result.days} dias em {selectedCountry?.name ?? result.countryCode}
                  </p>
                </div>
                <div className="budget-result__right">
                  <p className="budget-result__total-label">Total estimado</p>
                  <p className="budget-result__total">{fmt(result.totalEstimated)}</p>
                </div>
              </div>

              <div className="budget-result__rows">
                {ITEMS.map(item => (
                  <div key={item.key} className="budget-result__row">
                    <span className="budget-result__item">
                      <span className="budget-result__item-icon">{item.icon}</span>
                      {item.label}
                      {item.key !== "estimatedFlight" && ` · ${result.days} dias`}
                    </span>
                    <span className="budget-result__value">
                      {fmt(result[item.key as keyof BudgetResult] as number)}
                    </span>
                  </div>
                ))}
              </div>

              <div className={`budget-result__surplus ${!result.withinBudget ? "deficit" : ""}`}>
                <span className="budget-result__surplus-label">
                  {result.withinBudget ? "💰 Reserva disponível" : "⚠️ Orçamento insuficiente"}
                </span>
                <span className="budget-result__surplus-value">
                  {fmt(Math.abs(result.remaining))}
                </span>
              </div>
            </div>
          ) : (
            <div className="budget-placeholder">
              <div className="budget-placeholder__icon">🧮</div>
              <p className="budget-placeholder__title">Pronto para calcular</p>
              <p className="budget-placeholder__sub">
                Selecione um destino e informe os dados da viagem para ver a estimativa detalhada.
              </p>
            </div>
          )}

        </div>
      </div>
    </div>
  );
}
