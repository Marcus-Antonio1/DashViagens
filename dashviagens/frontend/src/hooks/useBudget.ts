import { useMutation } from "@tanstack/react-query";
import { estimateBudget } from "../api/budgetApi";

export function useBudget() {
  return useMutation({
    mutationFn: estimateBudget,
  });
}