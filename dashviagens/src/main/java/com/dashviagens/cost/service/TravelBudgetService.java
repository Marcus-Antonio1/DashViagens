package com.dashviagens.cost.service;

import java.math.BigDecimal;

import com.dashviagens.common.exception.ResourceNotFoundException;
import com.dashviagens.cost.dto.BudgetRequest;
import com.dashviagens.cost.dto.BudgetResponse;
import com.dashviagens.cost.model.CountryCost;
import com.dashviagens.cost.repository.CountryCostRepository;
import org.springframework.stereotype.Service;

/**
 * Calculadora de viagem (v1 - soma simples).
 * v2 (futuro): sugerir ajustes quando o orcamento for insuficiente.
 */
@Service
public class TravelBudgetService {

    private final CountryCostRepository countryCostRepository;

    public TravelBudgetService(CountryCostRepository countryCostRepository) {
        this.countryCostRepository = countryCostRepository;
    }

    public BudgetResponse estimate(BudgetRequest request) {
        CountryCost cost = countryCostRepository.findByCountryCode(request.countryCode().toUpperCase())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Custo medio nao cadastrado para: " + request.countryCode()));

        BigDecimal days = BigDecimal.valueOf(request.days());

        BigDecimal hotel = cost.getHotelPerDay().multiply(days);
        BigDecimal food = cost.getFoodPerDay().multiply(days);
        BigDecimal transport = cost.getTransportPerDay().multiply(days);
        BigDecimal activities = cost.getActivitiesPerDay().multiply(days);
        BigDecimal flight = cost.getEstimatedFlight() != null ? cost.getEstimatedFlight() : BigDecimal.ZERO;

        BigDecimal total = hotel.add(food).add(transport).add(activities).add(flight);
        BigDecimal remaining = request.totalBudget().subtract(total);

        return new BudgetResponse(
                request.countryCode().toUpperCase(),
                request.days(),
                flight,
                hotel,
                food,
                transport,
                activities,
                total,
                request.totalBudget(),
                remaining,
                remaining.signum() >= 0
        );
    }
}
