# StudyHub Backend - Individual REST API Implementation

StudyHub is a full-stack Learning Management System (LMS) my team and I are developing for our APP2000 course project, as part of our studies at the University of South-Eastern Norway.

This repo documents my submission for the second ***individual mandatory assignment*** of the course.

This assignment focuses on developing REST API endpoints with Spring Boot and implementing basic CRUD operations.  
My implementation covers user and course management, supported by a seeded dataset of 49 users (39 students, 9 instructors, 1 admin), 30 courses, and their enrollments.

This document includes project setup instructions, detailed API documentation with examples, and highlights key implementation details, to address the assignment requirements.


## Package Layout
```
studyhub-backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/studyhub/
│   │   │   │   ├── config/          # Configuration classes (e.g., CorsConfig, SecurityConfig)
│   │   │   │   ├── constant/        # Constants (e.g., RoleConstant)
│   │   │   │   ├── controller/      # REST controllers (UserController, CourseController)
│   │   │   │   ├── dto/             # Data Transfer Objects (UserDTO, CourseDTO, etc.)
│   │   │   │   ├── exception/       # Custom exceptions (e.g., UserNotFoundException)
│   │   │   │   ├── mapper/          # MapStruct mappers (e.g., CourseMapper, UserMapper)
│   │   │   │   ├── model/           # Entity classes (User, Course)
│   │   │   │   ├── repository/      # JPA repositories (UserRepository, CourseRepository)
│   │   │   │   ├── service/         # Service classes (UserService, CourseService)
│   │   │   │   ├── StudyhubBackendApplication.java  # Main application class
│   │   ├── resources/
│   │   │   ├── application.properties   # Application configuration
│   │   │   ├── data.sql                 # Database seeding script
├── pom.xml                      # Maven project file
└── README.md                    # This file
```

## Project Setup Instructions

### Prerequisites
- Java 21
- Maven 3.8+
- PostgreSQL 15+

### Local Setup

#### 1. Clone the Project
- **Option 1: Download ZIP**
    - Download the project as a `.zip` file and extract it to a directory (e.g., `studyhub-backend`).
- **Option 2: Clone via Terminal**
    - Open a terminal and run:
      ```bash
      git clone https://github.com/Lidizz/studyhub-backend.git
      ```
    - Navigate to the project folder:
      ```bash
      cd studyhub-backend
      ```
- **Option 3: Clone via IDE**
    - In your IDE (e.g., IntelliJ IDEA or VS Code):
        1. Go to `File > New > Project from Version Control` (or equivalent).
        2. Enter the URL: `https://github.com/Lidizz/studyhub-backend.git`.
        3. Clone and open the project.

#### 2. Configure PostgreSQL
- **Create the Database:**
    - **Using pgAdmin:**
        1. Open pgAdmin and connect to your PostgreSQL server.
        2. Right-click "Databases" and select "Create" -> "Database...".
        3. Enter `studyhub-db` as the database name and click "Save".
    - **Using psql (Terminal):**
        1. Open a terminal and connect to PostgreSQL:
        ```bash
        psql -U postgres
        ```
        2. Enter your password when prompted.
        3. Create the database:
        ```sql
        CREATE DATABASE 'studyhub-db';
        ```
        4. Exit psql: `\q`
- **Update Configuration:**
    - Edit `src/main/resources/application.properties` with your PostgreSQL credentials:
      ```properties
      spring.datasource.url=jdbc:postgresql://localhost:5432/studyhub-db
      spring.datasource.username=your_username
      spring.datasource.password=your_password
      spring.jpa.hibernate.ddl-auto=create-drop
      spring.sql.init.mode=always
      ```

#### 3. Run the Application
- Run the following commands in the terminal:
  ```bash
  mvn clean install
  mvn spring-boot:run
  ```
- The API will be accessible at `http://localhost:8080/api`.
- Swagger UI: `http://localhost:8080/api/swagger-ui.html`

### Database Seeding
- On startup, `data.sql` populates the database with initial data: 50 users (40 students, 9 instructors, 1 admin), 30 courses, and approximately 200 enrollments.

## API Documentation

### User Controller (`/api/users`)
| Method | Endpoint         | Description          | Request Body       | Response                        |
|--------|------------------|----------------------|--------------------|---------------------------------|
| POST   | `/register`      | Register a new user  | `UserDTO`          | `200 OK` with `UserResponseDTO`  |
| POST   | `/login`         | Authenticate a user  | `LoginRequestDTO`  | `200 OK` with `UserResponseDTO` |
| GET    | `/{id}`          | Get user by ID       | -                  | `200 OK` with `UserResponseDTO` |
| GET    | `/email/{email}` | Get user by email    | -                  | `200 OK` with `UserResponseDTO` |
| PUT    | `/{id}`          | Update user details  | `UserUpdateDTO`    | `200 OK` with `UserResponseDTO` |
| DELETE | `/{id}`          | Delete a user        | -                  | `204 No Content`                |


