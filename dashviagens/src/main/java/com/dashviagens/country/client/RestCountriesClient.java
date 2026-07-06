package com.dashviagens.country.client;

import java.time.Duration;

import com.dashviagens.country.dto.RestCountryResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

/**
 * Integra com countries.dev substituta gratuita e sem API key da REST Countries.
 *
 * Endpoint: GET https://countries.dev/alpha/{code}
 * Documentação: https://countries.dev/docs/api/alpha
 */
@Component
public class RestCountriesClient {

    private final WebClient webClient;

    public RestCountriesClient(WebClient.Builder webClientBuilder,
                               @Value("${dashviagens.external-apis.rest-countries-base-url}") String baseUrl) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    /**
     * Busca dados de um país pelo código alpha-3 (ex: "JPN", "ITA", "BRA").
     * Retorna null se o país não for encontrado na API.
     */
    public RestCountryResponse fetchByCode(String alpha3Code) {
        try {
            return webClient.get()
                    .uri("/alpha/{code}", alpha3Code.toUpperCase())
                    .retrieve()
                    .bodyToMono(RestCountryResponse.class)
                    .timeout(Duration.ofSeconds(5))
                    .block();

        } catch (WebClientResponseException.NotFound ex) {
            return null;
        }
    }
}
