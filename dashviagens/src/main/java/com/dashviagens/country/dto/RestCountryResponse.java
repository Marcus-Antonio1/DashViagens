package com.dashviagens.country.dto;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Mapeia exatamente o formato da resposta da REST Countries v3.1.
 * Tem objetos aninhados: name.common, languages (mapa), currencies (mapa), etc.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record RestCountryResponse(

        @JsonProperty("name")
        CountryName name,

        @JsonProperty("cca3")
        String cca3,

        @JsonProperty("capital")
        List<String> capital,

        @JsonProperty("languages")
        Map<String, String> languages,

        @JsonProperty("currencies")
        Map<String, CurrencyInfo> currencies,

        @JsonProperty("population")
        Long population,

        @JsonProperty("timezones")
        List<String> timezones,

        @JsonProperty("latlng")
        List<Double> latlng

) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record CountryName(
            @JsonProperty("common") String common
    ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record CurrencyInfo(
            @JsonProperty("name") String name,
            @JsonProperty("symbol") String symbol
    ) {}
}
