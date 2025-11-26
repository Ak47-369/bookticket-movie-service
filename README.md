# BookTicket :: Services :: Movie Service

## Overview

The **Movie Service** is a core business microservice that acts as the central catalog for all movie-related information on the BookTicket platform. It manages movie details, genres, cast, posters, and other descriptive data.

## Core Responsibilities

-   **Movie Catalog Management:** Provides CRUD (Create, Read, Update, Delete) operations for the movie catalog.
-   **Data Source:** Acts as the single source of truth for all descriptive information about movies.
-   **Search and Query:** Exposes endpoints to allow clients to search and filter the movie catalog.

## Architecture
<img width="1190" height="1010" alt="Movie Service-2025-11-26-201412" src="https://github.com/user-attachments/assets/dc21b74d-58cb-4200-b47a-59c053f59609" />


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

The service's endpoints are exposed through the API Gateway under the `/api/v1/movies/**` path. Key operations include:

-   `GET /api/v1/movies`: Fetches a list of all movies.
-   `GET /api/v1/movies/{id}`: Fetches details for a specific movie.
-   `POST /api/v1/movies`: Adds a new movie to the catalog (requires `ADMIN` role).
