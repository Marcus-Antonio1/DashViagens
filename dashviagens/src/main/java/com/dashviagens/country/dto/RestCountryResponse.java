package com.dashviagens.country.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Mapeia a resposta da countries.dev (substituta da REST Countries v3.1 descontinuada).
 * Endpoint: GET https://countries.dev/alpha/{code}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record RestCountryResponse(

        @JsonProperty("name")
        String name,

        @JsonProperty("alpha3Code")
        String alpha3Code,

        @JsonProperty("capital")
        String capital,

        @JsonProperty("languages")
        List<LanguageInfo> languages,

        @JsonProperty("currencies")
        List<CurrencyInfo> currencies,

        @JsonProperty("population")
        Long population,

        @JsonProperty("timezones")
        List<String> timezones,

        @JsonProperty("latlng")
        List<Double> latlng

) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record LanguageInfo(
            @JsonProperty("name") String name
    ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record CurrencyInfo(
            @JsonProperty("code") String code,
            @JsonProperty("name") String currencyName
    ) {}
}
