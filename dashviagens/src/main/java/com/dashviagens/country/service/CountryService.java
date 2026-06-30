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

    public Country findByCode(String code) {
        return countryRepository.findByCode(code.toUpperCase())
                .orElseThrow(() -> new ResourceNotFoundException("País nao encontrado: " + code));
    }

    /**
     * Cria um pais usando o CountryDTO como base.
     * Se o DTO nao trouxer capital/idioma/populacao/fuso, tenta preencher
     * automaticamente consultando a REST Countries API pelo codigo alpha-3.
     */
    public Country create(CountryDTO dto) {
        String code = dto.code().toUpperCase();

        Country.CountryBuilder builder = Country.builder()
                .code(code)
                .name(dto.name())
                .bestSeason(dto.bestSeason())
                .bestSeasonDescription(dto.bestSeasonDescription())
                .latitude(dto.latitude())
                .longitude(dto.longitude());

        // Tenta enriquecer com dados da REST Countries se os campos opcionais nao vieram no DTO
        if (dto.capital() == null || dto.language() == null || dto.population() == null) {
            RestCountryResponse remote = restCountriesClient.fetchByCode(code);

            if (remote != null) {
                builder.capital(dto.capital() != null ? dto.capital()
                        : (remote.capital() != null && !remote.capital().isEmpty() ? remote.capital().get(0) : null));

                builder.language(dto.language() != null ? dto.language()
                        : (remote.languages() != null ? remote.languages().values().stream().findFirst().orElse(null) : null));

                builder.currencyCode(dto.currencyCode() != null ? dto.currencyCode()
                        : (remote.currencies() != null ? remote.currencies().keySet().stream().findFirst().orElse(null) : null));

                builder.population(dto.population() != null ? dto.population() : remote.population());

                builder.timezone(dto.timezone() != null ? dto.timezone()
                        : (remote.timezones() != null && !remote.timezones().isEmpty() ? remote.timezones().get(0) : null));

                if (dto.latitude() == null && remote.latlng() != null && remote.latlng().size() >= 2) {
                    builder.latitude(remote.latlng().get(0));
                    builder.longitude(remote.latlng().get(1));
                }
            }
        } else {
            // Todos os campos vieram no DTO
            builder.capital(dto.capital())
                    .language(dto.language())
                    .currencyCode(dto.currencyCode())
                    .population(dto.population())
                    .timezone(dto.timezone());
        }

        return countryRepository.save(builder.build());
    }

}
