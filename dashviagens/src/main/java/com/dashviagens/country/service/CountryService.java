package com.dashviagens.country.service;

import java.util.List;

import com.dashviagens.common.exception.ResourceNotFoundException;
import com.dashviagens.country.dto.CountryDTO;
import com.dashviagens.country.model.Country;
import com.dashviagens.country.repository.CountryRepository;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    public Country findByCode(String code) {
        return countryRepository.findByCode(code.toUpperCase())
                .orElseThrow(() -> new ResourceNotFoundException("Pais nao encontrado: " + code));
    }

    public Country create(CountryDTO dto) {
        Country country = Country.builder()
                .code(dto.code().toUpperCase())
                .name(dto.name())
                .capital(dto.capital())
                .language(dto.language())
                .currencyCode(dto.currencyCode())
                .population(dto.population())
                .timezone(dto.timezone())
                .bestSeason(dto.bestSeason())
                .bestSeasonDescription(dto.bestSeasonDescription())
                .latitude(dto.latitude())
                .longitude(dto.longitude())
                .build();

        return countryRepository.save(country);
    }

    // TODO: update(Long id, CountryDTO dto), delete(Long id)
}
