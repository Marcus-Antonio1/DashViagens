package com.dashviagens.cost.controller;

import com.dashviagens.cost.dto.BudgetRequest;
import com.dashviagens.cost.dto.BudgetResponse;
import com.dashviagens.cost.service.TravelBudgetService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/costs")
public class CostController {

    private final TravelBudgetService travelBudgetService;

    public CostController(TravelBudgetService travelBudgetService) {
        this.travelBudgetService = travelBudgetService;
    }

    @PostMapping("/estimate")
    public BudgetResponse estimate(@Valid @RequestBody BudgetRequest request) {
        return travelBudgetService.estimate(request);
    }

    // TODO: POST/PUT para cadastrar/editar o custo medio de um pais (ROLE_ADMIN)
}
