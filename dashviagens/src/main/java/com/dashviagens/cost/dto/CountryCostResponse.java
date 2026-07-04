package com.dashviagens.cost.dto;

import java.math.BigDecimal;

import com.dashviagens.cost.model.CountryCost;

/**
 * DTO de resposta para CountryCost.
 * Evita serializar a entidade JPA diretamente.
 */
public record CountryCostResponse(
        Long id,
        String countryCode,
        BigDecimal hotelPerDay,
        BigDecimal foodPerDay,
        BigDecimal transportPerDay,
        BigDecimal activitiesPerDay,
        BigDecimal estimatedFlight,
        String currency
) {
    public static CountryCostResponse from(CountryCost cost) {
        return new CountryCostResponse(
                cost.getId(),
                cost.getCountry().getCode(),
                cost.getHotelPerDay(),
                cost.getFoodPerDay(),
                cost.getTransportPerDay(),
                cost.getActivitiesPerDay(),
                cost.getEstimatedFlight(),
                cost.getCurrency()
        );
    }
}
