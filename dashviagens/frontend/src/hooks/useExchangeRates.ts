import { useQuery } from "@tanstack/react-query";
import { getExchangeRates } from "../api/exchangeApi";

export function useExchangeRates(base = "BRL") {
  return useQuery({
    queryKey: ["exchange-rates", base],
    queryFn: () => getExchangeRates(base),
    staleTime: 1000 * 60 * 10,
  });
}