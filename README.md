# Music Provider API Project

This project provides an API for users to search for music, follow artists, and manage favorite tracks.

## Technologies

- Java 17
- Spring Framework 3.1.1
- Spring Boot 3.1.1
- Lombok
- PostgreSQL
- Redis (caching)
- Swagger (API documentation)

## Installation

1. Clone the project's GitHub repository:

`git clone https://github.com/busraciftlik/music-provider-api.git`

2. Edit the application.properties file:

`src/main/resources/application.properties`

3. Configure the PostgreSQL and Redis servers:
   <pre> spring:
        datasource:
            password: 'yourdbpw'
            username: yourdbusername
            url: jdbc:postgresql://localhost:5432/db-name
   </pre>

4. Navigate to the project's root directory and start the project by running:

`mvn spring-boot:run`

5. Access the API documentation by opening `http://localhost:8080/swagger-ui.html` in your browser.

## Usage

You can test the APIs and explore the API documentation using Swagger UI. Create user accounts, search for music, add favorite tracks, and follow other users.

