# E-Commerce Microservices

A Spring Boot microservices workspace for an e-commerce platform. The repository contains separate services for users, products, orders, configuration, and service discovery.

## Repository Layout

```text
ecom-microservices/
├── Config_Server/     # Spring Cloud Config Server
├── Eureka-Server/     # Service registry
├── User/              # User service
├── Product/           # Product service
├── Order/             # Order and cart service
└── ecom-application/  # Legacy application module
```

Each service follows the standard Maven layout:

- `src/main/java` for application code
- `src/main/resources` for configuration
- `src/test/java` for tests

## Services

- `Eureka-Server` runs on `http://localhost:8761`
- `Config_Server` runs on `http://localhost:8888`
- `User` runs on `http://localhost:8082`
- `Product` runs on `http://localhost:8081`
- `Order` runs on `http://localhost:8083`

`Order` uses Eureka and Spring Cloud LoadBalancer for inter-service calls to `User` and `Product`.

## Roadmap

- More services and features will be added over time as the platform grows.
- New modules will be added as sibling directories at the repository root.

## Local Setup

Start the infrastructure services first, then the business services:

1. `Eureka-Server`
2. `Config_Server`
3. `User`
4. `Product`
5. `Order`

The `Order`, `User`, and `Product` services load most runtime settings from the Config Server using their `spring.config.import` entries.

## Build and Test

Run commands from the service directory you are working in.

- `./mvnw spring-boot:run` or `mvnw.cmd spring-boot:run`: start one service locally
- `./mvnw test` or `mvnw.cmd test`: run the test suite
- `./mvnw clean package`: compile, test, and build the JAR
- `./mvnw clean`: remove generated build output

Use the Maven wrapper instead of a system Maven install.

## Development Notes

- Java 21 and Spring Boot 4.0.6 are used across the services.
- Keep secrets and environment-specific values out of source control.
- Use conventional commit messages such as `fix: restore inter-service communication for order service`.

For contributor guidance, see [AGENTS.md](AGENTS.md).
