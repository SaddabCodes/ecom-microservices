# Repository Guidelines

## Project Structure & Module Organization
This repository is a Spring microservices workspace. Root modules are `ecom-application/`, `Order/`, `Product/`, `User/`, `Config_Server/`, and `Eureka-Server/`. Each service follows the standard Maven layout: application code in `src/main/java`, configuration in `src/main/resources`, and tests in `src/test/java`. Keep code grouped by responsibility under each service package, such as `controller`, `services`, `repository`, `model`, `dto`, and `client`.

## Build, Test, and Development Commands
Run commands from the service directory you are changing.

- `./mvnw spring-boot:run` or `mvnw.cmd spring-boot:run`: start a single service locally.
- `./mvnw test` or `mvnw.cmd test`: run the test suite for that service.
- `./mvnw clean package`: compile, test, and build the JAR in `target/`.
- `./mvnw clean`: remove generated build output.

Use the Maven wrapper instead of a system Maven install.

## Coding Style & Naming Conventions
Use standard Java formatting with 4-space indentation. Prefer one public class per file, `PascalCase` for classes, `camelCase` for methods and fields, and `UPPER_SNAKE_CASE` for constants. Keep Spring component names descriptive, for example `CartController`, `OrderService`, and `ProductServiceClient`. Use Lombok only when it keeps code clear.

## Testing Guidelines
The projects use JUnit 5 with Spring Boot test support. Name test classes with the `*Tests` suffix and mirror the production package structure. Prefer focused unit or slice tests for controllers, services, and repositories; reserve `@SpringBootTest` for wiring and integration checks. Run tests before opening a pull request.

## Commit & Pull Request Guidelines
Use conventional commit messages in the form `<type>: <short imperative summary>`, such as `fix: restore inter-service communication for order service`. Keep each commit scoped to one change. Pull requests should include a short summary, impacted modules, and test evidence. Include request/response examples when API behavior changes.

## Configuration & Agent Notes
Keep service defaults safe for local development. Do not commit secrets or environment-specific URLs. Prefer externalized Spring configuration for credentials and infrastructure endpoints. When working on framework or library behavior, check current official documentation before changing code.
