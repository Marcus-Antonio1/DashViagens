package com.dashviagens.exchange.controller;

import java.math.BigDecimal;

import com.dashviagens.exchange.dto.ConvertResponse;
import com.dashviagens.exchange.dto.ExchangeRateResponse;
import com.dashviagens.exchange.service.ExchangeService;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exchange")
public class ExchangeController {

    private final ExchangeService exchangeService;

    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    /** GET /api/exchange/rates — cotações em BRL */
    @GetMapping("/rates")
    public ExchangeRateResponse rates() {
        return exchangeService.getRates();
    }

    /** GET /api/exchange/convert?amount=5000&from=BRL&to=USD */
    @GetMapping("/convert")
    public ConvertResponse convert(
            @RequestParam @Positive BigDecimal amount,
            @RequestParam String from,
            @RequestParam String to) {
        return exchangeService.convert(amount, from, to);
    }
}
