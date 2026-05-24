# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Architecture Overview

This is a microservices workspace for an e-commerce platform. Currently contains one Spring Boot service (`ecom-application/`), with additional services expected as sibling directories at the repository root.

**ecom-application** is a Spring Boot 4.0.6 REST API service using:
- Spring Data JPA with MySQL (Hibernate auto-DDL enabled)
- Lombok for boilerplate reduction
- Standard layered architecture: Controller → Service → Repository → Entity

The service implements soft deletion patterns (entities have `active` boolean flags) and uses DTOs for API contracts rather than exposing domain models directly.

### Key Domain Models

**User**: Central user entity with embedded Address (one-to-one with cascade), UserRole enum (CUSTOMER default), and audit timestamps.

**Product**: Catalog entity with pricing, inventory tracking (stockQuantity), and soft deletion via active flag.

**Address**: Embedded entity owned by User, cascade-deleted when user is removed.

Repository layer uses Spring Data JPA with custom query methods. Example: `ProductRepository.searchProducts()` uses JPQL to filter by active status, stock availability, and case-insensitive name matching.

## Development Commands

All commands run from `ecom-application/` directory. Use Maven wrapper (not system Maven).

**Start service locally:**
```bash
./mvnw spring-boot:run
# Windows: mvnw.cmd spring-boot:run
```

**Run tests:**
```bash
./mvnw test
```

**Build JAR:**
```bash
./mvnw clean package
```

**Database setup required:** MySQL running on localhost:3306 with database `ecom_application`. Credentials in `application.yaml` (root/1234 for local dev).

## Testing the API

HTTP request files are organized in feature-specific folders:
- `http-request-user/` - User CRUD operations
- `http-request-product/` - Product CRUD and search operations

These `.http` files work with IntelliJ IDEA HTTP Client or VS Code REST Client extension. They target `http://localhost:8080/api/*` endpoints.

## Code Organization

Base package: `com.sadcodes.ecomapplication`

```
controller/   - REST endpoints (@RestController)
services/     - Business logic (@Service)
repository/   - Spring Data JPA interfaces
model/        - JPA entities (@Entity)
dto/          - Request/Response DTOs (no JPA annotations)
```

Controllers return `ResponseEntity<DTO>` with appropriate HTTP status codes. Services handle entity-to-DTO mapping and business validation. Repositories extend `JpaRepository<Entity, ID>`.

## Coding Conventions

- Package naming: Follow `com.sadcodes.ecomapplication.*` structure
- Entity management: Use soft deletion (set `active = false`) rather than hard deletes
- DTO pattern: Always map entities to Response DTOs before returning from controllers
- Lombok usage: `@Getter/@Setter` on entities, `@Data` on DTOs, `@RequiredArgsConstructor` on services/controllers for dependency injection
- Audit fields: Prefer `@CreationTimestamp` and `@UpdateTimestamp` over manual date setting
- Test class naming: Use `*Tests` suffix (not `*Test`)

## Configuration Notes

`application.yaml` contains database credentials for local development. Never commit production credentials or environment-specific config to this file.

Hibernate DDL is set to `update` mode, which auto-creates/modifies tables on startup. This is for development convenience.

## Commit Message Format

Use conventional commit format: `<type>: <imperative summary>`

Types: `feat`, `fix`, `docs`, `refactor`, `test`, `build`, `chore`

Examples:
- `feat: add order checkout endpoint`
- `fix: resolve product search case sensitivity`
- `refactor: extract user mapping to utility`

Keep commits scoped to one concern. Include co-authorship when working with Claude Code.
