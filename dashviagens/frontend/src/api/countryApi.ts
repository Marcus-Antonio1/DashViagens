import { api } from "./axios";
import type { Country } from "../types/Country";

export async function getCountries(): Promise<Country[]> {
  const response = await api.get("/countries");

  return response.data;
}