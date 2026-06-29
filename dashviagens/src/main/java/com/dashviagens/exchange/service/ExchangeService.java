package com.dashviagens.exchange.service;

import com.dashviagens.exchange.client.ExchangeRateClient;
import com.dashviagens.exchange.dto.ExchangeRateResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ExchangeService {

    private final ExchangeRateClient exchangeRateClient;

    public ExchangeService(ExchangeRateClient exchangeRateClient) {
        this.exchangeRateClient = exchangeRateClient;
    }

    @Cacheable(cacheNames = "exchangeRates", key = "#base")
    public ExchangeRateResponse getRates(String base) {
        return exchangeRateClient.fetchLatestRates(base);
    }

    // TODO: metodo convert(BigDecimal amount, String from, String to)
}
