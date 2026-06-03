# E-Commerce Microservices

A Spring Boot microservices platform for e-commerce. Currently in development.

## Project Structure

```
ecom-microservices/
├── User/               # User Service
├── Product/            # Product Service
├── Order/              # Order Service
├── ecom-application/   # Legacy monolith (being refactored)
└── CLAUDE.md           # Development guidelines
```

## Services

- **User Service** - User management and profiles
- **Product Service** - Product catalog and inventory
- **Order Service** - Order processing and cart management

Each service is a standalone Spring Boot application with its own database.

## Tech Stack

- Java 25
- Spring Boot 4.0.6
- Spring Data JPA
- PostgreSQL / MySQL
- Docker

## Development Status

⚠️ **This project is in active development**. APIs and database schemas are subject to change.

## Getting Started

Refer to [CLAUDE.md](CLAUDE.md) for detailed development guidelines.
