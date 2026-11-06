# SightseeingApp

A REST API for discovering tourist attractions, saving favourites, writing reviews and keeping travel journals.

🏆 **Winning project of the TIS Grupa Java/Spring Academy 2026 hackathon** (Zagreb, July 2026)

---

## About

SightseeingApp was built as a team project during a time-limited hackathon at the end of the TIS Java/Spring Academy. The task was to implement as many user stories as possible for an attraction-browsing app, following a given API specification, with strict rules on validation and error handling. Using LLMs to generate code was not allowed.

After the hackathon I kept working on the project, mainly refactoring and fixing the attraction endpoints.

## My contribution

- **User module (hackathon)**: designed and implemented `UserController` and the whole user flow behind it: creating users with validation (unique email, email format, ISO country code), fetching user details, and managing favourite attractions.
- **Travel journal (hackathon)**: helped a teammate with `TravelJournalController` and finished it (creating, updating and fetching travel journals).
- **Attractions (after the hackathon)**: refactored and fixed `AttractionController` and related logic.

## Features

- List attractions by city
- Attraction details with reviews, optional date filtering and an option to exclude reviews
- Rate and review attractions (rating 1–5)
- Upload and download attraction pictures
- Create users and fetch user details
- Save and list a user's favourite attractions
- Create, update and fetch travel journals
- Consistent error responses (`message`, `timestamp`, `UUID`), with every failed request logged to the database
- Web pages for browsing and adding attractions (Thymeleaf)

## Tech stack

- Java 25
- Spring Boot 4 (Web, Data JPA, Validation, Thymeleaf)
- H2 database
- MapStruct for DTO mapping
- springdoc-openapi (Swagger UI)
- Maven

## API overview

| Method | Endpoint | Description |
|---|---|---|
| GET | `/attractions/{location}` | List attractions in a city |
| GET | `/attractions/{location}/{attractionUrlName}` | Attraction details and reviews |
| POST | `/attraction/review` | Review an attraction |
| POST | `/attraction/{location}/{attractionUrlName}/picture` | Upload a picture |
| GET | `/attraction/{location}/{attractionUrlName}/picture/{id}` | Download a picture |
| POST | `/user` | Create a user |
| GET | `/user/{userId}` | Get user details |
| POST | `/user/{userId}/favourites` | Add a favourite attraction |
| GET | `/user/{userId}/favorites` | List favourite attractions |
| POST | `/travel-journal/{userId}` | Create a travel journal |
| PATCH | `/travel-journal/{id}` | Update a travel journal |
| GET | `/travel-journal/{id}` | Get a travel journal |

Full interactive documentation is available in Swagger UI at `/swagger-ui.html` when the app is running.
