# 🏦 Smart Banking Microservices Platform

A production-ready, distributed backend banking ecosystem built with **Java 21** and **Spring Boot 3 / Spring Cloud**. The platform simulates core financial operations—including user management, bank branch administration, checking/savings accounts, credit cards, billing invoices, and asynchronous audit logging.

## 🚀 Key Features

- **Service Discovery & Registration:** Powered by **Netflix Eureka Server** (`discovery-client-service`).
- **API Gateway & Routing:** Single entry-point routing and load balancing using **Spring Cloud Gateway** (`api-gateway-service`).
- **Polyglot Persistence:** 
  - **PostgreSQL:** Primary relational database for financial entities (Users, Banks, Accounts, Cards, Invoices).
  - **MongoDB:** High-throughput document store for system audit logs.
- **Asynchronous Event-Driven Logging:** Decoupled log distribution via **RabbitMQ** to ensure zero latency impact on core business transactions.
- **In-Memory Caching:** **Redis** caching integration for fast read response times.
- **Declarative REST Clients:** **Spring Cloud OpenFeign** for clean inter-service communication.
- **Containerized Orchestration:** End-to-end environment deployment using **Docker & Docker Compose**.
