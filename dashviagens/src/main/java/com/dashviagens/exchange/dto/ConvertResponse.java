package com.dashviagens.exchange.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ConvertResponse(
        BigDecimal amount,
        String from,
        String to,
        BigDecimal result,
        LocalDate date
) {}
