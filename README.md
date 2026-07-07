# ✈️ DashViagens

> Planeje sua próxima viagem em um único lugar.

O **DashViagens** é uma aplicação **Full Stack** desenvolvida com **Java + Spring Boot** no backend e **React + TypeScript** no frontend, criada para centralizar informações essenciais para quem deseja viajar.

A aplicação reúne funcionalidades como consulta de cotações em tempo real, exploração de destinos turísticos, cálculo estimado de custos de viagem e gerenciamento de informações através de uma API REST documentada com Swagger.

O projeto foi desenvolvido com foco em boas práticas de arquitetura, organização de código, segurança, documentação e integração entre frontend e backend.

---

## 📸 Preview

### Home

<div align="center">
<img src="https://github.com/user-attachments/assets/4896bdba-f7d8-44a1-acbe-ff45af9b5bbf" />
</div>

### Países

<div align="center">
<img src="https://github.com/user-attachments/assets/8c760637-6837-4f96-b526-a8a851ec3eb2" />
</div>

### Atrações

<div align="center">
<img src="https://github.com/user-attachments/assets/f08e5f41-6d44-4e79-83fa-203f61160f94" />
</div>

### Calculadora

<div align="center">
<img src="https://github.com/user-attachments/assets/7bc11528-5a7d-4d89-8f03-43e9d95de65f" />
</div>

### Câmbio

<div align="center">
<img src="https://github.com/user-attachments/assets/2f46923e-4fa1-4e88-84ce-c436a1da115f" />
</div>

---

# ✨ Funcionalidades

## 💱 Câmbio em tempo real

- Consulta das principais moedas utilizando a Frankfurter API
- Atualização automática das cotações
- Cache para reduzir chamadas à API externa
- Exibição das principais moedas na página inicial

Moedas suportadas:

- USD
- EUR
- GBP
- JPY
- CAD
- CHF
- ARS
- CLP

---

## 💸 Conversor de moedas

Conversão entre moedas utilizando BRL como moeda intermediária.

Exemplo:

```
5000 BRL

↓

968 USD
```

---

## 🌍 Exploração de países

Consulta dos países cadastrados contendo informações como:

- Nome
- Capital
- Idioma
- Moeda
- Fuso horário
- Melhor época para visitar

---

## 🗺️ Pontos turísticos

Cada país pode possuir diversas atrações cadastradas.

Cada atração possui:

- Nome
- Descrição
- Mapa interativo utilizando Leaflet + OpenStreetMap

---

## 💰 Calculadora de orçamento

Informe:

- País
- Quantidade de dias
- Orçamento disponível

O sistema calcula automaticamente:

- ✈️ Passagem
- 🏨 Hospedagem
- 🍽️ Alimentação
- 🚖 Transporte
- 🎟️ Passeios

Ao final é informado:

- Valor total estimado
- Valor restante
- Se o orçamento é suficiente

---

# 🛠️ Tecnologias

## Backend

| Tecnologia | Finalidade |
|------------|------------|
| Java 21 | Linguagem principal |
| Spring Boot | Framework |
| Spring Web | APIs REST |
| Spring Security | Segurança |
| JWT | Autenticação |
| Spring Data JPA | Persistência |
| Hibernate | ORM |
| PostgreSQL | Banco de dados |
| Flyway | Migrações |
| Spring Cache | Cache das cotações |
| WebClient | Consumo de APIs externas |
| SpringDoc OpenAPI | Swagger |
| Maven | Build |
| Lombok | Redução de boilerplate |

---

## Frontend

| Tecnologia | Finalidade |
|------------|------------|
| React | Interface |
| TypeScript | Tipagem |
| Vite | Build Tool |
| React Router | Navegação |
| TanStack Query | Cache e gerenciamento de requisições |
| Axios | Cliente HTTP |
| Leaflet | Mapas |
| CSS | Estilização |

---

## Banco de Dados

- PostgreSQL

---

## APIs Externas

| API | Utilização |
|------|------------|
| Frankfurter API | Cotações de moedas |
| Countries API | Dados dos países |
| OpenStreetMap | Mapas |
| Leaflet | Visualização geográfica |

---

# 🏗️ Arquitetura

O DashViagens foi desenvolvido seguindo uma arquitetura em camadas, separando responsabilidades entre apresentação, regras de negócio, persistência e integração com serviços externos.

### Backend

