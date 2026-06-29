package com.dashviagens.cost.dto;

import java.math.BigDecimal;

public record BudgetResponse(
        String countryCode,
        int days,
        BigDecimal estimatedFlight,
        BigDecimal estimatedHotel,
        BigDecimal estimatedFood,
        BigDecimal estimatedTransport,
        BigDecimal estimatedActivities,
        BigDecimal totalEstimated,
        BigDecimal totalBudget,
        BigDecimal remaining,
        boolean withinBudget
) {
}
