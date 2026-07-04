package com.dashviagens.attraction.dto;

import com.dashviagens.attraction.model.Attraction;

/**
 * DTO de resposta para Attraction.
 * Evita serializar a entidade JPA diretamente.
 */
public record AttractionResponse(
        Long id,
        String countryCode,
        String name,
        String description,
        String imageUrl,
        Double latitude,
        Double longitude
) {
    public static AttractionResponse from(Attraction a) {
        return new AttractionResponse(
                a.getId(),
                a.getCountry().getCode(),
                a.getName(),
                a.getDescription(),
                a.getImageUrl(),
                a.getLatitude(),
                a.getLongitude()
        );
    }
}
