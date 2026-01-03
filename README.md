# Platzi Fake Store API Tests (Rest Assured)

API-Test-Automation mit Java, JUnit 5 und Rest Assured gegen die öffentliche Platzi / EscuelaJS Fake Store API.
Enthält DTO-Mapping (Jackson), Raw-vs-Parsed Client-Pattern und Allure Reporting.

## Tech Stack
- Java 17
- Maven
- JUnit 5
- Rest Assured
- Jackson (inkl. Java Time)
- Allure Reports

## Projektstruktur
```text
src/test/java/de/id/platzi
├── assertions/        # wiederverwendbare Assertions
├── base/              # BaseTest (Config), gemeinsame Test-Basics
├── clients/           # AuthClient, ProductClient, CategoryClient (+ BaseClient)
├── config/            # Config + ConfigLoader
├── models/
│   ├── request/       # Request DTOs (Create/Update/Login/Refresh)
│   └── response/      # Response DTOs (AuthToken, Product, Category, Profile)
└── tests/
    ├── auth/
    ├── category/
    ├── products/
    └── smoke/

src/test/resources
└── config.properties
```

## Tests ausführen
```code
mvn clean test
```

## Alure Report
```code
mvn clean test allure:serve
```
