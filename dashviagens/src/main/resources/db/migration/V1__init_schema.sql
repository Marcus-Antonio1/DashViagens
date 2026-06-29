-- Paises
CREATE TABLE countries (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(3) NOT NULL UNIQUE,
    name VARCHAR(120) NOT NULL,
    capital VARCHAR(120),
    language VARCHAR(120),
    currency_code VARCHAR(3),
    population BIGINT,
    timezone VARCHAR(60),
    best_season VARCHAR(60),
    best_season_description VARCHAR(255),
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);

-- Pontos turisticos
CREATE TABLE attractions (
    id BIGSERIAL PRIMARY KEY,
    country_id BIGINT NOT NULL REFERENCES countries(id) ON DELETE CASCADE,
    name VARCHAR(150) NOT NULL,
    description TEXT,
    image_url VARCHAR(500),
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    created_at TIMESTAMP NOT NULL DEFAULT now()
);
CREATE INDEX idx_attractions_country_id ON attractions(country_id);

-- Custo medio diario por pais (usado pela calculadora de viagem)
CREATE TABLE country_costs (
    id BIGSERIAL PRIMARY KEY,
    country_id BIGINT NOT NULL UNIQUE REFERENCES countries(id) ON DELETE CASCADE,
    hotel_per_day NUMERIC(10,2) NOT NULL,
    food_per_day NUMERIC(10,2) NOT NULL,
    transport_per_day NUMERIC(10,2) NOT NULL,
    activities_per_day NUMERIC(10,2) NOT NULL,
    estimated_flight NUMERIC(10,2),
    currency VARCHAR(3) NOT NULL DEFAULT 'BRL'
);

-- Usuario admin (unico papel de autenticacao do sistema; protege rotas de escrita)
CREATE TABLE admin_users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(60) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(30) NOT NULL DEFAULT 'ADMIN',
    created_at TIMESTAMP NOT NULL DEFAULT now()
);

-- Para criar o primeiro admin: gere um hash BCrypt (ex: via PasswordEncoder no console
-- do Spring, ou bcrypt-generator.com) e rode um INSERT manual, ou crie um
-- CommandLineRunner que cria o admin na primeira inicializacao se a tabela estiver vazia.
