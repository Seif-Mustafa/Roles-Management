# Roles-Management

![Java](https://img.shields.io/badge/Java-21-blue?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green?logo=spring)
![Maven](https://img.shields.io/badge/Maven-4.0.0-brightgreen?logo=apachemaven)
![MySQL](https://img.shields.io/badge/MySQL-8.x-blue?logo=mysql)

This project is a backend application developed using the Spring Boot framework to provide a robust system for user and role management. It exposes a set of RESTful endpoints to perform CRUD (Create, Read, Update, Delete) operations on users and their assigned roles. This serves as a foundational module for applications requiring role-based access control (RBAC).

## Key Features

*   **User Management**: Endpoints for creating, retrieving, updating, and deleting users.
*   **Role Management**: Functionality to manage application roles (CRUD).
*   **Layered Architecture**: Follows a standard Controller-Service-Repository pattern for separation of concerns and maintainability.
*   **Data Transfer Objects (DTOs)**: Utilizes DTOs and mappers to decouple the API layer from the data model.
*   **JPA/Hibernate**: Leverages Spring Data JPA for simplified data access and persistence.
*   **Docker Support**: Includes a `docker-compose.yml` for easy setup of the MySQL database.

## Technology Stack

*   **Java 21**: Core programming language.
*   **Spring Boot 3**: Framework for building the application.
*   **Spring Data JPA**: For data persistence with JPA/Hibernate.
*   **Maven**: Dependency Management.
*   **MySQL**: Relational Database.
*   **Lombok**: To reduce boilerplate code.
*   **Docker**: For containerizing the database.

## Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites

*   JDK 21 or later
*   Maven
*   Docker (Recommended for database setup)

### Installation & Setup

1.  **Clone the repository:**
    ```sh
    git clone https://github.com/Seif-Mustafa/RolesManagement.git
    ```

2.  **Navigate to the project directory:**
    ```sh
    cd RolesManagement
    ```

3.  **Set up the Database:**

    You have two options for setting up the MySQL database.

    **Option 1: Using Docker (Recommended)**

    This is the easiest way to get the database running. Make sure you have Docker installed and running on your machine.

    From the root of the project directory, run:
    ```sh
    docker-compose up -d
    ```
    This will start a MySQL container with all the necessary configurations. The database will be accessible on port `3307`. The `application.properties` file is already configured to connect to this instance.

    **Option 2: Manual MySQL Setup**

    If you prefer to use a local or remote MySQL instance, ensure it is running and then update `src/main/resources/application.properties` with your database connection details:
    ```properties
    spring.datasource.url=jdbc:mysql://YOUR_HOST:YOUR_PORT/YOUR_DATABASE
    spring.datasource.username=YOUR_USERNAME
    spring.datasource.password=YOUR_PASSWORD
    ```

4.  **Run the application:**

    You can run the application using the Maven Spring Boot plugin:
    ```sh
    ./mvnw spring-boot:run
    ```
    The application will start on the default port `8080` with the context path `/api`.

## API Endpoints

The base URL for the API is `http://localhost:8080/api`.

### Roles Management

| Method | Endpoint          | Description              |
| :----- | :---------------- | :----------------------- |
| `GET`  | `/roles`          | Get all roles            |
| `POST` | `/roles`          | Create a new role        |
| `GET`  | `/roles/{id}`     | Get a role by its ID     |
| `PUT`  | `/roles/{id}`     | Update an existing role  |
| `DELETE`| `/roles/{id}`     | Delete a role by its ID  |

### User Management

| Method | Endpoint          | Description              |
| :----- | :---------------- | :----------------------- |
| `GET`  | `/users`          | Get all users            |
| `POST` | `/users`          | Create a new user        |
| `GET`  | `/users/{id}`     | Get a user by their ID   |
| `PUT`  | `/users/{id}`     | Update an existing user  |
| `DELETE`| `/users/{id}`     | Delete a user by their ID|

## Database Schema

*(This is a placeholder for you to add your schema image. You can drag and drop the image into the GitHub editor.)*

!Database Schema
