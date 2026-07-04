package com.dashviagens.cost.controller;

import java.util.List;

import com.dashviagens.cost.dto.BudgetRequest;
import com.dashviagens.cost.dto.BudgetResponse;
import com.dashviagens.cost.dto.CountryCostDTO;
import com.dashviagens.cost.dto.CountryCostResponse;
import com.dashviagens.cost.service.CountryCostService;
import com.dashviagens.cost.service.TravelBudgetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/costs")
public class CostController {

    private final TravelBudgetService travelBudgetService;
    private final CountryCostService countryCostService;

    public CostController(TravelBudgetService travelBudgetService, CountryCostService countryCostService) {
        this.travelBudgetService = travelBudgetService;
        this.countryCostService = countryCostService;
    }

    @GetMapping
    public List<CountryCostResponse> findAll() {
        return countryCostService.findAll().stream().map(CountryCostResponse::from).toList();
    }

    @GetMapping("/{countryCode}")
    public CountryCostResponse findByCountryCode(@PathVariable String countryCode) {
        return CountryCostResponse.from(countryCostService.findByCountryCode(countryCode));
    }

    @PostMapping("/estimate")
    public BudgetResponse estimate(@Valid @RequestBody BudgetRequest request) {
        return travelBudgetService.estimate(request);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CountryCostResponse create(@Valid @RequestBody CountryCostDTO dto) {
        return CountryCostResponse.from(countryCostService.create(dto));
    }

    @PutMapping("/{countryCode}")
    public CountryCostResponse update(@PathVariable String countryCode, @RequestBody CountryCostDTO dto) {
        return CountryCostResponse.from(countryCostService.update(countryCode, dto));
    }

    @DeleteMapping("/{countryCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String countryCode) {
        countryCostService.delete(countryCode);
    }
}
