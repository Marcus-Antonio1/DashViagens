package com.dashviagens.exchange.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

/**
 * Resposta normalizada das cotacoes. "rates" usa BRL como referencia,
 * ex: { "USD": 5.42, "EUR": 6.18 }
 */
public record ExchangeRateResponse(
        String base,
        LocalDate date,
        Map<String, BigDecimal> rates
) {
}
