package com.dashviagens.cost.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record BudgetRequest(
        @NotBlank String countryCode,
        @Min(1) int days,
        @NotNull @Positive BigDecimal totalBudget
) {
}
