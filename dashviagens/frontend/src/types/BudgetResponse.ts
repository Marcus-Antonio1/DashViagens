export interface BudgetResponse {
  countryCode: string;
  days: number;
  estimatedFlight: number;
  estimatedHotel: number;
  estimatedFood: number;
  estimatedTransport: number;
  estimatedActivities: number;
  totalEstimated: number;
  totalBudget: number;
  remaining: number;
  withinBudget: boolean;
}