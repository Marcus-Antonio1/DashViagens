package com.dashviagens.country;

import java.util.List;

import com.dashviagens.country.dto.CountryDTO;
import com.dashviagens.country.repository.CountryRepository;
import com.dashviagens.country.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Popula automaticamente os paises na primeira inicializacao.
 *
 * So executa se a tabela "countries" estiver vazia.
 * Os dados basicos (capital, idioma, moeda, populacao, fuso, lat/lng)
 * são buscados automaticamente na countries.dev so é necessario
 * informar o codigo alpha-3 e o nome do país.
 *
 * Os campos integrados manualmente (bestSeason, bestSeasonDescription)
 * são informados aqui pois não existem em nenhuma API publica.
 *
 * @Order(2): roda depois do AdminBootstrap (Order 1 implicito).
 */
@Component
@Order(2)
public class CountrySeeder implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(CountrySeeder.class);

    private final CountryRepository countryRepository;
    private final CountryService    countryService;

    public CountrySeeder(CountryRepository countryRepository, CountryService countryService) {
        this.countryRepository = countryRepository;
        this.countryService    = countryService;
    }

    @Override
    public void run(String... args) {
        if (countryRepository.count() > 0) {
            log.info("Paises ja populados seeder ignorado.");
            return;
        }

        log.info("Populando paises iniciais via countries.dev...");

        List<CountryDTO> seeds = List.of(
                country("JPN", "Japão",          "Primavera",  "Março a maio — sakura em flor, clima ameno e festivais tradicionais"),
                country("ITA", "Itália",          "Primavera",  "Abril a junho — clima ideal, sem o calor intenso do verão"),
                country("FRA", "França",          "Primavera",  "Abril a junho — Paris em flor, filas menores nos museus"),
                country("PRT", "Portugal",        "Primavera",  "Março a maio — clima agradável e preços mais baixos"),
                country("ESP", "Espanha",         "Primavera",  "Abril a junho — perfeito antes do verão lotado"),
                country("USA", "EUA",             "Outono",     "Setembro a novembro — folhagem colorida e clima agradável"),
                country("CAN", "Canadá",          "Verão",      "Junho a agosto — dias longos e parques nacionais acessíveis"),
                country("ARG", "Argentina",       "Outono",     "Março a maio — Buenos Aires com temperatura ideal"),
                country("CHL", "Chile",           "Verão",      "Dezembro a fevereiro — Patagônia e Atacama acessíveis"),
                country("GRC", "Grécia",          "Primavera",  "Maio a junho — ilhas com clima bom antes da alta temporada"),
                country("THA", "Tailândia",       "Seca",       "Novembro a fevereiro — calor seco, menos chuvas"),
                country("DEU", "Alemanha",        "Verão",      "Junho a agosto — festivais, castelos e clima agradável"),
                country("GBR", "Reino Unido",     "Verão",      "Junho a agosto — a época mais ensolarada do ano"),
                country("AUS", "Austrália",       "Primavera",  "Setembro a novembro — clima ameno antes do verão intenso"),
                country("MEX", "México",          "Seca",       "Dezembro a abril — praias cristalinas e sítios arqueológicos"),
                country("NLD", "Países Baixos",   "Primavera",  "Abril a maio — tulipas em flor e dias iluminados"),
                country("CHE", "Suíça",           "Verão",      "Junho a agosto — trilhas alpinas e lagos de tirar o fôlego")
        );

        int sucesso = 0;
        int falha   = 0;

        for (CountryDTO dto : seeds) {
            try {
                countryService.create(dto);
                log.info("  ✓ {}", dto.name());
                sucesso++;
            } catch (Exception ex) {
                log.warn("  ✗ {} — {}", dto.name(), ex.getMessage());
                falha++;
            }
        }

        log.info("Seeder concluido: {} importados, {} com falha.", sucesso, falha);
    }

    /**
     * Cria um DTO com apenas os campos
     * capital, language, currencyCode, population, timezone e latlng
     * serão preenchidos automaticamente pelo CountryService via countries.dev.
     */
    private CountryDTO country(String code, String name, String bestSeason, String bestSeasonDescription) {
        return new CountryDTO(
                null,                           // id — ignorado na criacao
                code, name,
                null, null, null, null, null,   // capital/lang/currency/pop/tz — via countries.dev
                bestSeason, bestSeasonDescription,
                null, null                      // lat/lng — via countries.dev
        );
    }
}