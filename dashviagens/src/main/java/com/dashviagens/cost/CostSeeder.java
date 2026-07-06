package com.dashviagens.cost;

import java.math.BigDecimal;
import java.util.List;

import com.dashviagens.cost.model.CountryCost;
import com.dashviagens.cost.repository.CountryCostRepository;
import com.dashviagens.country.repository.CountryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
public class CostSeeder implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(CostSeeder.class);

    private final CountryCostRepository costRepository;
    private final CountryRepository     countryRepository;

    public CostSeeder(CountryCostRepository costRepository,
                      CountryRepository countryRepository) {
        this.costRepository    = costRepository;
        this.countryRepository = countryRepository;
    }

    private record CostSeed(
            String code,
            int hotel, int food, int transport, int activities, int flight
    ) {}

    @Override
    public void run(String... args) {
        if (costRepository.count() > 0) {
            log.info("Custos ja populados seeder ignorado.");
            return;
        }

        log.info("Populando custos médios por País...");

        List<CostSeed> seeds = List.of(
                new CostSeed("JPN",  580, 180, 120, 200, 6000),
                new CostSeed("ITA",  620, 220, 100, 180, 5500),
                new CostSeed("FRA",  700, 250, 120, 200, 5500),
                new CostSeed("PRT",  380, 160,  80, 120, 4500),
                new CostSeed("ESP",  450, 180,  90, 150, 5000),
                new CostSeed("USA",  650, 280, 150, 250, 5000),
                new CostSeed("CAN",  580, 250, 120, 200, 5500),
                new CostSeed("ARG",  200, 100,  50,  80, 3000),
                new CostSeed("CHL",  320, 140,  70, 120, 3500),
                new CostSeed("GRC",  450, 180,  80, 150, 5000),
                new CostSeed("THA",  280, 100,  60, 120, 5500),
                new CostSeed("DEU",  550, 220, 110, 180, 5000),
                new CostSeed("GBR",  700, 280, 150, 220, 5500),
                new CostSeed("AUS",  650, 280, 140, 220, 8000),
                new CostSeed("MEX",  380, 150,  70, 130, 3500),
                new CostSeed("NLD",  600, 230, 100, 180, 5000),
                new CostSeed("CHE",  900, 350, 180, 250, 5500)
        );

        for (CostSeed s : seeds) {
            countryRepository.findByCode(s.code()).ifPresentOrElse(
                    country -> {
                        CountryCost cost = CountryCost.builder()
                                .country(country)
                                .hotelPerDay(bd(s.hotel()))
                                .foodPerDay(bd(s.food()))
                                .transportPerDay(bd(s.transport()))
                                .activitiesPerDay(bd(s.activities()))
                                .estimatedFlight(bd(s.flight()))
                                .currency("BRL")
                                .build();
                        costRepository.save(cost);
                        log.info("  ✓ {}", s.code());
                    },
                    () -> log.warn("  ✗ País não encontrado: {}", s.code())
            );
        }

        log.info("Custos populados: {} registros.", costRepository.count());
    }

    private BigDecimal bd(int value) {
        return BigDecimal.valueOf(value);
    }
}