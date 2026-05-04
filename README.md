# EGyan — Online Learning Platform

A full-stack online learning platform where **Faculty** can create courses and upload materials, and **Students** can browse and enroll in courses.

---

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Frontend | HTML, CSS, JavaScript (Vanilla) |
| Backend | Java, Spring Boot |
| Database | MySQL |
| ORM | Spring Data JPA (Hibernate) |
| Security | BCrypt Password Encoding |

---

## Features

- User registration and login (Student / Faculty roles)
- Faculty can create and delete courses
- Faculty can upload course materials (Videos, Documents)
- Students can browse all courses and enroll
- Students and Faculty can view course materials and download them
- Role-based UI — different views for Student vs Faculty

---

## Project Structure

```
egyan/
├── controller/
│   ├── CourseController.java
│   ├── CourseMaterialController.java
│   └── UserController.java
├── entity/
│   ├── Course.java
│   ├── CourseMaterial.java
│   └── User.java
├── exception/
│   ├── EmailAlreadyExistsException.java
│   └── GlobalExceptionHandler.java
├── repository/
│   ├── CourseRepository.java
│   ├── CourseMaterialRepository.java
│   └── UserRepository.java
├── service/
│   ├── CourseService.java
│   ├── CourseMaterialService.java
│   └── UserService.java
└── EgyanApplication.java

frontend/
└── index.html
```

---

## Database Setup

1. Open MySQL and create the database:

```sql
CREATE DATABASE egyan;
```

2. The tables will be created automatically when you run the application (JPA `ddl-auto=update`).

---

## How to Run

### Backend (Spring Boot)

1. Make sure MySQL is running on your machine.
2. Open `src/main/resources/application.properties` and update if needed:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/egyan
spring.datasource.username=root
spring.datasource.password=root123
```

3. Run the application:

```bash
./mvnw spring-boot:run
```

The backend will start at `http://localhost:8080`

### Frontend

Just open `index.html` directly in your browser. No extra setup needed.

---

## API Endpoints

### Users
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/users` | Register a new user |
| POST | `/users/login` | Login |

### Courses
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/courses` | Get all courses |
| POST | `/courses/{teacherId}` | Create a course |
| PUT | `/courses/{courseId}/enroll/{studentId}` | Enroll a student |
| GET | `/courses/teacher/{teacherId}` | Get courses by teacher |
| GET | `/courses/student/{studentId}` | Get courses by student |
| DELETE | `/courses/{courseId}/teacher/{teacherId}` | Delete a course |

### Materials
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/materials/{courseId}` | Upload a material |
| GET | `/materials/{courseId}` | Get materials for a course |
| GET | `/materials/download/{materialId}` | Download a material |
| DELETE | `/materials/{materialId}` | Delete a material |

---

## User Roles

| Role | Permissions |
|------|------------|
| `STUDENT` | Browse courses, enroll, view and download materials |
| `FACULTY` | Create/delete courses, upload materials, view enrolled students |

---

## File Uploads

Uploaded files are saved in an `uploads/` folder in the root of the project directory. Supported file types:

- Videos (`mp4`, etc.)
- Documents (`pdf`, `doc`, `docx`, `ppt`, `pptx`)

Max file size is set to **500MB** in `application.properties`.

---

## Notes

- Passwords are encrypted using **BCrypt** before being stored in the database.
- The frontend connects to the backend at `http://localhost:8080` — make sure the backend is running before using the UI.
- This project was built as a beginner-level full-stack Java project.
