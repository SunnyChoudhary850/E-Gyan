EGyan — Institute of Excellence 🎓
A full-stack Learning Management System (LMS) built with Spring Boot and vanilla JavaScript, connecting passionate educators with curious students.
![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5-brightgreen?style=flat-square&logo=springboot)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=flat-square&logo=mysql)
![JavaScript](https://img.shields.io/badge/JavaScript-ES6-yellow?style=flat-square&logo=javascript)
---
🚀 Features
👨‍🎓 Student
Register with OTP email verification
Browse and enroll in courses
Access and download course materials (PDF, DOCX, PPTX)
Watch video lectures in-browser
👨‍🏫 Faculty
Create and manage courses
Upload multimedia materials (PDF, MP4, DOCX) up to 500MB
Edit course descriptions
View enrolled students
🛡️ Admin
View dashboard with live stats (students, faculty, courses)
Manage and delete users
Manage and delete courses
Send platform-wide notifications
---
🛠️ Tech Stack
Layer	Technology
Backend	Java 17, Spring Boot 3.5
Security	Spring Security, BCrypt
Database	MySQL 8.0, Spring Data JPA
Email	JavaMailSender, Gmail SMTP
Frontend	HTML5, CSS3, Vanilla JavaScript
Build Tool	Maven
---
📁 Project Structure
```
egyan/
├── src/main/java/com/egyan/
│   ├── config/          # Security & CORS config
│   ├── controller/      # REST API controllers
│   ├── entity/          # JPA entities
│   ├── exception/       # Global exception handling
│   ├── repository/      # JPA repositories
│   └── service/         # Business logic
├── src/main/resources/
│   └── static/
│       ├── index.html   # Landing page
│       ├── egyan.html   # Main app portal
│       └── Admin.html   # Admin panel
└── pom.xml
```
---
⚙️ Setup & Installation
Prerequisites
Java 17+
MySQL 8.0+
Maven 3.8+
Gmail account with App Password enabled
1. Clone the repository
```bash
git clone https://github.com/SunnyChoudhary850/E-Gyan.git
cd E-Gyan
```
2. Create MySQL database
```sql
CREATE DATABASE egyan;
```
3. Configure application properties
Create `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/egyan
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
spring.jpa.hibernate.ddl-auto=update

spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_gmail@gmail.com
spring.mail.password=your_gmail_app_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```
4. Run the application
```bash
mvn spring-boot:run
```
5. Access the application
Portal	URL
Landing Page	http://localhost:8080
Student/Faculty Portal	http://localhost:8080/egyan
Admin Panel	http://localhost:8080/admin
---
🔑 Default Admin Setup
To create an admin account, directly insert into the database:
```sql
INSERT INTO user (name, email, password, role)
VALUES ('Admin', 'admin@egyan.com', '<bcrypt_hashed_password>', 'ADMIN');
```
---
📧 Email Features
Automated HTML emails are triggered for:
✅ Student/Faculty registration welcome
✅ OTP verification on signup
✅ Course enrollment confirmation
✅ Course creation confirmation
---
📸 Screenshots
> Landing Page, Student Portal, Faculty Dashboard, Admin Panel
(Add screenshots here)
---
🔗 API Endpoints
Users
Method	Endpoint	Description
POST	`/users`	Register user
POST	`/users/login`	Login
POST	`/users/send-otp`	Send OTP
POST	`/users/verify-otp`	Verify OTP
DELETE	`/users/{id}`	Delete user
Courses
Method	Endpoint	Description
GET	`/courses`	Get all courses
POST	`/courses/{teacherId}`	Create course
PUT	`/courses/{courseId}/enroll/{studentId}`	Enroll student
PUT	`/courses/{courseId}/teacher/{teacherId}/update`	Update course
DELETE	`/courses/{courseId}/teacher/{teacherId}`	Delete course
Materials
Method	Endpoint	Description
GET	`/materials/{courseId}`	Get course materials
POST	`/materials/{courseId}`	Upload material
GET	`/materials/download/{id}`	Download material
DELETE	`/materials/{id}`	Delete material
---
👨‍💻 Author
Er. Sunny Choudhary
📧 royalsunny850@gmail.com
🔗 LinkedIn
🐙 GitHub
📍 Gorakhpur, UP, India
---
📄 License
This project is open source and available under the MIT License.
---
<p align="center">Built with ❤️ by Er. Sunny Choudhary</p>
