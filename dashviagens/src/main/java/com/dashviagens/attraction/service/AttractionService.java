package com.dashviagens.attraction.service;

import java.util.List;

import com.dashviagens.attraction.dto.AttractionDTO;
import com.dashviagens.attraction.model.Attraction;
import com.dashviagens.attraction.repository.AttractionRepository;
import com.dashviagens.common.exception.ResourceNotFoundException;
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
        return attractionRepository.findByCountry_Code(countryCode.toUpperCase());
    }

    public Attraction findById(Long id) {
        return attractionRepository.findByIdWithCountry(id)
                .orElseThrow(() -> new ResourceNotFoundException("Atracao nao encontrada: id=" + id));
    }

    public Attraction create(AttractionDTO dto) {
        Country country = countryService.findByCode(dto.countryCode());
        return attractionRepository.save(Attraction.builder()
                .country(country)
                .name(dto.name())
                .description(dto.description())
                .imageUrl(dto.imageUrl())
                .latitude(dto.latitude())
                .longitude(dto.longitude())
                .build());
    }

    public Attraction update(Long id, AttractionDTO dto) {
        Attraction existing = findById(id);
        if (dto.countryCode() != null) existing.setCountry(countryService.findByCode(dto.countryCode()));
        if (dto.name() != null)        existing.setName(dto.name());
        if (dto.description() != null) existing.setDescription(dto.description());
        if (dto.imageUrl() != null)    existing.setImageUrl(dto.imageUrl());
        if (dto.latitude() != null)    existing.setLatitude(dto.latitude());
        if (dto.longitude() != null)   existing.setLongitude(dto.longitude());
        return attractionRepository.save(existing);
    }

    public void delete(Long id) {
        if (!attractionRepository.existsById(id))
            throw new ResourceNotFoundException("Atração não encontrada: id=" + id);
        attractionRepository.deleteById(id);
    }
}
