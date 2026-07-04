import { BrowserRouter, Routes, Route } from "react-router-dom";
import { MainLayout } from "../../components/layout/MainLayout";
import { HomePage }       from "../../pages/Home";
import { CountriesPage }  from "../../pages/Countries";
import { BudgetPage }     from "../../pages/Budget";
import { AttractionsPage } from "../../pages/attractions";
import { ExchangePage }   from "../../pages/Exchange/ExchangePage";

export function AppRouter() {
  return (
    <BrowserRouter>
      <Routes>
        <Route element={<MainLayout />}>
          <Route path="/"           element={<HomePage />}       />
          <Route path="/exchange"   element={<ExchangePage />}   />
          <Route path="/countries"  element={<CountriesPage />}  />
          <Route path="/budget"     element={<BudgetPage />}     />
          <Route path="/attractions" element={<AttractionsPage />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}
