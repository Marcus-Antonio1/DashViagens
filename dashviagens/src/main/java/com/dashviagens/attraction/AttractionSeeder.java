package com.dashviagens.attraction;

import java.util.List;

import com.dashviagens.attraction.model.Attraction;
import com.dashviagens.attraction.repository.AttractionRepository;
import com.dashviagens.country.repository.CountryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class AttractionSeeder implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(AttractionSeeder.class);

    private final AttractionRepository attractionRepository;
    private final CountryRepository    countryRepository;

    public AttractionSeeder(AttractionRepository attractionRepository,
                            CountryRepository countryRepository) {
        this.attractionRepository = attractionRepository;
        this.countryRepository    = countryRepository;
    }

    @Override
    public void run(String... args) {
        if (attractionRepository.count() > 0) {
            log.info("Atrações ja populadas seeder ignorado.");
            return;
        }

        log.info("Populando atrações turisticas...");

        seed("JPN", List.of(
                attr("Monte Fuji",          "Icônico vulcão e símbolo do Japão, sagrado para o xintoísmo.",               35.3606,  138.7274),
                attr("Templo Senso-ji",     "Templo budista do séc. VII em Asakusa, Tóquio — o mais visitado do Japão.",  35.7148,  139.7967),
                attr("Fushimi Inari",       "Santuário de Quioto famoso pelos milhares de portões torii vermelhos.",       34.9671,  135.7727),
                attr("Shibuya Crossing",    "O cruzamento de pedestres mais movimentado do mundo, em Tóquio.",             35.6595,  139.7004),
                attr("Castelo de Osaka",    "Marco histórico do séc. XVI com vista panorâmica da cidade.",                 34.6873,  135.5262)
        ));

        seed("ITA", List.of(
                attr("Coliseu",             "Anfiteatro romano do séc. I d.C., o maior já construído.",                   41.8902,   12.4922),
                attr("Basílica de São Pedro","Centro da Cidade do Vaticano com a famosa cúpula de Michelangelo.",         41.9022,   12.4539),
                attr("Canal Grande",        "Principal via aquática de Veneza, ladeada por palácios históricos.",         45.4408,   12.3155),
                attr("Costa Amalfitana",    "Litoral dramático com vilarejos coloridos e penhascos sobre o mar.",         40.6340,   14.6027),
                attr("Galeria Uffizi",      "Um dos maiores museus de arte do mundo, em Florença.",                       43.7677,   11.2553)
        ));

        seed("FRA", List.of(
                attr("Torre Eiffel",        "Símbolo de Paris, construída em 1889 para a Exposição Universal.",           48.8584,    2.2945),
                attr("Museu do Louvre",     "Maior museu de arte do mundo, lar da Mona Lisa.",                            48.8606,    2.3376),
                attr("Mont Saint-Michel",   "Ilha-mosteiro medieval na Normandia, Patrimônio da Humanidade.",             48.6361,   -1.5115),
                attr("Palácio de Versalhes","Residência real do séc. XVII com jardins imensuráveis.",                     48.8049,    2.1204),
                attr("Catedral Notre-Dame", "Obra-prima do gótico francês no coração de Paris.",                          48.8530,    2.3499)
        ));

        seed("PRT", List.of(
                attr("Torre de Belém",      "Fortaleza do séc. XVI às margens do Tejo, símbolo de Lisboa.",              38.6916,   -9.2159),
                attr("Palácio de Sintra",   "Conjunto de palácios românticos em meio à Serra de Sintra.",                38.7879,   -9.3906),
                attr("Vale do Douro",       "Região vinícola em terraços, Patrimônio da Humanidade.",                    41.1579,   -7.7941),
                attr("Algarve",             "Costa sul com praias de areias douradas e falésias imponentes.",            37.0179,   -7.9307),
                attr("Mosteiro dos Jerónimos","Obra máxima do estilo manuelino, em Belém, Lisboa.",                      38.6979,   -9.2067)
        ));

        seed("ESP", List.of(
                attr("Sagrada Família",     "Basílica inacabada de Gaudí em Barcelona, maior atração da Espanha.",       41.4036,    2.1744),
                attr("Alhambra",            "Palácio e fortaleza mouriscos em Granada, obra-prima islâmica.",            37.1761,   -3.5881),
                attr("Park Güell",          "Parque modernista de Gaudí com mosaicos e vistas de Barcelona.",            41.4145,    2.1527),
                attr("Museu do Prado",      "Principal museu espanhol com obras de Velázquez e Goya.",                   40.4138,   -3.6921),
                attr("Plaza Mayor",         "Praça histórica barroca no centro de Madri.",                               40.4154,   -3.7074)
        ));

        seed("USA", List.of(
                attr("Grand Canyon",        "Cânion de 446 km esculpido pelo Rio Colorado no Arizona.",                  36.1069, -112.1129),
                attr("Estátua da Liberdade","Símbolo de Nova York, presente da França aos EUA em 1886.",                 40.6892,  -74.0445),
                attr("Yellowstone",         "Primeiro parque nacional do mundo, com geiseres e vida selvagem.",          44.4280, -110.5885),
                attr("Times Square",        "Centro vibrante de Nova York, o 'cruzamento do mundo'.",                    40.7580,  -73.9855),
                attr("Yosemite",            "Vale glacial com cachoeiras e penhascos de granito na Califórnia.",         37.8651, -119.5383)
        ));

        seed("CAN", List.of(
                attr("Cataratas do Niágara","Conjunto de quedas d'água entre Canadá e EUA, das mais famosas do mundo.",  43.0962,  -79.0377),
                attr("Parque Nacional Banff","Montanhas rochosas e lagos de cor turquesa em Alberta.",                   51.1784, -115.5708),
                attr("CN Tower",            "Torre de comunicações de 553 m, ícone de Toronto.",                         43.6426,  -79.3871),
                attr("Cidade Velha de Quebec","Único bairro amuralhado ao norte do México, Patrimônio da Humanidade.",  46.8139,  -71.2082),
                attr("Ilha de Vancouver",   "Paisagens exuberantes com baleias, ursos e praias intocadas.",              49.6506, -125.4494)
        ));

        seed("ARG", List.of(
                attr("Cataratas do Iguaçu", "275 quedas d'água na fronteira com o Brasil, Patrimônio da Humanidade.",   -25.6953,  -54.4367),
                attr("Glaciar Perito Moreno","Imponente geleira na Patagônia que avança sobre o Lago Argentino.",       -50.4969,  -73.1442),
                attr("Bairro La Boca",      "Bairro colorido de Buenos Aires, berço do tango.",                         -34.6345,  -58.3631),
                attr("Colônia del Sacramento","Cidade histórica com ruas de paralelepípedo próxima a Buenos Aires.",    -34.4725,  -57.8490),
                attr("Península Valdés",    "Reserva natural com orcas, elefantes-marinhos e pinguins.",                -42.5000,  -64.0000)
        ));

        seed("CHL", List.of(
                attr("Torres del Paine",    "Parque nacional na Patagônia com torres de granito e lagoas.",             -50.9423,  -73.4068),
                attr("Deserto do Atacama",  "Deserto mais seco do mundo com gêiseres, salinas e céu estrelado.",       -22.9087,  -68.2000),
                attr("Ilha de Páscoa",      "Ilha remota com os misteriosos moais de pedra dos Rapa Nui.",             -27.1127, -109.3497),
                attr("Valparaíso",          "Cidade portuária com casas coloridas em morros e elevadores históricos.", -33.0472,  -71.6127),
                attr("Lago Llanquihue",     "Lago vulcânico no sul do Chile com vista para o vulcão Osorno.",          -41.1167,  -72.7833)
        ));

        seed("GRC", List.of(
                attr("Acrópole de Atenas",  "Cidadela histórica com o Partenon, símbolo da civilização grega.",         37.9715,   23.7257),
                attr("Santorini",           "Ilha vulcânica com casas brancas, cúpulas azuis e pores do sol únicos.",   36.3932,   25.4615),
                attr("Delfos",              "Sítio arqueológico do oráculo de Delfos nas encostas do monte Parnaso.",   38.4824,   22.5010),
                attr("Meteora",             "Mosteiros medievais erguidos sobre pilares de rocha na Tessália.",          39.7217,   21.6306),
                attr("Rodes",               "Cidade medieval amuralhada, Patrimônio da Humanidade.",                    36.4349,   28.2176)
        ));

        seed("THA", List.of(
                attr("Grande Palácio",      "Complexo palaciano em Bangkok, residência dos reis tailandeses.",          13.7500,  100.4913),
                attr("Ilhas Phi Phi",       "Arquipélago com águas cristalinas e paisagens de tirar o fôlego.",          7.7407,   98.7784),
                attr("Chiang Mai",          "Cidade dos templos no norte da Tailândia, porta do interior.",             18.7883,   98.9853),
                attr("Wat Arun",            "Templo do Amanhecer às margens do Rio Chao Phraya em Bangkok.",            13.7437,  100.4888),
                attr("Khao Yai",            "Floresta tropical com elefantes e cachoeiras, Patrimônio da UNESCO.",      14.4289,  101.3731)
        ));

        seed("DEU", List.of(
                attr("Portão de Brandemburgo","Símbolo da reunificação alemã e ícone de Berlim.",                       52.5163,   13.3777),
                attr("Castelo Neuschwanstein","Castelo de conto de fadas nos Alpes da Baviera.",                        47.5576,   10.7498),
                attr("Catedral de Colônia",  "Catedral gótica com 157 m, Patrimônio da Humanidade.",                   50.9413,    6.9583),
                attr("Floresta Negra",       "Região montanhosa coberta de abetos em Baden-Württemberg.",              48.0000,    8.2000),
                attr("Museu Island",         "Ilha dos museus em Berlim com cinco museus mundialmente famosos.",        52.5170,   13.4014)
        ));

        seed("GBR", List.of(
                attr("Big Ben",             "Torre do relógio do Parlamento Britânico, símbolo de Londres.",            51.5007,   -0.1246),
                attr("Buckingham Palace",   "Residência oficial da monarquia britânica no coração de Londres.",         51.5014,   -0.1419),
                attr("Stonehenge",          "Monumento pré-histórico de pedras megalíticas em Wiltshire.",             51.1789,   -1.8262),
                attr("Castelo de Edimburgo","Fortaleza histórica no topo de um rochedo vulcânico na Escócia.",         55.9486,   -3.1999),
                attr("Lago Ness",           "Famoso lago escocês associado à lenda do monstro Nessie.",                57.3229,   -4.4244)
        ));

        seed("AUS", List.of(
                attr("Ópera de Sydney",     "Obra-prima da arquitetura moderna às margens do porto de Sydney.",        -33.8568,  151.2153),
                attr("Grande Barreira de Corais","Maior sistema de recifes de coral do mundo, Patrimônio da Humanidade.",-18.2871, 147.6992),
                attr("Uluru",               "Monolito de arenito sagrado para os aborígenes no outback australiano.", -25.3444,  131.0369),
                attr("Praia de Bondi",      "Icônica praia urbana de Sydney com ondas famosas.",                      -33.8915,  151.2767),
                attr("Blue Mountains",      "Planalto com eucaliptos, cachoeiras e formações rochosas únicas.",       -33.7200,  150.3000)
        ));

        seed("MEX", List.of(
                attr("Chichén Itzá",        "Cidade maia com a Pirâmide de Kukulcán, uma das Sete Maravilhas do Mundo.", 20.6843, -88.5678),
                attr("Tulum",               "Ruínas maias à beira-mar com vista para o Caribe.",                        20.2114, -87.4654),
                attr("Teotihuacán",         "Cidade pré-colombiana com a Pirâmide do Sol próxima à CDMX.",              19.6925, -98.8438),
                attr("Cañon del Cobre",     "Sistema de cânions maior que o Grand Canyon na Sierra Tarahumara.",        27.5000,-107.5000),
                attr("Cancún",              "Resort costeiro no Caribe mexicano com praias de areia branca.",           21.1619, -86.8515)
        ));

        seed("NLD", List.of(
                attr("Keukenhof",           "Jardim com 7 milhões de tulipas, o mais famoso do mundo.",                 52.2705,    4.5469),
                attr("Casa de Anne Frank",  "Museu no esconderijo da família Frank durante a 2ª Guerra Mundial.",       52.3752,    4.8839),
                attr("Rijksmuseum",         "Principal museu da Holanda com obras de Rembrandt e Vermeer.",             52.3600,    4.8852),
                attr("Kinderdijk",          "Conjunto de 19 moinhos de vento históricos, Patrimônio da Humanidade.",   51.8833,    4.6333),
                attr("Delft",               "Cidade histórica com canais e a famosa cerâmica azul e branca.",           52.0116,    4.3571)
        ));

        seed("CHE", List.of(
                attr("Matterhorn",          "Pico piramidal de 4478 m, símbolo dos Alpes suíços.",                     45.9763,    7.6586),
                attr("Jungfrau",            "Topo da Europa com estação ferroviária a 3454 m de altitude.",             46.5472,    7.9852),
                attr("Lago Genebra",        "Maior lago dos Alpes, fronteira natural entre Suíça e França.",            46.4500,    6.6000),
                attr("Lucerna",             "Cidade medieval com a famosa Ponte de Madeira do séc. XIV.",               47.0502,    8.3093),
                attr("Château de Chillon",  "Castelo medieval às margens do Lago Genebra, inspiração de Lord Byron.",  46.4139,    6.9272)
        ));

        log.info("Atrações populadas: {} registros.", attractionRepository.count());
    }

    private void seed(String countryCode, List<Attraction> attractions) {
        countryRepository.findByCode(countryCode).ifPresentOrElse(country -> {
            attractions.forEach(a -> a.setCountry(country));
            attractionRepository.saveAll(attractions);
            log.info("  ✓ {} — {} atrações", countryCode, attractions.size());
        }, () -> log.warn("  ✗ País não encontrado: {}", countryCode));
    }

    private Attraction attr(String name, String description, Double lat, Double lng) {
        return Attraction.builder()
                .name(name)
                .description(description)
                .latitude(lat)
                .longitude(lng)
                .build();
    }
}