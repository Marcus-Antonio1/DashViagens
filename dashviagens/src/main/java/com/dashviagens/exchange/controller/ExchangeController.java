package com.dashviagens.exchange.controller;

import com.dashviagens.exchange.dto.ExchangeRateResponse;
import com.dashviagens.exchange.service.ExchangeService;
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

    @GetMapping("/rates")
    public ExchangeRateResponse rates(@RequestParam(defaultValue = "BRL") String base) {
        return exchangeService.getRates(base);
    }

    // TODO: GET /convert?amount=&from=&to=
}
