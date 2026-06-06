# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Architecture Overview

This is a microservices workspace for an e-commerce platform with three independent Spring Boot 4.0.6 services using Java 25. Each service runs on a separate port and manages its own data store.

### Services

**User Service** (`User/`)
- Base package: `com.sadcodes.ecommerce.user`
- Database: MongoDB (ms_user_db on localhost:27017)
- Port: 8082
- Models: User with embedded Address, UserRole enum

**Product Service** (`Product/`)
- Base package: `com.sadcodes.ecommerce.product`
- Database: MySQL (ms_product_db on localhost:3306)
- Port: 8081
- Models: Product with pricing and inventory tracking (stockQuantity)
- Soft deletion via `active` flag

**Order Service** (`Order/`)
- Base package: `com.sadcodes.ecommerce.order`
- Database: PostgreSQL (ms_order_db on localhost:5433)
- Port: 8083
- Models: Order, CartItem, OrderItem with OrderStatus enum
- Manages shopping carts and order processing

### Common Architecture

All services follow standard layered architecture: Controller → Service → Repository → Entity
- Spring Data JPA (MongoDB for User, relational for Product/Order)
- Lombok for boilerplate reduction
- DTOs for API contracts (never expose domain models directly)
- Hibernate auto-DDL enabled (`ddl-auto: update`)

## Development Commands

All commands run from the respective service directory. Use Maven wrapper (not system Maven).

**Start a service locally:**
```bash
cd User && ./mvnw spring-boot:run
cd Product && ./mvnw spring-boot:run
cd Order && ./mvnw spring-boot:run
# Windows: mvnw.cmd spring-boot:run
```

**Run all tests:**
```bash
./mvnw test
```

**Run a single test class:**
```bash
./mvnw test -Dtest=UserServiceTests
```

**Build JAR:**
```bash
./mvnw clean package
```

**Database setup required:**
- MongoDB on localhost:27017 (User service database: `ms_user_db`)
- MySQL on localhost:3306 (Product service database: `ms_product_db`, user: root/1234)
- PostgreSQL on localhost:5433 (Order service database: `ms_order_db`, user: postgres/1234)

All credentials and hosts are configurable via environment variables in `application.yaml`/`application.yml` files.

## Testing the API

HTTP request files are organized in service-specific folders:
- `User/http-request-user/` - User CRUD operations (port 8082)
- `Product/http-request-product/` - Product CRUD and search operations (port 8081)
- `Order/http-request-cart/` - Cart item management (port 8083)
- `Order/http-request-order/` - Order operations (port 8083)

These `.http` files work with IntelliJ IDEA HTTP Client or VS Code REST Client extension. Each service runs on its own port as defined in `application.yaml`/`application.yml`. Cart endpoints require the `X-USER-ID` header to identify the user.

## Code Organization

Each service follows the same layered structure:

**User Service:** `com.sadcodes.ecommerce.user`
**Product Service:** `com.sadcodes.ecommerce.product`
**Order Service:** `com.sadcodes.ecommerce.order`

```
controller/   - REST endpoints (@RestController)
services/     - Business logic (@Service)
repository/   - Data layer interfaces (JpaRepository/MongoRepository)
model/        - Domain entities (@Entity or @Document for MongoDB)
dto/          - Request/Response DTOs (no persistence annotations)
```

Controllers return `ResponseEntity<DTO>` with appropriate HTTP status codes. Services handle entity-to-DTO mapping and business validation. User service uses Spring Data MongoDB; Product and Order services use Spring Data JPA with relational databases.

## Coding Conventions

- Package naming: Follow `com.sadcodes.ecommerce.<service-name>.*` structure
- Entity management: Use soft deletion (set `active = false`) rather than hard deletes
- DTO pattern: Always map entities/documents to Response DTOs before returning from controllers
- Lombok usage: `@Getter/@Setter` on entities, `@Data` on DTOs, `@RequiredArgsConstructor` on services/controllers for dependency injection
- Audit fields: Prefer `@CreationTimestamp` and `@UpdateTimestamp` over manual date setting
- Test class naming: Use `*Tests` suffix (not `*Test`)
- MongoDB documents: Use `@Document` annotation; repositories extend `MongoRepository<Entity, ID>`
- Relational entities: Use `@Entity` annotation; repositories extend `JpaRepository<Entity, ID>`

## Configuration Notes

Each service has its own `application.yaml`/`application.yml` in `src/main/resources/`:

**User Service** (`User/src/main/resources/application.yml`):
- MongoDB connection with environment variable overrides (DB_HOST, DB_PORT, DB_DATABASE)
- Actuator endpoints enabled for monitoring

**Product Service** (`Product/src/main/resources/application.yaml`):
- MySQL connection with environment variable overrides
- Hibernate DDL set to `update` mode

**Order Service** (`Order/src/main/resources/application.yaml`):
- PostgreSQL connection with environment variable overrides
- Hibernate DDL set to `update` mode

Never commit production credentials or environment-specific config. Use environment variables for any environment-dependent settings.

## Commit Message Format

Use conventional commit format: `<type>: <imperative summary>`

Types: `feat`, `fix`, `docs`, `refactor`, `test`, `build`, `chore`

Examples:
- `feat: add order checkout endpoint`
- `fix: resolve product search case sensitivity`
- `refactor: extract user mapping to utility`

Keep commits scoped to one concern. Include co-authorship when working with Claude Code.
