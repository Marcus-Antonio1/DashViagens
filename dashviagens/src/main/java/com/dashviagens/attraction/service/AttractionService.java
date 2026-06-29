package com.dashviagens.attraction.service;

import java.util.List;

import com.dashviagens.attraction.dto.AttractionDTO;
import com.dashviagens.attraction.model.Attraction;
import com.dashviagens.attraction.repository.AttractionRepository;
import com.dashviagens.country.model.Country;
import com.dashviagens.country.service.CountryService;
import org.springframework.stereotype.Service;

@Service
public class AttractionService {

    private final AttractionRepository attractionRepository;
    private final CountryService countryService;

    public AttractionService(AttractionRepository attractionRepository, CountryService countryService) {
        this.attractionRepository = attractionRepository;
        this.countryService = countryService;
    }

    public List<Attraction> findByCountryCode(String countryCode) {
        return attractionRepository.findByCountryCode(countryCode.toUpperCase());
    }

    public Attraction create(AttractionDTO dto) {
        Country country = countryService.findByCode(dto.countryCode());

        Attraction attraction = Attraction.builder()
                .country(country)
                .name(dto.name())
                .description(dto.description())
                .imageUrl(dto.imageUrl())
                .latitude(dto.latitude())
                .longitude(dto.longitude())
                .build();

        return attractionRepository.save(attraction);
    }

    // TODO: update(Long id, AttractionDTO dto), delete(Long id)
}
