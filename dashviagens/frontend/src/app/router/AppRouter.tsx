import { BrowserRouter, Routes, Route } from "react-router-dom";

import { HomePage } from "../../pages/Home/HomePage";
import { CountriesPage } from "../../pages/Countries/CountriesPage";
import { ExchangePage } from "../../pages/Exchange/ExchangePage";
import { BudgetPage } from "../../pages/Budget/BudgetPage";

export function AppRouter() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/countries" element={<CountriesPage />} />
        <Route path="/exchange" element={<ExchangePage />} />
        <Route path="/budget" element={<BudgetPage />} />
      </Routes>
    </BrowserRouter>
  );
}