# AI Food Tracker

**Enterprise-Grade Microservices Demonstration**

## 🔝 Key Selling Points

* Architected for **Scalability & Resilience**: Decoupled microservices via Kafka, containerized with Docker and orchestrated on Kubernetes.
* **Security-First** Design: OAuth2 Resource Server, JWT, and zero-trust principles with Spring Security.
* **Cloud-Native Ready**: Twelve-Factor compliance, externalized configuration, and built-in health checks via Spring Boot Actuator.
* **AI-Driven**: Leverages OpenAI’s cutting-edge models with Spring AI for seamless image/text analysis.

## 🚀 Technology Stack

* **Language & Frameworks:** Java 17, Spring Boot 3.x (Web, Data JPA, Security, Cloud)
* **API Gateway & Routing:** Spring Cloud Gateway (WebFlux), Resilience4j
* **Security:** Spring Security (OAuth2 Resource Server), JWT (jjwt), Keycloak integration
* **Messaging & Eventing:** Apache Kafka (Spring Kafka), Avro serialization, Schema Registry
* **Data Persistence:** PostgreSQL (production-grade), H2 (dev), Flyway migrations
* **AI & ML Integration:** OpenAI API via Spring AI, TensorFlow for custom ML pipelines
* **Documentation & UI:** Swagger (Springfox OpenAPI 3), React-based API Console
* **CI/CD & DevOps:** Gradle, Jenkins/GitLab CI, Docker, Docker Compose, Helm charts
* **Container Orchestration:** Kubernetes, Istio service mesh
* **Monitoring & Observability:** Prometheus, Grafana, ELK Stack, Zipkin for distributed tracing
* **Quality & Testing:** JUnit 5, Mockito, integration tests with Testcontainers, SonarQube
* **Code Productivity:** Lombok, MapStruct, OpenAPI code generation

## 🏗️ Architecture Overview

```plaintext
            ┌─────────────────────────────────┐
            │         API Gateway            │
            │ (Routing, JWT, Circuit Breaker)│
            └───────────────┬─────────────────┘
                            │
          ┌─────────────────┴────────────────┐
          │                                  │
 ┌────────▼─────────┐              ┌─────────▼─────────┐
 │   auth-service   │              │   food-service    │
 │ (JWT Issuance,   │              │  (OpenAI AI,      │
 │  OAuth2, H2/PG,  │              │   Kafka Producer, │
 │  Flyway, Health) │              │   Swagger, Metrics)│
 └───────┬──────────┘              └─────────┬─────────┘
         │                                   │
         │            Kafka topic            │
         │<──────────────────────────────────>│
         │                                   │
 ┌───────▼──────────┐              ┌─────────▼─────────┐
 │ calorie-service  │              │  Kafka Cluster    │
 │ (Kafka Consumer, │              │ (High-Throughput, │
 │  Postgres,       │              │  Replicated)      │
 │  Prometheus)     │              └───────────────────┘
 └──────────────────┘

```

## 🌟 Advanced Features & Next-Gen Practices

**Implemented Production-Ready Extensions:**

* **Distributed Tracing & Observability:** Integrated Spring Boot Actuator metrics with Prometheus and Grafana dashboards; Zipkin traces for end-to-end request flows.
* **Circuit Breakers & Resilience:** Resilience4j rate limiters, retry policies, and circuit breakers protecting downstream calls.
* **Schema Evolution:** Apache Avro schemas stored in a Confluent Schema Registry; contract-validation for Kafka messages.
* **Contract-Driven Development:** OpenAPI code generation for client/server stubs, ensuring API compatibility.
* **Integration Testing with Testcontainers:** Automated spinning up Kafka, PostgreSQL, and H2 containers for reliable CI tests.
* **Centralized Configuration:** Spring Cloud Config server with encrypted secrets, enabling dynamic property updates without redeploy.
* **Containerized Deployments:** Docker Compose setup for local orchestration and Helm charts for Kubernetes deployments.
* **Structured Logging & Monitoring:** JSON-formatted logs via Logback, shipped to Elasticsearch, with Kibana dashboards and alerting.
* **Database Migrations:** Flyway version-controlled migrations for all relational schemas.
* **Feature Toggles:** FF4J-based runtime feature flags for safe rollouts and A/B experiments.
* **Security Hardening:** OWASP security headers configured, dependency vulnerability scanning via OWASP Dependency Check.
