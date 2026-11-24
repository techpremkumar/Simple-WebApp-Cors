# Simple WebApp CORS

A full-stack product catalog application built to demonstrate how a React frontend communicates with a Spring Boot REST API across browser origins.

## Overview

This repository contains a Spring Boot backend, a React + Vite frontend, and a small Java demo module. The backend exposes product endpoints backed by MySQL and Spring Data JPA. Product images can be uploaded and retrieved through dedicated API routes.

## Technology stack

| Layer | Technology |
| --- | --- |
| Backend | Java 21, Spring Boot 4.1.2, Spring MVC |
| Persistence | Spring Data JPA, Hibernate |
| Database | MySQL |
| Frontend | React 19, Vite |
| Build tools | Maven, npm |

## Repository structure

```text
.
├── src/main/java/com/prem/demo/
│   ├── controller/       # REST endpoints
│   ├── model/            # JPA entities
│   ├── repository/       # Database access
│   └── service/          # Business operations
├── src/main/resources/   # Application configuration and SQL data
├── cors-frontend/        # React + Vite frontend
├── demo/                 # Small Java demonstration module
└── pom.xml               # Maven build configuration
```

## Prerequisites

- JDK 21
- Maven 3.9+
- Node.js 20.19+ or 22.12+ with npm
- MySQL 8+

## Backend setup

1. Create the database:

   ```sql
   CREATE DATABASE spring_data_jpa_db;
   ```

2. Configure environment variables before starting the application:

   | Variable | Default / example | Purpose |
   | --- | --- | --- |
   | `DB_URL` | `jdbc:mysql://localhost:3306/spring_data_jpa_db` | JDBC connection URL |
   | `DB_USERNAME` | `root` | MySQL username |
   | `DB_PASSWORD` | Set your local password | MySQL password |
   | `CORS_ALLOWED_ORIGINS` | `http://localhost:5173` | Allowed frontend origin |

   Never commit real credentials. Supply `DB_PASSWORD` through your shell,
   IDE run configuration, or deployment secret manager.

3. Start the backend from the repository root:

   ```bash
   ./mvnw spring-boot:run
   ```

   On Windows:

   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

   If the Maven wrapper is not present, use `mvn spring-boot:run`.

The API is available at `http://localhost:8080`.

## Frontend setup

Open a second terminal:

```bash
cd cors-frontend
npm install
npm run dev
```

Open the local Vite URL printed in the terminal (typically
`http://localhost:5173`). The backend CORS configuration must include that
origin.

## API reference

Base URL: `http://localhost:8080/api/products`

| Method | Endpoint | Description |
| --- | --- | --- |
| GET | `/api/products` | List all products |
| GET | `/api/products/home` | Basic service welcome endpoint |
| GET | `/api/products/search?keyword={term}` | Search products |
| POST | `/api/products/image` | Create a product with an image (multipart form data) |
| GET | `/api/products/{id}/image` | Retrieve a product image |
| PUT | `/api/products/{id}` | Update a product |
| DELETE | `/api/products/{id}` | Delete a product |

### Image upload fields

Send product properties as multipart form fields and attach the image using
the field name `image`.

## CORS configuration

The backend controller reads its allowed origin from
`app.cors.allowed-origins`, which is configured using the
`CORS_ALLOWED_ORIGINS` environment variable. Use explicit trusted origins;
avoid wildcard origins when credentials or sensitive APIs are involved.

## Development commands

Backend:

```bash
./mvnw test
./mvnw package
```

Frontend:

```bash
cd cors-frontend
npm run lint
npm run build
```

## Current scope and next improvements

- Add request validation and consistent API error responses.
- Use DTOs instead of exposing JPA entities directly.
- Add automated controller and service tests.
- Validate uploaded image type and size.
- Add pagination and sorting for product listings.
- Configure production database migrations and deployment profiles.

## Security

- Keep database credentials outside version control.
- Restrict CORS to known frontend origins.
- Validate and limit uploaded files before storing them.
- Use HTTPS and externalized secrets in deployed environments.

## License

No license has been specified yet. Add a license before accepting external
contributions or reuse.
