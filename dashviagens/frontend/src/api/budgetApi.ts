import { api } from "./axios";
import type { BudgetRequest } from "../types/BudgetRequest";
import type { BudgetResponse } from "../types/BudgetResponse";

export async function estimateBudget(
  request: BudgetRequest
): Promise<BudgetResponse> {
  const response = await api.post(
    "/costs/estimate",
    request
  );

  return response.data;
}