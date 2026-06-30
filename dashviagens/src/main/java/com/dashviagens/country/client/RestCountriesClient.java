package com.dashviagens.country.client;

import java.time.Duration;
import java.util.List;

import com.dashviagens.country.dto.RestCountryResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;


@Component
public class RestCountriesClient {

    private final WebClient webClient;

    public RestCountriesClient(WebClient.Builder webClientBuilder,
                               @Value("${dashviagens.external-apis.rest-countries-base-url}") String baseUrl) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    /**
     * Busca os dados de um pais pelo codigo alpha-3 (ex: "JPN", "ITA", "BRA").
     *
     * @return o objeto do pais, ou null se nao encontrado na REST Countries.
     */
    public RestCountryResponse fetchByCode(String alpha3Code) {
        try {
            List<RestCountryResponse> result = webClient.get()
                    .uri("/alpha/{code}", alpha3Code)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<RestCountryResponse>>() {})
                    .timeout(Duration.ofSeconds(5))
                    .block();

            return (result != null && !result.isEmpty()) ? result.get(0) : null;

        } catch (WebClientResponseException.NotFound ex) {
            return null;
        }
    }
}
