# Foody – Restaurant Web Application (Backend)

Foody is a **backend-only restaurant web application** developed using **Java Spring Boot**.  
This project was developed as a learning-focused individual project to understand real-world backend architecture, authentication, and role-based access control.
---
### Features
- User authentication and authorization
- Role-based access control (USER / ADMIN)
- Secure REST APIs using Spring Security
- Custom `UserDetailsService` with Spring Data JPA
- Password encryption using BCrypt
- Clean and modular backend architecture
---
### 🛠 Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Security
- Spring Data JPA
- Maven
- MySQL

---
### Security Implementation

Spring Security is used to secure the application.
- Custom `UserDetailsService` to load users from the database
- URL-level and method-level authorization
- Password hashing using `BCryptPasswordEncoder`
- HTTP Basic Authentication (can be extended to JWT)

