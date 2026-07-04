package com.dashviagens.country.service;

import java.util.List;

import com.dashviagens.common.exception.ResourceNotFoundException;
import com.dashviagens.country.client.RestCountriesClient;
import com.dashviagens.country.dto.CountryDTO;
import com.dashviagens.country.dto.RestCountryResponse;
import com.dashviagens.country.model.Country;
import com.dashviagens.country.repository.CountryRepository;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    private final CountryRepository countryRepository;
    private final RestCountriesClient restCountriesClient;

    public CountryService(CountryRepository countryRepository, RestCountriesClient restCountriesClient) {
        this.countryRepository = countryRepository;
        this.restCountriesClient = restCountriesClient;
    }

    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    public Country findById(Long id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pais nao encontrado: id=" + id));
    }

    public Country findByCode(String code) {
        return countryRepository.findByCode(code.toUpperCase())
                .orElseThrow(() -> new ResourceNotFoundException("Pais nao encontrado: " + code));
    }

    public Country create(CountryDTO dto) {
        String code = dto.code().toUpperCase();
        Country.CountryBuilder builder = Country.builder()
                .code(code)
                .name(dto.name())
                .bestSeason(dto.bestSeason())
                .bestSeasonDescription(dto.bestSeasonDescription())
                .latitude(dto.latitude())
                .longitude(dto.longitude());

        enrich(builder, code, dto);
        return countryRepository.save(builder.build());
    }

    public Country update(Long id, CountryDTO dto) {
        Country existing = findById(id);
        if (dto.name() != null)                  existing.setName(dto.name());
        if (dto.capital() != null)               existing.setCapital(dto.capital());
        if (dto.language() != null)              existing.setLanguage(dto.language());
        if (dto.currencyCode() != null)          existing.setCurrencyCode(dto.currencyCode());
        if (dto.population() != null)            existing.setPopulation(dto.population());
        if (dto.timezone() != null)              existing.setTimezone(dto.timezone());
        if (dto.bestSeason() != null)            existing.setBestSeason(dto.bestSeason());
        if (dto.bestSeasonDescription() != null) existing.setBestSeasonDescription(dto.bestSeasonDescription());
        if (dto.latitude() != null)              existing.setLatitude(dto.latitude());
        if (dto.longitude() != null)             existing.setLongitude(dto.longitude());
        return countryRepository.save(existing);
    }

    public void delete(Long id) {
        if (!countryRepository.existsById(id))
            throw new ResourceNotFoundException("Pais nao encontrado: id=" + id);
        countryRepository.deleteById(id);
    }

    // --- helpers ---

    private void enrich(Country.CountryBuilder builder, String code, CountryDTO dto) {
        if (dto.capital() != null && dto.language() != null && dto.population() != null) {
            builder.capital(dto.capital()).language(dto.language())
                    .currencyCode(dto.currencyCode()).population(dto.population()).timezone(dto.timezone());
            return;
        }

        RestCountryResponse remote = restCountriesClient.fetchByCode(code);
        if (remote == null) return;

        builder.capital(firstNonNull(dto.capital(),
                remote.capital() != null && !remote.capital().isEmpty() ? remote.capital().get(0) : null));
        builder.language(firstNonNull(dto.language(),
                remote.languages() != null ? remote.languages().values().stream().findFirst().orElse(null) : null));
        builder.currencyCode(firstNonNull(dto.currencyCode(),
                remote.currencies() != null ? remote.currencies().keySet().stream().findFirst().orElse(null) : null));
        builder.population(firstNonNull(dto.population(), remote.population()));
        builder.timezone(firstNonNull(dto.timezone(),
                remote.timezones() != null && !remote.timezones().isEmpty() ? remote.timezones().get(0) : null));

        if (dto.latitude() == null && remote.latlng() != null && remote.latlng().size() >= 2) {
            builder.latitude(remote.latlng().get(0)).longitude(remote.latlng().get(1));
        }
    }

    private <T> T firstNonNull(T a, T b) { return a != null ? a : b; }
}