```
Controller
      │
      ▼
 Service
      │
      ▼
Repository
      │
      ▼
 PostgreSQL
```

As integrações com APIs externas são encapsuladas em clientes específicos utilizando **Spring WebClient**, mantendo o restante da aplicação desacoplado.

---

### Frontend

```
Pages
   │
   ▼
Components
   │
   ▼
Hooks (React Query)
   │
   ▼
Axios
   │
   ▼
Spring Boot API
```

Toda comunicação com o backend é realizada através de serviços centralizados utilizando **Axios**, enquanto o gerenciamento das requisições e cache é feito pelo **TanStack Query**.

---

# ⚙️ Executando o projeto

## Pré-requisitos

Antes de iniciar, é necessário possuir instalado:

- Java 21+
- Maven 3.9+
- Node.js 20+
- PostgreSQL 14+

---

# ☕ Backend

## 1. Clone o repositório

```bash
git clone https://github.com/Marcus-Antonio1/DashViagens.git
```

Entre na pasta do projeto

```bash
cd DashViagens/backend
```

---

## 2. Criar o banco

No PostgreSQL:

```sql
CREATE DATABASE dashviagens;
```

---

## 3. Configurar o application.yml

Caso necessário, altere as credenciais do banco.

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/dashviagens
    username: postgres
    password: postgres
```

Também configure sua chave JWT:

```yaml
dashviagens:
  jwt:
    secret: SUA_CHAVE_COM_NO_MINIMO_32_CARACTERES
```

Caso queira gerar uma chave segura:

```bash
openssl rand -base64 32
```

---

## 4. Executar

```bash
mvn spring-boot:run
```

A aplicação ficará disponível em

```
http://localhost:8080
```

---

## 📚 Swagger

Após iniciar o backend:

```
http://localhost:8080/docs
```

Toda a documentação da API estará disponível através do Swagger/OpenAPI.

<div align="center">
<img src="https://github.com/user-attachments/assets/5a3b4ff7-01d6-4358-8a7d-c18786e6fbfe" />
</div>

---

# ⚛️ Frontend

Entre na pasta

```bash
cd DashViagens/frontend
```

Instale as dependências

```bash
npm install
```

Execute

```bash
npm run dev
```

Aplicação disponível em

```
http://localhost:5173
```

---

## 🔄 Comunicação Frontend ↔ Backend

Durante o desenvolvimento, o frontend utiliza o proxy do Vite para redirecionar automaticamente todas as requisições iniciadas por `/api` para:

```
http://localhost:8080
```

Dessa forma não é necessário configurar CORS específico durante o desenvolvimento.

---

# 🔒 Segurança

A aplicação utiliza autenticação baseada em **JWT**.

Existem dois tipos de acesso:

### Público

- Consulta de países
- Consulta de atrações
- Consulta de cotações
- Calculadora de orçamento

### Administrador

Requer token JWT para:

- Criar países
- Editar países
- Excluir países
- Gerenciar atrações
- Gerenciar custos médios

```
Authorization: Bearer <token>
```

---

## 🔐 Autenticação

Realiza login do administrador.

```http
POST /api/admin/auth/login
```

---

## 💱 Câmbio

Retorna as cotações atualizadas.

```http
GET /api/exchange/rates
```

Converte valores entre moedas.

```http
GET /api/exchange/convert
```

Exemplo:

```
GET /api/exchange/convert?amount=1000&from=BRL&to=USD
```

---

## 🌍 Países

Lista todos os países cadastrados.

```http
GET /api/countries
```

Busca um país pelo código.

```http
GET /api/countries/{code}
```

Cadastro de país (Admin)

```http
POST /api/countries
```

Atualização

```http
PUT /api/countries/{id}
```

Remoção

```http
DELETE /api/countries/{id}
```

---

## 🗺️ Atrações

Lista atrações.

```http
GET /api/attractions
```

Lista atrações por país.

```http
GET /api/attractions?countryCode=JPN
```

Busca uma atração.

```http
GET /api/attractions/{id}
```

Cadastro

```http
POST /api/attractions
```

Atualização

```http
PUT /api/attractions/{id}
```

Remoção

```http
DELETE /api/attractions/{id}
```

---

## 💰 Custos

Lista custos cadastrados.

```http
GET /api/costs
```

Busca custo por país.

```http
GET /api/costs/{countryCode}
```

Calcula um orçamento.

```http
POST /api/costs/estimate
```

Cadastro de custo.

```http
POST /api/costs
```

Atualização.

```http
PUT /api/costs/{countryCode}
```

Remoção.

```http
DELETE /api/costs/{countryCode}
```

---

# Decisões de Arquitetura

Durante o desenvolvimento foram tomadas algumas decisões visando simplicidade, organização e facilidade de manutenção.

---

## Arquitetura em Camadas

Cada módulo possui suas próprias responsabilidades.

```
Controller

