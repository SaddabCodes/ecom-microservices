# Repository Guidelines

## Project Structure & Module Organization
This repository is a microservices workspace. It currently contains one Spring Boot service in `ecom-application/`, and additional services are expected to be added later as sibling directories at the repository root. Inside each service, application code lives under `src/main/java`, configuration under `src/main/resources`, and tests under `src/test/java`. For the current service, the base package is `com.sadcodes.ecomapplication`. Keep new feature packages grouped by responsibility, for example `controller`, `service`, `domain`, and `config`.

## Build, Test, and Development Commands
Run commands from `ecom-application/`.

- `./mvnw spring-boot:run` or `mvnw.cmd spring-boot:run`: start the service locally.
- `./mvnw test`: run the JUnit test suite.
- `./mvnw clean package`: compile, test, and build the JAR in `target/`.
- `./mvnw clean`: remove generated build output before a fresh build.

Use the Maven wrapper instead of a system Maven install so everyone builds with the same toolchain.

## Coding Style & Naming Conventions
Use standard Java formatting with 4-space indentation and one public class per file. Follow the existing package root `com.sadcodes.ecomapplication`. Class names use `PascalCase`, methods and fields use `camelCase`, and constants use `UPPER_SNAKE_CASE`. Prefer descriptive Spring component names such as `ProductController` and `OrderService`. Lombok is enabled; use it deliberately and avoid hiding non-trivial behavior behind annotations.

## Testing Guidelines
The project uses JUnit 5 with Spring Boot test support (`spring-boot-starter-webmvc-test`). Name test classes with the `*Tests` suffix and mirror the production package structure. Add focused unit or slice tests for new controllers and services; reserve `@SpringBootTest` for application wiring and integration coverage. Run `./mvnw test` before opening a pull request.

## Commit & Pull Request Guidelines
This branch currently has no commit history, so use a simple conventional format for all new commits: `<type>: <short imperative summary>`. Preferred types include `feat`, `fix`, `docs`, `refactor`, `test`, `build`, and `chore`. Examples: `feat: add product controller`, `docs: update local setup steps`, `refactor: simplify order service wiring`. When asked to generate a commit message for this repository, always return it in that format. Keep each commit scoped to one concern. Pull requests should include a brief summary, impacted modules, test evidence, and any configuration changes. Add request/response examples when API behavior changes.

For repository-level changes, prefer subjects that describe the service or workspace clearly, for example `feat: initialize ecom-application service` or `docs: add microservices repository guidelines`.

## Configuration & Environment
Keep defaults in `application.yaml` safe for local development. Do not commit secrets, credentials, or environment-specific URLs. Prefer environment variables or externalized Spring configuration for sensitive values.
