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
                attr("Monte Fuji",       "Icônico vulcão e símbolo do Japão, sagrado para o xintoísmo.",               35.3606,  138.7274,
                        "https://images.unsplash.com/photo-1490806843957-31f4c9a91c65?w=600&q=80"),
                attr("Templo Senso-ji",  "Templo budista do séc. VII em Asakusa, o mais visitado do Japão.",           35.7148,  139.7967,
                        "https://images.unsplash.com/photo-1570459027562-4a916cc6113f?w=600&q=80"),
                attr("Fushimi Inari",    "Santuário de Quioto com milhares de portões torii vermelhos.",               34.9671,  135.7727,
                        "https://images.unsplash.com/photo-1478436127897-769e1b3f0f36?w=600&q=80"),
                attr("Shibuya Crossing", "O cruzamento de pedestres mais movimentado do mundo, em Tóquio.",            35.6595,  139.7004,
                        "https://images.unsplash.com/photo-1542051841857-5f90071e7989?w=600&q=80"),
                attr("Castelo de Osaka", "Marco histórico do séc. XVI com vista panorâmica da cidade.",                34.6873,  135.5262,
                        "https://images.unsplash.com/photo-1589308078059-be1415eab4c3?w=600&q=80")
        ));

        seed("ITA", List.of(
                attr("Coliseu",              "Anfiteatro romano do séc. I d.C., o maior já construído.",               41.8902,  12.4922,
                        "https://images.unsplash.com/photo-1552832230-c0197dd311b5?w=600&q=80"),
                attr("Basílica de São Pedro","Centro da Cidade do Vaticano com a cúpula de Michelangelo.",             41.9022,  12.4539,
                        "https://images.unsplash.com/photo-1531572753322-ad063cecc140?w=600&q=80"),
                attr("Canal Grande",         "Principal via aquática de Veneza, ladeada por palácios históricos.",     45.4408,  12.3155,
                        "https://images.unsplash.com/photo-1534113414509-0eec2bfb493f?w=600&q=80"),
                attr("Costa Amalfitana",     "Litoral dramático com vilarejos coloridos sobre o mar.",                 40.6340,  14.6027,
                        "https://images.unsplash.com/photo-1557800636-894a64c1696f?w=600&q=80"),
                attr("Galeria Uffizi",       "Um dos maiores museus de arte do mundo, em Florença.",                   43.7677,  11.2553,
                        "https://images.unsplash.com/photo-1541370976299-4d24ebbc9077?w=600&q=80")
        ));

        seed("FRA", List.of(
                attr("Torre Eiffel",         "Símbolo de Paris, construída em 1889 para a Exposição Universal.",       48.8584,    2.2945,
                        "https://images.unsplash.com/photo-1511739001486-6bfe10ce785f?w=600&q=80"),
                attr("Museu do Louvre",      "Maior museu de arte do mundo, lar da Mona Lisa.",                        48.8606,    2.3376,
                        "https://images.unsplash.com/photo-1565799557186-4fd5cbb9d5e7?w=600&q=80"),
                attr("Mont Saint-Michel",    "Ilha-mosteiro medieval na Normandia, Patrimônio da Humanidade.",         48.6361,   -1.5115,
                        "https://images.unsplash.com/photo-1589149098258-3e9102cd63d3?w=600&q=80"),
                attr("Palácio de Versalhes", "Residência real do séc. XVII com jardins imensuráveis.",                 48.8049,    2.1204,
                        "https://images.unsplash.com/photo-1573843981267-be1fb51deff5?w=600&q=80"),
                attr("Catedral Notre-Dame",  "Obra-prima do gótico francês no coração de Paris.",                      48.8530,    2.3499,
                        "https://images.unsplash.com/photo-1546268060-2592ff93ee24?w=600&q=80")
        ));

        seed("PRT", List.of(
                attr("Torre de Belém",         "Fortaleza do séc. XVI às margens do Tejo, símbolo de Lisboa.",         38.6916,  -9.2159,
                        "https://images.unsplash.com/photo-1588492069485-d05b56b2831d?w=600&q=80"),
                attr("Palácio de Sintra",      "Conjunto de palácios românticos em meio à Serra de Sintra.",           38.7879,  -9.3906,
                        "https://images.unsplash.com/photo-1555881400-74d7acaacd8b?w=600&q=80"),
                attr("Vale do Douro",          "Região vinícola em terraços, Patrimônio da Humanidade.",               41.1579,  -7.7941,
                        "https://images.unsplash.com/photo-1555881400-74d7acaacd8b?w=600&q=80"),
                attr("Algarve",                "Costa sul com praias de areias douradas e falésias imponentes.",       37.0179,  -7.9307,
                        "https://images.unsplash.com/photo-1561016444-14f747499547?w=600&q=80"),
                attr("Mosteiro dos Jerónimos", "Obra máxima do estilo manuelino, em Belém, Lisboa.",                   38.6979,  -9.2067,
                        "https://images.unsplash.com/photo-1588492069485-d05b56b2831d?w=600&q=80")
        ));

        seed("ESP", List.of(
                attr("Sagrada Família",  "Basílica inacabada de Gaudí em Barcelona, maior atração da Espanha.",        41.4036,    2.1744,
                        "https://images.unsplash.com/photo-1583779457094-ab6f77f7bf57?w=600&q=80"),
                attr("Alhambra",         "Palácio e fortaleza mouriscos em Granada, obra-prima islâmica.",             37.1761,   -3.5881,
                        "https://images.unsplash.com/photo-1558642891-54be180ea339?w=600&q=80"),
                attr("Park Güell",       "Parque modernista de Gaudí com mosaicos e vistas de Barcelona.",             41.4145,    2.1527,
                        "https://images.unsplash.com/photo-1591775995956-eb96f25a1aac?w=600&q=80"),
                attr("Museu do Prado",   "Principal museu espanhol com obras de Velázquez e Goya.",                    40.4138,   -3.6921,
                        "https://images.unsplash.com/photo-1543783207-ec64e4d95325?w=600&q=80"),
                attr("Plaza Mayor",      "Praça histórica barroca no centro de Madri.",                                40.4154,   -3.7074,
                        "https://images.unsplash.com/photo-1543783207-ec64e4d95325?w=600&q=80")
        ));

        seed("USA", List.of(
                attr("Grand Canyon",          "Cânion de 446 km esculpido pelo Rio Colorado no Arizona.",              36.1069, -112.1129,
                        "https://images.unsplash.com/photo-1509316785289-025f5b846b35?w=600&q=80"),
                attr("Estátua da Liberdade",  "Símbolo de Nova York, presente da França aos EUA em 1886.",             40.6892,  -74.0445,
                        "https://images.unsplash.com/photo-1485738422979-f5c462d49f74?w=600&q=80"),
                attr("Yellowstone",           "Primeiro parque nacional do mundo, com geiseres e vida selvagem.",      44.4280, -110.5885,
                        "https://images.unsplash.com/photo-1504198266287-1659872e6590?w=600&q=80"),
                attr("Times Square",          "Centro vibrante de Nova York, o 'cruzamento do mundo'.",                40.7580,  -73.9855,
                        "https://images.unsplash.com/photo-1534430480872-3498386e7856?w=600&q=80"),
                attr("Yosemite",              "Vale glacial com cachoeiras e penhascos de granito na Califórnia.",     37.8651, -119.5383,
                        "https://images.unsplash.com/photo-1472396961693-142e6e269027?w=600&q=80")
        ));

        seed("CAN", List.of(
                attr("Cataratas do Niágara",  "Conjunto de quedas d'água entre Canadá e EUA.",                        43.0962,  -79.0377,
                        "https://images.unsplash.com/photo-1489447068241-b3490214e879?w=600&q=80"),
                attr("Parque Nacional Banff", "Montanhas rochosas e lagos de cor turquesa em Alberta.",               51.1784, -115.5708,
                        "https://images.unsplash.com/photo-1503614472-8c93d56e92ce?w=600&q=80"),
                attr("CN Tower",              "Torre de comunicações de 553 m, ícone de Toronto.",                    43.6426,  -79.3871,
                        "https://images.unsplash.com/photo-1617360668537-27a44a5e0e93?w=600&q=80"),
                attr("Cidade Velha de Quebec","Único bairro amuralhado ao norte do México, Patrimônio da Humanidade.",46.8139,  -71.2082,
                        "https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=600&q=80"),
                attr("Ilha de Vancouver",     "Paisagens exuberantes com baleias, ursos e praias intocadas.",         49.6506, -125.4494,
                        "https://images.unsplash.com/photo-1508693926297-1d61ee3df82a?w=600&q=80")
        ));

        seed("GRC", List.of(
                attr("Acrópole de Atenas", "Cidadela histórica com o Partenon, símbolo da civilização grega.",        37.9715,  23.7257,
                        "https://images.unsplash.com/photo-1555993539-1732b0258235?w=600&q=80"),
                attr("Santorini",          "Ilha vulcânica com casas brancas, cúpulas azuis e pores do sol únicos.",  36.3932,  25.4615,
                        "https://images.unsplash.com/photo-1570077188670-e3a8d69ac5ff?w=600&q=80"),
                attr("Delfos",             "Sítio arqueológico do oráculo nas encostas do monte Parnaso.",            38.4824,  22.5010,
                        "https://images.unsplash.com/photo-1533105079780-92b9be482077?w=600&q=80"),
                attr("Meteora",            "Mosteiros medievais erguidos sobre pilares de rocha na Tessália.",        39.7217,  21.6306,
                        "https://images.unsplash.com/photo-1586861203927-800a5acdda4d?w=600&q=80"),
                attr("Rodes",              "Cidade medieval amuralhada, Patrimônio da Humanidade.",                   36.4349,  28.2176,
                        "https://images.unsplash.com/photo-1533105079780-92b9be482077?w=600&q=80")
        ));

        seed("THA", List.of(
                attr("Grande Palácio",   "Complexo palaciano em Bangkok, residência histórica dos reis tailandeses.", 13.7500,  100.4913,
                        "https://images.unsplash.com/photo-1563492065599-3520f775eeed?w=600&q=80"),
                attr("Ilhas Phi Phi",    "Arquipélago com águas cristalinas e paisagens deslumbrantes.",               7.7407,   98.7784,
                        "https://images.unsplash.com/photo-1537953773345-d172ccf13cf4?w=600&q=80"),
                attr("Chiang Mai",       "Cidade dos templos no norte, porta do interior da Tailândia.",              18.7883,   98.9853,
                        "https://images.unsplash.com/photo-1528181304800-259b08848526?w=600&q=80"),
                attr("Wat Arun",         "Templo do Amanhecer às margens do Rio Chao Phraya.",                        13.7437,  100.4888,
                        "https://images.unsplash.com/photo-1563492065599-3520f775eeed?w=600&q=80"),
                attr("Khao Yai",         "Floresta tropical com elefantes e cachoeiras, Patrimônio da UNESCO.",       14.4289,  101.3731,
                        "https://images.unsplash.com/photo-1528181304800-259b08848526?w=600&q=80")
        ));

        seed("AUS", List.of(
                attr("Ópera de Sydney",            "Obra-prima da arquitetura moderna às margens do porto.",         -33.8568,  151.2153,
                        "https://images.unsplash.com/photo-1506973035872-a4ec16b8e8d9?w=600&q=80"),
                attr("Grande Barreira de Corais",  "Maior sistema de recifes de coral do mundo.",                    -18.2871,  147.6992,
                        "https://images.unsplash.com/photo-1559128010-7c1ad6e1b6a5?w=600&q=80"),
                attr("Uluru",                      "Monolito sagrado para os aborígenes no outback australiano.",    -25.3444,  131.0369,
                        "https://images.unsplash.com/photo-1529967297014-9697e4c5f40c?w=600&q=80"),
                attr("Praia de Bondi",             "Icônica praia urbana de Sydney com ondas famosas.",              -33.8915,  151.2767,
                        "https://images.unsplash.com/photo-1523428096881-5bd79d043006?w=600&q=80"),
                attr("Blue Mountains",             "Planalto com eucaliptos, cachoeiras e formações rochosas.",      -33.7200,  150.3000,
                        "https://images.unsplash.com/photo-1493807742766-7df6b5e1e6d7?w=600&q=80")
        ));

        seed("MEX", List.of(
                attr("Chichén Itzá",   "Pirâmide de Kukulcán, uma das Sete Maravilhas do Mundo.",                    20.6843,  -88.5678,
                        "https://images.unsplash.com/photo-1518638150340-f706e86654de?w=600&q=80"),
                attr("Tulum",          "Ruínas maias à beira-mar com vista para o Caribe.",                          20.2114,  -87.4654,
                        "https://images.unsplash.com/photo-1570789210967-2cac24afeb00?w=600&q=80"),
                attr("Teotihuacán",    "Cidade pré-colombiana com a Pirâmide do Sol próxima à CDMX.",                19.6925,  -98.8438,
                        "https://images.unsplash.com/photo-1518638150340-f706e86654de?w=600&q=80"),
                attr("Cancún",         "Resort costeiro no Caribe mexicano com praias de areia branca.",             21.1619,  -86.8515,
                        "https://images.unsplash.com/photo-1510097467424-192d713fd8b2?w=600&q=80"),
                attr("Cañon del Cobre","Sistema de cânions maior que o Grand Canyon na Sierra Tarahumara.",          27.5000, -107.5000,
                        "https://images.unsplash.com/photo-1518638150340-f706e86654de?w=600&q=80")
        ));

        seed("DEU", List.of(
                attr("Portão de Brandemburgo","Símbolo da reunificação alemã e ícone de Berlim.",                    52.5163,  13.3777,
                        "https://images.unsplash.com/photo-1467269204594-9661b134dd2b?w=600&q=80"),
                attr("Castelo Neuschwanstein","Castelo de conto de fadas nos Alpes da Baviera.",                     47.5576,  10.7498,
                        "https://images.unsplash.com/photo-1508014393500-ef0938de84f6?w=600&q=80"),
                attr("Catedral de Colônia",   "Catedral gótica com 157 m, Patrimônio da Humanidade.",                50.9413,   6.9583,
                        "https://images.unsplash.com/photo-1467269204594-9661b134dd2b?w=600&q=80"),
                attr("Floresta Negra",        "Região montanhosa coberta de abetos em Baden-Württemberg.",           48.0000,   8.2000,
                        "https://images.unsplash.com/photo-1467269204594-9661b134dd2b?w=600&q=80"),
                attr("Museu Island",          "Ilha dos museus em Berlim com cinco museus mundialmente famosos.",    52.5170,  13.4014,
                        "https://images.unsplash.com/photo-1467269204594-9661b134dd2b?w=600&q=80")
        ));

        seed("GBR", List.of(
                attr("Big Ben",              "Torre do relógio do Parlamento Britânico, símbolo de Londres.",        51.5007,   -0.1246,
                        "https://images.unsplash.com/photo-1513635269975-59663e0ac1ad?w=600&q=80"),
                attr("Buckingham Palace",    "Residência oficial da monarquia britânica.",                           51.5014,   -0.1419,
                        "https://images.unsplash.com/photo-1513635269975-59663e0ac1ad?w=600&q=80"),
                attr("Stonehenge",           "Monumento pré-histórico de pedras megalíticas em Wiltshire.",         51.1789,   -1.8262,
                        "https://images.unsplash.com/photo-1599833975787-5c143f373c30?w=600&q=80"),
                attr("Castelo de Edimburgo", "Fortaleza histórica no topo de rochedo vulcânico na Escócia.",        55.9486,   -3.1999,
                        "https://images.unsplash.com/photo-1570913149827-d2ac84ab3f9a?w=600&q=80"),
                attr("Lago Ness",            "Famoso lago escocês associado à lenda do monstro Nessie.",            57.3229,   -4.4244,
                        "https://images.unsplash.com/photo-1570913149827-d2ac84ab3f9a?w=600&q=80")
        ));

        seed("NLD", List.of(
                attr("Keukenhof",         "Jardim com 7 milhões de tulipas, o mais famoso do mundo.",                52.2705,    4.5469,
                        "https://images.unsplash.com/photo-1523712999610-f77fbcfc3843?w=600&q=80"),
                attr("Casa de Anne Frank", "Museu no esconderijo da família Frank durante a 2ª Guerra Mundial.",     52.3752,    4.8839,
                        "https://images.unsplash.com/photo-1534351590666-13e3e96b5017?w=600&q=80"),
                attr("Rijksmuseum",        "Principal museu da Holanda com obras de Rembrandt e Vermeer.",           52.3600,    4.8852,
                        "https://images.unsplash.com/photo-1534351590666-13e3e96b5017?w=600&q=80"),
                attr("Kinderdijk",         "19 moinhos de vento históricos, Patrimônio da Humanidade.",             51.8833,    4.6333,
                        "https://images.unsplash.com/photo-1523712999610-f77fbcfc3843?w=600&q=80"),
                attr("Delft",              "Cidade histórica com canais e a famosa cerâmica azul e branca.",         52.0116,    4.3571,
                        "https://images.unsplash.com/photo-1534351590666-13e3e96b5017?w=600&q=80")
        ));

        seed("CHE", List.of(
                attr("Matterhorn",         "Pico piramidal de 4478 m, símbolo dos Alpes suíços.",                   45.9763,    7.6586,
                        "https://images.unsplash.com/photo-1531366936337-7c912a4589a7?w=600&q=80"),
                attr("Jungfrau",           "Topo da Europa com estação ferroviária a 3454 m de altitude.",          46.5472,    7.9852,
                        "https://images.unsplash.com/photo-1506905925346-21bda4d32df4?w=600&q=80"),
                attr("Lago Genebra",       "Maior lago dos Alpes, fronteira natural entre Suíça e França.",         46.4500,    6.6000,
                        "https://images.unsplash.com/photo-1506905925346-21bda4d32df4?w=600&q=80"),
                attr("Lucerna",            "Cidade medieval com a famosa Ponte de Madeira do séc. XIV.",            47.0502,    8.3093,
                        "https://images.unsplash.com/photo-1527668752968-14dc70a27c95?w=600&q=80"),
                attr("Château de Chillon", "Castelo medieval às margens do Lago Genebra.",                         46.4139,    6.9272,
                        "https://images.unsplash.com/photo-1527668752968-14dc70a27c95?w=600&q=80")
        ));

        seed("ARG", List.of(
                attr("Cataratas do Iguaçu", "275 quedas d'água na fronteira com o Brasil, Patrimônio da Humanidade.", -25.6953, -54.4367,
                        "https://images.unsplash.com/photo-1591017403286-fd8493524e1e?w=600&q=80"),
                attr("Glaciar Perito Moreno","Imponente geleira na Patagônia que avança sobre o Lago Argentino.",    -50.4969, -73.1442,
                        "https://images.unsplash.com/photo-1612294037637-ec328d0e075e?w=600&q=80"),
                attr("Bairro La Boca",      "Bairro colorido de Buenos Aires, berço do tango.",                     -34.6345, -58.3631,
                        "https://images.unsplash.com/photo-1612294037637-ec328d0e075e?w=600&q=80"),
                attr("Península Valdés",    "Reserva natural com orcas, elefantes-marinhos e pinguins.",            -42.5000, -64.0000,
                        "https://images.unsplash.com/photo-1612294037637-ec328d0e075e?w=600&q=80"),
                attr("Torres del Paine (AR)","Vista argentina das torres, porta da Patagônia.",                     -50.9423, -73.4068,
                        "https://images.unsplash.com/photo-1612294037637-ec328d0e075e?w=600&q=80")
        ));

        seed("CHL", List.of(
                attr("Torres del Paine",   "Parque nacional na Patagônia com torres de granito e lagoas.",          -50.9423,  -73.4068,
                        "https://images.unsplash.com/photo-1478827536114-da961b7f86d2?w=600&q=80"),
                attr("Deserto do Atacama", "Deserto mais seco do mundo com gêiseres e céu estrelado.",             -22.9087,  -68.2000,
                        "https://images.unsplash.com/photo-1478827536114-da961b7f86d2?w=600&q=80"),
                attr("Ilha de Páscoa",     "Ilha remota com os misteriosos moais de pedra dos Rapa Nui.",          -27.1127, -109.3497,
                        "https://images.unsplash.com/photo-1553697388-94e804e2f0f6?w=600&q=80"),
                attr("Valparaíso",         "Cidade portuária com casas coloridas em morros históricos.",           -33.0472,  -71.6127,
                        "https://images.unsplash.com/photo-1478827536114-da961b7f86d2?w=600&q=80"),
                attr("Lago Llanquihue",    "Lago vulcânico no sul do Chile com vista para o vulcão Osorno.",      -41.1167,  -72.7833,
                        "https://images.unsplash.com/photo-1478827536114-da961b7f86d2?w=600&q=80")
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

    private Attraction attr(String name, String description,
                            Double lat, Double lng, String imageUrl) {
        return Attraction.builder()
                .name(name)
                .description(description)
                .latitude(lat)
                .longitude(lng)
                .imageUrl(imageUrl)
                .build();
    }
}
