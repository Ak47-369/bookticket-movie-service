# BookTicket :: Services :: Movie Service

## Overview

The **Movie Service** is a core business microservice that acts as the central catalog for all movie-related information on the BookTicket platform. It manages movie details, genres, cast, posters, and other descriptive data.

## Core Responsibilities

-   **Movie Catalog Management:** Provides CRUD (Create, Read, Update, Delete) operations for the movie catalog.
-   **Data Source:** Acts as the single source of truth for all descriptive information about movies.
-   **Search and Query:** Exposes endpoints to allow clients to search and filter the movie catalog.

## Architecture
<img width="1656" height="1052" alt="Movie-Service" src="https://github.com/user-attachments/assets/1d65e4db-99c0-47b6-9f01-1ef518a53ce6" />


### How It Works

1.  **Data Storage:** The service uses a **MongoDB** database to store movie information. The flexible, document-based nature of MongoDB is a natural fit for the rich and sometimes varied structure of movie data, allowing for easy updates to the data model without rigid schema migrations.
2.  **Authorization:** The service is protected by Spring Security. While authentication is handled by the API Gateway, the Movie Service performs its own authorization. It inspects trusted headers (e.g., `X-User-Roles`) passed from the gateway to determine if a user has the required permissions (e.g., `ADMIN`) to perform sensitive operations like adding or deleting a movie.
3.  **Service Discovery:** The Movie Service registers itself with the **Eureka Service Registry**, making it discoverable by other components in the system, primarily the API Gateway.

## Key Dependencies

-   **Spring Boot Starter Web:** For building the REST APIs.
-   **Spring Boot Starter Data MongoDB:** For interacting with the MongoDB database.
-   **Spring Boot Starter Security:** For handling role-based authorization.
-   **Eureka Discovery Client:** To register with the service registry.
-   **SpringDoc OpenAPI:** For generating API documentation.

## API Endpoints

The service's endpoints are exposed through the API Gateway under the `/api/v1/movies/**` path. All endpoints require a valid JWT for authentication.

-   `POST /api/v1/movies`: Adds a new movie to the catalog. This is a protected endpoint that requires the user to have either an `ADMIN` or `THEATER_OWNER` role.
-   `GET /api/v1/movies`: Retrieves a list of all movies available in the system.
-   `GET /api/v1/movies/{id}`: Fetches detailed information for a single movie by its unique ID.
-   `GET /api/v1/movies/now-playing`: A search endpoint that finds all movies currently playing in a specific city, based on show data from the Theater Service. This is a key endpoint for the user-facing application.