↓

Service

↓

Repository

↓

Database
```

Essa separação facilita testes, manutenção e evolução da aplicação.

---

## Monólito Modular

Embora seja um único projeto, cada domínio é totalmente isolado.

```
exchange

country

attraction

cost

admin
```

Cada módulo possui seus próprios:

- Controllers
- Services
- DTOs
- Repositories
- Entidades

Essa abordagem facilita uma futura migração para microsserviços caso necessário.

---

## Cache

As consultas de câmbio utilizam cache para reduzir chamadas à API externa.

Durante o desenvolvimento foi utilizado:

```
Spring Cache
```

com armazenamento em memória.

O projeto já está preparado para utilizar Redis futuramente.

---

## Integração com APIs Externas

Atualmente o sistema utiliza:

- Frankfurter API
- Countries API
- OpenStreetMap

Toda integração é feita através do **Spring WebClient**, mantendo o restante da aplicação desacoplado.

---

## Segurança

A autenticação foi implementada utilizando:

- Spring Security
- JWT

---

# 💵 Sobre os custos médios

Os custos utilizados na calculadora representam estimativas para auxiliar no planejamento da viagem.

As informações foram baseadas em fontes públicas consultadas durante o desenvolvimento do projeto, incluindo:

- Numbeo
- Quanto Custa Viajar
- Google Flights (estimativa de passagens)

Os valores consideram um perfil de viagem econômico, incluindo:

- Hotel padrão
- Alimentação em restaurantes simples
- Transporte público
- Passeios turísticos

Os custos podem variar conforme:

- época do ano;
- cidade visitada;
- cotação da moeda;
- perfil do viajante.

O objetivo da funcionalidade é fornecer uma estimativa inicial de planejamento, não um orçamento definitivo.

---

# 📚 Aprendizados

O desenvolvimento deste projeto permitiu aprofundar conhecimentos em diversas tecnologias e conceitos, entre eles:

### Backend

- Arquitetura em camadas
- Organização em módulos
- APIs REST
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- Hibernate
- PostgreSQL
- Flyway
- Spring Cache
- Consumo de APIs externas com WebClient
- Documentação com Swagger/OpenAPI

### Frontend

- React
- TypeScript
- Vite
- React Router
- TanStack Query
- Axios
- Componentização
- Consumo de APIs REST
- Organização de projetos Frontend

Além do desenvolvimento técnico, o projeto também reforçou conhecimentos sobre integração entre aplicações, modelagem de banco de dados e boas práticas de organização de código.

---

# 💡 Possíveis melhorias

Algumas ideias que podem ser implementadas futuramente:

- Histórico completo de variação cambial
- Recomendações de destinos por orçamento
- Comparação entre países
- Dashboard com gráficos e indicadores 
- Cadastro de usuários
- Favoritos persistidos
- Integração com APIs de clima
- Integração com APIs de passagens aéreas
- Integração com APIs de hotéis
- Alertas sobre queda do valor das moedas

---

# 📈 Próximos passos

Pretendo continuar evoluindo o DashViagens adicionando novas funcionalidades e melhorias de arquitetura.

Entre elas:

- disponibilizar uma versão online da aplicação;
- melhorar a experiência do usuário;
- ampliar a base de destinos;
- integrar novos serviços externos.

---

# 💬 Feedback

Este projeto faz parte da construção do meu portfólio como Desenvolvedor Backend Java.

Se você tiver alguma sugestão de melhoria, encontrar algum problema ou quiser compartilhar alguma ideia, ficarei muito feliz em receber seu feedback.

Você pode abrir uma **Issue** neste repositório ou entrar em contato pelo LinkedIn.

---

# 🌐 Deploy

Estou avaliando disponibilizar uma versão pública do DashViagens.

Na sua opinião:

**Você faria o deploy deste projeto?**

---

### Contato

- LinkedIn: https://linkedin.com/in/marcus-toledo-3a380a231

---

⭐ Se este projeto foi interessante para você, deixe uma estrela no repositório.
