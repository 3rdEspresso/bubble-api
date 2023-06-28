# Bubble API

This project is a Spring Boot application that provides a REST API for the Bubble application.

## Prerequisites

- Java 17 (Amazon Corretto)
- Gradle
- Docker
- Docker Compose

## Getting Started
### Local Development
1. Clone the repository:

   ```bash
   git clone https://github.com/3rdEspresso/bubble-api.git
   ```
2. Navigate into the directory:

   ```bash
   cd bubble-api
   ```
3. Build the project:

    ```bash
    ./gradlew clean build
    ```

4. Run the application:

    ```bash
    ./gradlew bootRun
    ```

### Running using Docker Compose
1. Clone the repository:

   ```bash
   git clone https://github.com/3rdEspresso/bubble-api.git
    ```
2. Navigate into the directory:

   ```bash
   cd bubble-api
   ```
3. Run docker compose:

   ```bash
   docker compose -f app.yml -f compose.yaml up -d --build
   ```

## Using the Application

Navigate to [http://localhost:8080/graphiql](http://localhost:8080/graphiql) to view the Interactive GraphQL.