import { useQuery } from "@tanstack/react-query";
import { getExchangeRates } from "../api/exchangeApi";

export function useExchangeRates() {
  return useQuery({
    queryKey: ["exchange-rates"],
    queryFn: getExchangeRates,
    staleTime: 1000 * 60 * 10, // 10 min
  });
}
