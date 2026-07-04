import { useState } from "react";

import { useBudget } from "../../hooks/useBudget";

export function BudgetPage() {

  const { mutate, data, isPending } = useBudget();

  const [countryCode, setCountryCode] = useState("");

  const [days, setDays] = useState(7);

  const [totalBudget, setTotalBudget] = useState(10000);

  function handleSubmit(e: React.FormEvent) {

    e.preventDefault();

    mutate({

      countryCode,

      days,

      totalBudget,

    });

  }

  return (

    <>

      <h1>Calculadora de Viagem</h1>

      <form onSubmit={handleSubmit}>

        <div>

          <label>País</label>

          <input

            value={countryCode}

            onChange={(e)=>setCountryCode(e.target.value.toUpperCase())}

            placeholder="JP"

          />

        </div>

        <div>

          <label>Dias</label>

          <input

            type="number"

            value={days}

            onChange={(e)=>setDays(Number(e.target.value))}

          />

        </div>

        <div>

          <label>Orçamento</label>

          <input

            type="number"

            value={totalBudget}

            onChange={(e)=>setTotalBudget(Number(e.target.value))}

          />

        </div>

        <button type="submit">

          Calcular

        </button>

      </form>

      {isPending && <p>Calculando...</p>}

      {data && (

        <>

          <h2>Resultado</h2>

          <p>Passagem: R$ {data.estimatedFlight.toFixed(2)}</p>

          <p>Hotel: R$ {data.estimatedHotel.toFixed(2)}</p>

          <p>Alimentação: R$ {data.estimatedFood.toFixed(2)}</p>

          <p>Transporte: R$ {data.estimatedTransport.toFixed(2)}</p>

          <p>Passeios: R$ {data.estimatedActivities.toFixed(2)}</p>

          <hr />

          <p>Total estimado: R$ {data.totalEstimated.toFixed(2)}</p>

          <p>Orçamento: R$ {data.totalBudget.toFixed(2)}</p>

          <p>Saldo restante: R$ {data.remaining.toFixed(2)}</p>

          <h3>

            {data.withinBudget

              ? "✅ Dentro do orçamento"

              : "❌ Fora do orçamento"}

          </h3>

        </>

      )}

    </>

  );

}