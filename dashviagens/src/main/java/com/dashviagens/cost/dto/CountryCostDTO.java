package com.dashviagens.cost.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CountryCostDTO(
        @NotBlank String countryCode,
        @NotNull @Positive BigDecimal hotelPerDay,
        @NotNull @Positive BigDecimal foodPerDay,
        @NotNull @Positive BigDecimal transportPerDay,
        @NotNull @Positive BigDecimal activitiesPerDay,
        BigDecimal estimatedFlight,
        String currency
) {}
