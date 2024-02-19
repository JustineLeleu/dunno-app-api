# I Dunno App Login/Register API

## Description
This API is designed to handle user registration and login for the I Dunno App. It is built using Java, SQL, Spring Boot, and Maven.

## Installation

### Prerequisites
- Java 8 or higher
- Maven
- MySQL

### Steps
1. Clone the repository
2. Add a file application-dev.properties to the resources
3. In application-dev.properties add :
    `spring.datasource.url=your_database_url`
    `spring.datasource.username=your_username`
    `spring.datasource.password=your_password`
4. Navigate to the project directory
5. Run `mvn spring-boot:run`

## Usage

### Endpoints

| Method | Endpoint       | Description                                                    | Request Body                    | Response Body |
|:-------|:---------------|:---------------------------------------------------------------|:--------------------------------|:--------------|
| POST   | /register      | Register a new user                                            | UserDto object                  | Login info    |
| POST   | /login         | Login a user                                                   | LoginDto object                 | Login info    |
| POST   | /api/user      | Add a new user                                                 | UserDto object                  | User created  |
| GET    | /api/user/{id} | Get a user                                                     | id=[UUID]                       | User object   |
| PUT    | /api/user/{id} | Update a user (add premium and calculate subscription end)     | id=[UUID], membershipId=[Short] | User updated  |
| PUT    | /api/user/{id} | Update a user (remove premium)                                 | id=[UUID]                       | User updated  |
| PUT    | /api/user/{id} | Update a user (already premium and add months to subscription) | id=[UUID], months=[Integer]     | User updated  |
| DELETE | /api/user/{id} | Delete a user                                                  | id=[UUID]                       | User deleted  |

## Team
- [Valentin](https://github.com/Valentin-Lefort)
- [Justine](https://github.com/JustineLeleu/)
