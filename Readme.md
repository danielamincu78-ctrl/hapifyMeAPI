# HapifyMe API Automation Framework

A Java-based API automation framework built with **REST Assured**, **TestNG**, and **Maven**, demonstrating a complete End-to-End user lifecycle for the HapifyMe application.

This project was developed as part of my QA Automation portfolio to showcase the design and implementation of maintainable, scalable, and reliable API tests using modern Java testing practices.

---

## Features

- Complete End-to-End API workflow
- Dynamic test data generation
- Request and Response mapping using POJOs
- JSON serialization/deserialization with Jackson
- Authentication using Bearer Token
- Asynchronous API validation with Awaitility
- Detailed logging with Log4j2
- Rich test reporting with Allure Reports
- Continuous Integration with GitHub Actions

---

## Tech Stack

| Technology | Purpose |
|------------|---------|
| Java 21 | Programming language |
| Maven | Build automation and dependency management |
| REST Assured | REST API testing |
| TestNG | Test execution framework |
| Jackson | JSON serialization/deserialization |
| Awaitility | Polling asynchronous operations |
| Log4j2 | Logging framework |
| Allure Report | Test reporting |
| GitHub Actions | Continuous Integration |

---

## End-to-End Test Workflow

The automated test validates the complete lifecycle of a user within the HapifyMe platform:

1. Register a new user using dynamically generated data.
2. Wait for the account to become available using asynchronous polling.
3. Authenticate the user and retrieve a Bearer token.
4. Retrieve and validate the user's profile.
5. Update the user's profile information.
6. Verify that the changes have been successfully applied.
7. Delete the user account.
8. Confirm that the account has been removed.

This workflow validates the application's most important API endpoints in a realistic user scenario.

---

## Project Structure

```
src
└── test
    ├── java
    │   └── com.hapifyme.api
    │       ├── models
    │       │   ├── Request POJOs
    │       │   └── Response POJOs
    │       │
    │       ├── tests
    │       │   ├── BaseTest.java
    │       │   └── FullUserLifecycleTest.java
    │       │
    │       └── utils
    │           └── ApiPoller.java
    │
    └── resources
        └── log4j2.xml
```

### Package Overview

**models/**

Contains the Java POJOs used to serialize requests and deserialize API responses, providing type-safe interaction with the REST endpoints.

**tests/**

Contains the automated test suite. `BaseTest` provides common configuration, while `FullUserLifecycleTest` executes the complete End-to-End user scenario.

**utils/**

Contains reusable helper classes such as `ApiPoller`, responsible for waiting until asynchronous API operations reach the expected state.

---

## Running the Tests

### Execute the complete test suite

```bash
mvn clean test
```

### Execute using the TestNG suite

```bash
mvn test -DsuiteXmlFile=testng.xml
```

## Test Reporting

The framework integrates **Allure Reports** for generating detailed execution reports, including:

- Test execution status
- Execution steps
- Exceptions and stack traces
- Execution timeline

Generate the report using:

```bash
allure serve allure-results
```

---

## Continuous Integration

The project includes a GitHub Actions workflow that automatically executes the test suite on every push and pull request.

The pipeline performs:

- Project build
- Dependency resolution
- Test execution
- Report generation
- Artifact upload

This ensures that the API workflow is continuously validated and regressions are detected early.

---

## Design Principles

This framework was built with maintainability and scalability in mind by following several key principles:

- Separation of test logic from data models
- Reusable utility components
- Strongly typed request/response objects
- Clear package organization
- Minimal code duplication
- Readable and maintainable test scenarios

---

## About This Project

This project is part of my QA Automation portfolio and demonstrates my ability to design, develop, and maintain reliable API automation frameworks using industry-standard Java technologies and best practices.