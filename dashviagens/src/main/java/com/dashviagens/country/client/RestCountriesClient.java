package com.dashviagens.country.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Integra com a REST Countries API (https://restcountries.com) para
 * preencher capital, idioma, populacao e fuso horario na primeira carga
 * de um pais. TODO: implementar fetchByCode(String alpha3Code).
 */
@Component
public class RestCountriesClient {

    private final WebClient webClient;

    public RestCountriesClient(WebClient.Builder webClientBuilder,
                                @Value("${dashviagens.external-apis.rest-countries-base-url}") String baseUrl) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }
}
