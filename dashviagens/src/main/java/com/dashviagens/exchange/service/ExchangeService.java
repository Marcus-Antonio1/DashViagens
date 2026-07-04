package com.dashviagens.exchange.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.stream.Collectors;

import com.dashviagens.exchange.client.ExchangeRateClient;
import com.dashviagens.exchange.dto.ConvertResponse;
import com.dashviagens.exchange.dto.ExchangeRateResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ExchangeService {

    private static final int SCALE = 6;

    private final ExchangeRateClient exchangeRateClient;

    public ExchangeService(ExchangeRateClient exchangeRateClient) {
        this.exchangeRateClient = exchangeRateClient;
    }


    @Cacheable(cacheNames = "exchangeRates", key = "'BRL'")
    public ExchangeRateResponse getRates() {
        ExchangeRateResponse raw = exchangeRateClient.fetchLatestRates("BRL");

        Map<String, BigDecimal> ratesAgainstBrl = raw.rates().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> BigDecimal.ONE.divide(e.getValue(), SCALE, RoundingMode.HALF_UP)
                ));

        return new ExchangeRateResponse(raw.base(), raw.date(), ratesAgainstBrl);
    }


    public ConvertResponse convert(BigDecimal amount, String from, String to) {
        String f = from.toUpperCase();
        String t = to.toUpperCase();

        ExchangeRateResponse rates = getRates();
        BigDecimal result;

        if (f.equals(t)) {
            result = amount;
        } else if ("BRL".equals(f)) {
            result = amount.divide(getRate(rates, t), 2, RoundingMode.HALF_UP);
        } else if ("BRL".equals(t)) {
            result = amount.multiply(getRate(rates, f)).setScale(2, RoundingMode.HALF_UP);
        } else {
            BigDecimal rateFrom = getRate(rates, f);
            BigDecimal rateTo   = getRate(rates, t);
            result = amount.multiply(rateFrom).divide(rateTo, 2, RoundingMode.HALF_UP);
        }

        return new ConvertResponse(amount, f, t, result, rates.date());
    }

    private BigDecimal getRate(ExchangeRateResponse rates, String currency) {
        BigDecimal rate = rates.rates().get(currency);
        if (rate == null) throw new IllegalArgumentException("Moeda nao suportada: " + currency);
        return rate;
    }
}
