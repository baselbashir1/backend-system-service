# Backend System Service

This service represents a backend system connected to MySQL.

## Database
- MySQL schema: bundle_db 
- Table: bundle
- Migration: Flyway
- You can see the table structure in the resources/db.migration/V1__Create_bundle_table.sql

## Endpoints
- /bundles/{id} (GET) – Get bundle by ID
- /bundles (POST) – Create bundle
- /bundles/{id} (PATCH) – Update bundle
- /bundles/{id} (DELETE) – Delete bundle

## Tech Stack
- Spring Boot 3.5.4
- Java 21
- Spring Data JPA
- MySQL
- Validation
- Flyway
- Lombok

## Build & Run
```bash
./mvnw clean package
java -jar target/backendsystem-0.0.1-SNAPSHOT.war