### Course Controller (`/api/courses`)
| Method | Endpoint                         | Description                       | Request Body       | Response                       |
|--------|----------------------------------|-----------------------------------|--------------------|--------------------------------|
| POST   | `/`                             | Create a new course               | `CourseCreateDTO`  | `200 OK` with `CourseDTO`      |
| GET    | `/{id}`                         | Get course by ID                  | -                  | `200 OK` with `CourseDTO`      |
| GET    | `/department/{dept}`            | Get courses by department         | -                  | `200 OK` with `List<CourseDTO>`|
| GET    | `/department/{dept}/summary`    | Get summarized courses by department | -              | `200 OK` with `List<CourseSummaryDTO>` |
| GET    | `/student/{studentId}`          | Get courses for a student         | -                  | `200 OK` with `List<CourseDTO>`|
| GET    | `/student/{studentId}/summary`  | Get summarized courses for a student | -              | `200 OK` with `List<CourseSummaryDTO>` |
| POST   | `/enroll`                       | Enroll a student in a course      | `EnrollmentDTO`    | `200 OK`                       |
| PUT    | `/{id}`                         | Update a course                   | `CourseUpdateDTO`  | `200 OK` with `CourseDTO`      |
| DELETE | `/{id}`                         | Delete a course                   | -                  | `204 No Content`               |

## API Usage Examples

### Using Postman

#### User Controller
1. **Register a User (POST /api/users/register)**
    - **Method**: POST
    - **URL**: `http://localhost:8080/api/users/register`
    - **Headers**: `Content-Type: application/json`
    - **Body (raw/JSON)**:
      ```json
      {
        "email": "new.student@example.com",
        "firstName": "New",
        "lastName": "Student",
        "role": "STUDENT",
        "password": "password123"
      }
      ```
    - **Expected Response**: `200 OK` with `UserResponseDTO`:
      ```json
      {
        "id": 50,
        "email": "new.student@example.com",
        "firstName": "New",
        "lastName": "Student",
        "role": "STUDENT"
      }
      ```

2. **Login (POST /api/users/login)**
    - **Method**: POST
    - **URL**: `http://localhost:8080/api/users/login`
    - **Headers**: `Content-Type: application/json`
    - **Body (raw/JSON)**:
      ```json
      {
        "email": "john.doe@example.com",
        "password": "password123"
      }
      ```
    - **Expected Response**: `200 OK` with `UserResponseDTO`:
      ```json
      {
        "id": 1,
        "email": "john.doe@example.com",
        "firstName": "John",
        "lastName": "Doe",
        "role": "STUDENT"
      }
      ```

3. **Get User by ID (GET /api/users/{id})**
    - **Method**: GET
    - **URL**: `http://localhost:8080/api/users/1`
    - **Expected Response**: `200 OK` with `UserResponseDTO`:
      ```json
      {
        "id": 1,
        "email": "john.doe@example.com",
        "firstName": "John",
        "lastName": "Doe",
        "role": "STUDENT"
      }
      ```
    - **Error**: `404 Not Found` if user doesn’t exist.

4. **Get User by Email (GET /api/users/email/{email})**
    - **Method**: GET
    - **URL**: `http://localhost:8080/api/users/email/john.doe@example.com`
    - **Expected Response**: `200 OK` with `UserResponseDTO`:
      ```json
      {
        "id": 1,
        "email": "john.doe@example.com",
        "firstName": "John",
        "lastName": "Doe",
        "role": "STUDENT"
      }
      ```
    - **Error**: `404 Not Found` if user doesn’t exist.

5. **Update User (PUT /api/users/{id})**
    - **Method**: PUT
    - **URL**: `http://localhost:8080/api/users/1`
    - **Headers**: `Content-Type: application/json`
    - **Body (raw/JSON)**:
      ```json
      {
        "firstName": "UpdatedFirstName"
      }
      ```
    - **Expected Response**: `200 OK` with updated `UserResponseDTO`:
      ```json
      {
        "id": 1,
        "email": "john.doe@example.com",
        "firstName": "UpdatedFirstName",
        "lastName": "Doe",
        "role": "STUDENT"
      }
      ```

6. **Delete User (DELETE /api/users/{id})**
    - **Method**: DELETE
    - **URL**: `http://localhost:8080/api/users/1`
    - **Expected Response**: `204 No Content`
    - **Error**: `404 Not Found` if user doesn’t exist.

#### Course Controller
1. **Create a Course (POST /api/courses)**
    - **Method**: POST
    - **URL**: `http://localhost:8080/api/courses`
    - **Headers**: `Content-Type: application/json`
    - **Body (raw/JSON)**:
      ```json
      {
        "code": "CS50Xi",
        "department": "Computer Science",
        "title": "Advanced Programming",
        "credits": 3
      }
      ```
    - **Expected Response**: `200 OK` with `CourseDTO`:
      ```json
      {
        "id": 31,
        "code": "CS50Xi",
        "department": "Computer Science",
        "title": "Advanced Programming",
        "credits": 3,
        "description": null,
        "startDate": null,
        "endDate": null,
        "instructorIds": [],
        "studentIds": []
      }
      ```

