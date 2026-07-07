package com.dashviagens.exchange.client;

import com.dashviagens.exchange.dto.ExchangeRateResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/*
 * Integração com a Frankfurter API (https://www.frankfurter.app) para cotações.
 */
@Component
public class ExchangeRateClient {

    private final WebClient webClient;

    public ExchangeRateClient(WebClient.Builder webClientBuilder,
                              @Value("${dashviagens.external-apis.exchange-rate-base-url}") String baseUrl) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    public ExchangeRateResponse fetchLatestRates(String base) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/latest")
                        .queryParam("base", base)
                        .build())
                .retrieve()
                .bodyToMono(ExchangeRateResponse.class)
                .block();
    }
}
