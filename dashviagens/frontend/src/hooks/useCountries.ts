import { useQuery } from "@tanstack/react-query";
import { api } from "../api/axios";
import type { Country } from "../types/Country";

async function fetchCountries(): Promise<Country[]> {
  const res = await api.get("/countries");
  return res.data;
}

export function useCountries() {
  return useQuery({
    queryKey: ["countries"],
    queryFn: fetchCountries,
    staleTime: 1000 * 60 * 30,
  });
}
