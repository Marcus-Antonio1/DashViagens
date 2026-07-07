package com.dashviagens.cost.service;

import java.util.List;

import com.dashviagens.common.exception.ResourceNotFoundException;
import com.dashviagens.cost.dto.CountryCostDTO;
import com.dashviagens.cost.model.CountryCost;
import com.dashviagens.cost.repository.CountryCostRepository;
import com.dashviagens.country.model.Country;
import com.dashviagens.country.service.CountryService;
import org.springframework.stereotype.Service;

/**
 * CRUD do custo medio diario por país (rotas de escrita ROLE_ADMIN).
 * Separado do TravelBudgetService: um salva dados, o outro calcula.
 */
@Service
public class CountryCostService {

    private final CountryCostRepository countryCostRepository;
    private final CountryService countryService;

    public CountryCostService(CountryCostRepository countryCostRepository, CountryService countryService) {
        this.countryCostRepository = countryCostRepository;
        this.countryService = countryService;
    }

    public List<CountryCost> findAll() {
        return countryCostRepository.findAll();
    }

    public CountryCost findByCountryCode(String countryCode) {
        return countryCostRepository.findByCountry_Code(countryCode.toUpperCase())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Custo medio não encontrado para: " + countryCode));
    }

    public CountryCost create(CountryCostDTO dto) {
        String code = dto.countryCode().toUpperCase();
        Country country = countryService.findByCode(code);

        if (countryCostRepository.findByCountry_Code(code).isPresent()) {
            throw new IllegalStateException(
                    "Pais " + code + " ja possui custo cadastrado. Use PUT para atualizar.");
        }

        return countryCostRepository.save(CountryCost.builder()
                .country(country)
                .hotelPerDay(dto.hotelPerDay())
                .foodPerDay(dto.foodPerDay())
                .transportPerDay(dto.transportPerDay())
                .activitiesPerDay(dto.activitiesPerDay())
                .estimatedFlight(dto.estimatedFlight())
                .currency(dto.currency() != null ? dto.currency() : "BRL")
                .build());
    }

    public CountryCost update(String countryCode, CountryCostDTO dto) {
        CountryCost existing = findByCountryCode(countryCode);
        if (dto.hotelPerDay() != null)       existing.setHotelPerDay(dto.hotelPerDay());
        if (dto.foodPerDay() != null)        existing.setFoodPerDay(dto.foodPerDay());
        if (dto.transportPerDay() != null)   existing.setTransportPerDay(dto.transportPerDay());
        if (dto.activitiesPerDay() != null)  existing.setActivitiesPerDay(dto.activitiesPerDay());
        if (dto.estimatedFlight() != null)   existing.setEstimatedFlight(dto.estimatedFlight());
        if (dto.currency() != null)          existing.setCurrency(dto.currency());
        return countryCostRepository.save(existing);
    }

    public void delete(String countryCode) {
        CountryCost existing = findByCountryCode(countryCode);
        countryCostRepository.delete(existing);
    }
}
