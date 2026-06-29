package com.dashviagens.attraction.controller;

import java.util.List;

import com.dashviagens.attraction.dto.AttractionDTO;
import com.dashviagens.attraction.model.Attraction;
import com.dashviagens.attraction.service.AttractionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attractions")
public class AttractionController {

    private final AttractionService attractionService;

    public AttractionController(AttractionService attractionService) {
        this.attractionService = attractionService;
    }

    @GetMapping
    public List<Attraction> byCountry(@RequestParam String countryCode) {
        return attractionService.findByCountryCode(countryCode);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Attraction create(@Valid @RequestBody AttractionDTO dto) {
        return attractionService.create(dto);
    }

    // TODO: PUT e DELETE protegidos por ROLE_ADMIN
}
