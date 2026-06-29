package com.dashviagens.attraction.dto;

import jakarta.validation.constraints.NotBlank;

public record AttractionDTO(
        Long id,
        @NotBlank String countryCode,
        @NotBlank String name,
        String description,
        String imageUrl,
        Double latitude,
        Double longitude
) {
}
