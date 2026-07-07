package com.dashviagens.attraction.controller;

import java.util.List;

import com.dashviagens.attraction.dto.AttractionDTO;
import com.dashviagens.attraction.dto.AttractionResponse;
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
    public List<AttractionResponse> byCountry(@RequestParam String countryCode) {
        return attractionService.findByCountryCode(countryCode)
                .stream().map(AttractionResponse::from).toList();
    }

    @GetMapping("/{id}")
    public AttractionResponse findById(@PathVariable Long id) {
        return AttractionResponse.from(attractionService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AttractionResponse create(@Valid @RequestBody AttractionDTO dto) {
        return AttractionResponse.from(attractionService.create(dto));
    }

    @PutMapping("/{id}")
    public AttractionResponse update(@PathVariable Long id, @RequestBody AttractionDTO dto) {
        return AttractionResponse.from(attractionService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        attractionService.delete(id);
    }
}
