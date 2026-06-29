package com.dashviagens.country.controller;

import java.util.List;

import com.dashviagens.country.dto.CountryDTO;
import com.dashviagens.country.model.Country;
import com.dashviagens.country.service.CountryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> findAll() {
        return countryService.findAll();
    }

    @GetMapping("/{code}")
    public Country findByCode(@PathVariable String code) {
        return countryService.findByCode(code);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Country create(@Valid @RequestBody CountryDTO dto) {
        return countryService.create(dto);
    }

    // TODO: PUT e DELETE protegidos por ROLE_ADMIN (ja configurado no SecurityConfig)
}
