import { api } from "./axios";
import { ENDPOINTS } from "./endpoints";
import type { ExchangeRate } from "../types/ExchangeRate";

// GET /api/exchange/rates sem parâmetro pois o backend já retorna em BRL
export async function getExchangeRates(): Promise<ExchangeRate> {
  const response = await api.get(ENDPOINTS.exchange);
  return response.data;
}

export async function convertCurrency(
  amount: number,
  from: string,
  to: string
): Promise<{ amount: number; from: string; to: string; result: number; date: string }> {
  const response = await api.get("/exchange/convert", { params: { amount, from, to } });
  return response.data;
}
