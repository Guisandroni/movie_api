# Movie API

## Visão Geral do Projeto

A Movie API é uma API RESTful desenvolvida em Java com Spring Boot para gerenciar informações sobre filmes, categorias e plataformas de streaming. A API permite que os usuários se registrem, se autentiquem, realizem operações CRUD (Criar, Ler, Atualizar e Excluir) e importem dados de filmes do TMDb.

## Tecnologias Utilizadas

- **Java 21**: A linguagem de programação utilizada no projeto.
- **Spring Boot 3.5.5**: O framework utilizado para criar a aplicação.
- **Spring Security**: Para autenticação e autorização.
- **JPA (Java Persistence API)**: Para mapeamento objeto-relacional.
- **Hibernate**: A implementação do JPA.
- **PostgreSQL**: O banco de dados utilizado.
- **Flyway**: Para controle de versão do banco de dados.
- **Maven**: Para gerenciamento de dependências.
- **Lombok**: Para reduzir código boilerplate.
- **JWT (JSON Web Tokens)**: Para autenticação baseada em token.
- **SpringDoc OpenAPI (Swagger)**: Para documentação da API.

## Como Executar

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/movieapi.git
   ```
2. **Configure o banco de dados:**
   - Crie um banco de dados PostgreSQL.
   - Atualize as configurações do banco de dados no arquivo `src/main/resources/application.yaml`.
   - Configure sua chave da API do TMDb no mesmo arquivo.
3. **Execute a aplicação:**
   ```bash
   ./mvnw spring-boot:run
   ```
A API estará disponível em `http://localhost:8080`.

## Documentação com Swagger

A documentação completa e interativa da API está disponível através do Swagger UI. Após iniciar a aplicação, você pode acessá-la no seguinte endereço:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Endpoints da API

A seguir estão os endpoints disponíveis na API:

### Autenticação (`/movieapi/auth`)

- `POST /register`: Registra um novo usuário.
- `POST /login`: Autentica um usuário e retorna um token JWT.
- `DELETE /users/{id}`: Deleta um usuário por ID.
- `GET /users`: Retorna uma lista de todos os usuários.

### Categorias (`/movieapi/category`)

- `GET /`: Retorna uma lista de todas as categorias.
- `GET /{id}`: Retorna uma categoria por ID.
- `POST /`: Cria uma nova categoria.
- `PUT /{id}`: Atualiza uma categoria por ID.
- `DELETE /{id}`: Deleta uma categoria por ID.

### Filmes (`/movieapi/movie`)

- `GET /`: Retorna uma lista de todos os filmes.
- `GET /numberMovies`: Retorna a quantidade total de filmes cadastrados.
- `GET /{id}`: Retorna um filme por ID.
- `POST /`: Cria um novo filme.
- `PUT /{id}`: Atualiza um filme por ID.
- `DELETE /{id}`: Deleta um filme por ID.
- `GET /search?category={id}`: Retorna uma lista de filmes por ID da categoria.

### Streamings (`/movieapi/streaming`)

- `GET /`: Retorna uma lista de todas as plataformas de streaming.
- `GET /{id}`: Retorna uma plataforma de streaming por ID.
- `POST /`: Cria uma nova plataforma de streaming.
- `PUT /{id}`: Atualiza uma plataforma de streaming por ID.
- `DELETE /{id}`: Deleta uma plataforma de streaming por ID.

### Importação (`/movieapi/import`)

- `POST /movies`: Importa os filmes mais populares e suas categorias do TMDb (The Movie Database).