import { api } from "./axios";
import { ENDPOINTS } from "./endpoints";
import type { ExchangeRate } from "../types/ExchangeRate";

export async function getExchangeRates(
  base = "BRL"
): Promise<ExchangeRate> {
  const response = await api.get(ENDPOINTS.exchange, {
    params: {
      base,
    },
  });

  return response.data;
}