package com.dashviagens.country.dto;

public record CountryDTO(
        Long id,
        String code,
        String name,
        String capital,
        String language,
        String currencyCode,
        Long population,
        String timezone,
        String bestSeason,
        String bestSeasonDescription,
        Double latitude,
        Double longitude,
        String imageUrl
) {
}