2. **Get Course by ID (GET /api/courses/{id})**
    - **Method**: GET
    - **URL**: `http://localhost:8080/api/courses/5`
    - **Expected Response**: `200 OK` with `CourseDTO`:
      ```json
      {
        "id": 5,
        "code": "CS401",
        "department": "Computer Science",
        "title": "Operating Systems",
        "credits": 4,
        "description": "OS concepts and design",
        "startDate": "2026-09-01",
        "endDate": "2026-12-15",
        "instructorIds": [15],
        "studentIds": [48, 37, 43, 31]
      }
      ```
    - **Error**: `404 Not Found` if course doesn’t exist.

3. **Enroll a Student (POST /api/courses/enroll)**
    - **Method**: POST
    - **URL**: `http://localhost:8080/api/courses/enroll`
    - **Headers**: `Content-Type: application/json`
    - **Body (raw/JSON)**:
      ```json
      {
        "courseId": 1,
        "studentId": 5
      }
      ```
    - **Expected Response**: `200 OK`
    - **Error**: `400 Bad Request` if enrollment fails.

4. **Get Courses by Department (GET /api/courses/department/{department})**
    - **Method**: GET
    - **URL**: `http://localhost:8080/api/courses/department/Computer%20Science`
    - **Expected Response**: `200 OK` with `List<CourseDTO>` (example truncated):
      ```json
      [
        {
          "id": 1,
          "code": "CS101A",
          "department": "Computer Science",
          "title": "Intro to Programming",
          "credits": 3
        }
      ]
      ```

5. **Get Courses for a Student (GET /api/courses/student/{studentId})**
    - **Method**: GET
    - **URL**: `http://localhost:8080/api/courses/student/1`
    - **Expected Response**: `200 OK` with `List<CourseDTO>` (example truncated):
      ```json
      [
        {
          "id": 1,
          "code": "CS101A",
          "department": "Computer Science",
          "title": "Intro to Programming",
          "credits": 3,
          "description": "Introduction to coding concepts",
          "startDate": "2025-01-15",
          "endDate": "2025-05-15",
          "instructorIds": [6],
          "studentIds": [1, 4, 7]
        }
      ]
      ```
    - **Error**: `404 Not Found` if student or courses don’t exist; `400 Bad Request` if user isn’t a student.

6. **Get Summarized Courses for a Student (GET /api/courses/student/{studentId}/summary)**
    - **Method**: GET
    - **URL**: `http://localhost:8080/api/courses/student/1/summary`
    - **Expected Response**: `200 OK` with `List<CourseSummaryDTO>` (example truncated):
      ```json
      [
        {
          "id": 1,
          "code": "CS101A",
          "department": "Computer Science",
          "title": "Intro to Programming",
          "credits": 3
        }
      ]
      ```
    - **Error**: `404 Not Found` if student or courses don’t exist; `400 Bad Request` if user isn’t a student.

7. **Get Summarized Courses by Department (GET /api/courses/department/{department}/summary)**
    - **Method**: GET
    - **URL**: `http://localhost:8080/api/courses/department/Computer%20Science/summary`
    - **Expected Response**: `200 OK` with `List<CourseSummaryDTO>` (example truncated):
      ```json
      [
        {
          "id": 1,
          "code": "CS101A",
          "department": "Computer Science",
          "title": "Intro to Programming",
          "credits": 3
        }
      ]
      ```

8. **Update a Course (PUT /api/courses/{id})**
    - **Method**: PUT
    - **URL**: `http://localhost:8080/api/courses/1`
    - **Headers**: `Content-Type: application/json`
    - **Body (raw/JSON)**:
      ```json
      {
        "title": "Updated Course Title"
      }
      ```
    - **Expected Response**: `200 OK` with updated `CourseDTO`:
      ```json
      {
        "id": 1,
        "code": "CS101A",
        "department": "Computer Science",
        "title": "Updated Course Title",
        "credits": 3
      }
      ```

9. **Delete a Course (DELETE /api/courses/{id})**
    - **Method**: DELETE
    - **URL**: `http://localhost:8080/api/courses/1`
    - **Expected Response**: `204 No Content`
    - **Error**: `404 Not Found` if course doesn’t exist.

### Using Swagger UI
- Open Swagger UI: `http://localhost:8080/api/swagger-ui.html`.
- Expand the desired endpoint (e.g., `/api/users/{id}`).
- Click "Try it out".
- Enter required parameters (e.g., `id = 1`).
- Click "Execute".
- View the request URL, response body, and status code.
- Use the JSON examples from the Postman section to test requests.
