# Task Management REST API

A robust RESTful API for task management built with **Spring Boot**, **Spring Data JPA**, and **MySQL**. This application provides complete task lifecycle management with input validation, status filtering, pagination, sorting, and centralized exception handling.

---

## 🚀 Features

- **Full CRUD Operations**: Create, read, update, and delete tasks seamlessly.
- **Status Filtering**: Filter tasks by status (`TODO`, `IN_PROGRESS`, `DONE`) with case-insensitive matching.
- **Pagination & Sorting**: Efficient pagination and sorting support via Spring Data (`/api/tasks/page`).
- **Input Validation**: Strict request body validation using Jakarta Validation (`@Valid`, `@NotBlank`, `@Pattern`).
- **Centralized Error Handling**: Standardized JSON error responses for `404 Not Found` and `400 Bad Request` validation errors via `@RestControllerAdvice`.
- **Database Persistence**: Automatic schema management and ORM with Spring Data JPA & Hibernate connected to MySQL.

---

## 🛠️ Tech Stack

- **Java**: 17
- **Framework**: Spring Boot
  - Spring Web MVC
  - Spring Data JPA
  - Spring Boot Starter Validation
- **Database**: MySQL
- **Build Tool**: Maven (with Maven Wrapper included)

---

## 📁 Project Structure

```text
src/main/java/taskmanagement/
├── TaskmanagementApplication.java  # Main application entry point
├── controller/
│   └── TaskController.java         # REST endpoints for task operations
├── dto/
│   └── TaskRequest.java            # Request DTO with validation annotations
├── exception/
│   ├── GlobalExceptionHandler.java # Centralized exception handling
│   └── TaskNotFoundException.java  # Custom 404 exception
├── model/
│   └── Task.java                   # JPA entity representing the Task table
├── repository/
│   └── TaskRepository.java         # Spring Data JPA repository
└── service/
    └── TaskService.java            # Business logic and transaction management
```

---

## ⚙️ Prerequisites & Setup

### 1. Prerequisites
- **Java Development Kit (JDK)**: Version 17 or higher
- **MySQL Server**: Running on `localhost:3306`

### 2. Database Configuration
Create the MySQL database:
```sql
CREATE DATABASE task_management;
```

Update your database credentials in `src/main/resources/application.properties` if needed:
```properties
spring.application.name=taskmanagement
spring.datasource.url=jdbc:mysql://localhost:3306/task_management
spring.datasource.username=root
spring.datasource.password=your_password_here

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 🏃 Running the Application

### Using Maven Wrapper (macOS/Linux)
```bash
./mvnw clean spring-boot:run
```

### Using Maven Wrapper (Windows)
```cmd
mvnw.cmd clean spring-boot:run
```

The API will be available at: `http://localhost:8080/api/tasks`

### Build JAR Package
```bash
./mvnw clean package
java -jar target/taskmanagement-0.0.1-SNAPSHOT.jar
```

---

## 📡 API Endpoints

### Summary Table

| HTTP Method | Endpoint | Description | Query Parameters |
|---|---|---|---|
| `GET` | `/api/tasks` | Get all tasks | `status` (optional: `TODO`, `IN_PROGRESS`, `DONE`) |
| `GET` | `/api/tasks/page` | Get paginated tasks | `status`, `page` (default: 0), `size` (default: 5), `sort` |
| `GET` | `/api/tasks/{id}` | Get task by ID | None |
| `POST` | `/api/tasks` | Create a new task | None |
| `PUT` | `/api/tasks/{id}` | Update an existing task | None |
| `DELETE` | `/api/tasks/{id}` | Delete a task by ID | None |

---

### Request & Response Examples

#### 1. Create a Task
**Request**:
- **Method**: `POST`
- **URL**: `http://localhost:8080/api/tasks`
- **Headers**: `Content-Type: application/json`
- **Body**:
  ```json
  {
    "title": "Complete project documentation",
    "status": "TODO"
  }
  ```

*Allowed `status` values:* `TODO`, `IN_PROGRESS`, `DONE`.

**Response** (`201 Created`):
```json
{
  "title": "Complete project documentation",
  "status": "TODO",
  "id": 1
}
```

---

#### 2. Get All Tasks (with optional status filter)
**Request**:
- **Method**: `GET`
- **URL**: `http://localhost:8080/api/tasks?status=TODO`

**Response** (`200 OK`):
```json
[
  {
    "id": 1,
    "title": "Complete project documentation",
    "status": "TODO"
  }
]
```

---

#### 3. Get Paginated Tasks
**Request**:
- **Method**: `GET`
- **URL**: `http://localhost:8080/api/tasks/page?page=0&size=5&sort=id,asc`

**Response** (`200 OK`):
```json
{
  "content": [
    {
      "id": 1,
      "title": "Complete project documentation",
      "status": "TODO"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 5,
    "sort": {
      "empty": false,
      "sorted": true,
      "unsorted": false
    },
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "totalElements": 1,
  "totalPages": 1,
  "last": true,
  "size": 5,
  "number": 0,
  "first": true,
  "numberOfElements": 1,
  "empty": false
}
```

---

#### 4. Get Task by ID
**Request**:
- **Method**: `GET`
- **URL**: `http://localhost:8080/api/tasks/1`

**Response** (`200 OK`):
```json
{
  "id": 1,
  "title": "Complete project documentation",
  "status": "TODO"
}
```

---

#### 5. Update a Task
**Request**:
- **Method**: `PUT`
- **URL**: `http://localhost:8080/api/tasks/1`
- **Headers**: `Content-Type: application/json`
- **Body**:
  ```json
  {
    "title": "Complete project documentation",
    "status": "IN_PROGRESS"
  }
  ```

**Response** (`200 OK`):
```json
{
  "id": 1,
  "title": "Complete project documentation",
  "status": "IN_PROGRESS"
}
```

---

#### 6. Delete a Task
**Request**:
- **Method**: `DELETE`
- **URL**: `http://localhost:8080/api/tasks/1`

**Response** (`204 No Content`)

---

## ⚠️ Error Responses

### Validation Error (`400 Bad Request`)
Returned when required fields are missing or invalid:
```json
{
  "message": "Validation failed",
  "errors": {
    "status": "Status must be TODO, IN_PROGRESS, or DONE",
    "title": "Task title is required"
  }
}
```

### Task Not Found (`404 Not Found`)
Returned when querying, updating, or deleting a non-existent task ID:
```json
{
  "message": "Task not found with ID: 999"
}
```

---

## 📄 License
This project is open-source and available under the [MIT License](LICENSE).
