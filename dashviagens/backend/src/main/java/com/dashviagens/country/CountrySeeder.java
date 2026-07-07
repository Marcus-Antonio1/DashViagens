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
            log.info("Paises ja populados — seeder ignorado.");
            return;
        }
        log.info("Populando paises via countries.dev...");

        List<CountryDTO> seeds = List.of(
                country("JPN", "Japão",         "Primavera", "Março a maio — sakura em flor, clima ameno e festivais tradicionais",
                        "https://images.unsplash.com/photo-1493976040374-85c8e12f0c0e?w=800&q=80"),
                country("ITA", "Itália",         "Primavera", "Abril a junho — clima ideal, sem o calor intenso do verão",
                        "https://images.unsplash.com/photo-1515542622106-078bda69bcb3?w=800&q=80"),
                country("FRA", "França",         "Primavera", "Abril a junho — Paris em flor, filas menores nos museus",
                        "https://images.unsplash.com/photo-1499856871958-5b9627545d1a?w=800&q=80"),
                country("PRT", "Portugal",       "Primavera", "Março a maio — clima agradável e preços mais baixos",
                        "https://images.unsplash.com/photo-1555881400-74d7acaacd8b?w=800&q=80"),
                country("ESP", "Espanha",        "Primavera", "Abril a junho — perfeito antes do verão lotado",
                        "https://images.unsplash.com/photo-1543783207-ec64e4d95325?w=800&q=80"),
                country("USA", "EUA",            "Outono",   "Setembro a novembro — folhagem colorida e clima agradável",
                        "https://images.unsplash.com/photo-1501466044931-62695aada8e9?w=800&q=80"),
                country("CAN", "Canadá",         "Verão",    "Junho a agosto — dias longos e parques nacionais acessíveis",
                        "https://images.unsplash.com/photo-1503614472-8c93d56e92ce?w=800&q=80"),
                country("ARG", "Argentina",      "Outono",   "Março a maio — Buenos Aires com temperatura ideal",
                        "https://images.unsplash.com/photo-1612294037637-ec328d0e075e?w=800&q=80"),
                country("CHL", "Chile",          "Verão",    "Dezembro a fevereiro — Patagônia e Atacama acessíveis",
                        "https://images.unsplash.com/photo-1478827536114-da961b7f86d2?w=800&q=80"),
                country("GRC", "Grécia",         "Primavera","Maio a junho — ilhas com clima bom antes da alta temporada",
                        "https://images.unsplash.com/photo-1533105079780-92b9be482077?w=800&q=80"),
                country("THA", "Tailândia",      "Seca",     "Novembro a fevereiro — calor seco, menos chuvas",
                        "https://images.unsplash.com/photo-1528181304800-259b08848526?w=800&q=80"),
                country("DEU", "Alemanha",       "Verão",    "Junho a agosto — festivais, castelos e clima agradável",
                        "https://images.unsplash.com/photo-1467269204594-9661b134dd2b?w=800&q=80"),
                country("GBR", "Reino Unido",    "Verão",    "Junho a agosto — a época mais ensolarada do ano",
                        "https://images.unsplash.com/photo-1513635269975-59663e0ac1ad?w=800&q=80"),
                country("AUS", "Austrália",      "Primavera","Setembro a novembro — clima ameno antes do verão intenso",
                        "https://images.unsplash.com/photo-1506973035872-a4ec16b8e8d9?w=800&q=80"),
                country("MEX", "México",         "Seca",     "Dezembro a abril — praias cristalinas e sítios arqueológicos",
                        "https://images.unsplash.com/photo-1518638150340-f706e86654de?w=800&q=80"),
                country("NLD", "Países Baixos",  "Primavera","Abril a maio — tulipas em flor e dias iluminados",
                        "https://images.unsplash.com/photo-1534351590666-13e3e96b5017?w=800&q=80"),
                country("CHE", "Suíça",          "Verão",    "Junho a agosto — trilhas alpinas e lagos de tirar o fôlego",
                        "https://images.unsplash.com/photo-1506905925346-21bda4d32df4?w=800&q=80")
        );

        int sucesso = 0, falha = 0;
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

    private CountryDTO country(String code, String name, String bestSeason,
                               String bestSeasonDesc, String imageUrl) {
        return new CountryDTO(
                null, code, name,
                null, null, null, null, null,
                bestSeason, bestSeasonDesc,
                null, null,
                imageUrl
        );
    }
}
