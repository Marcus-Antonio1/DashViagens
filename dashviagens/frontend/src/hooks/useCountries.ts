import { useQuery } from "@tanstack/react-query";
import { getCountries } from "../api/countryApi";

export function useCountries() {
  return useQuery({
    queryKey: ["countries"],
    queryFn: getCountries,
  });
}