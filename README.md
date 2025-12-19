# 🍽️ Meal Planner 
[![Java](https://img.shields.io/badge/Java-17-blue?logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-brightgreen?logo=springboot)](https://spring.io/projects/spring-boot)
[![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.1-005f0f?logo=thymeleaf)](https://www.thymeleaf.org/)
[![Bootstrap](https://img.shields.io/badge/Bootstrap-5.3-7952B3?logo=bootstrap)](https://getbootstrap.com/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?logo=mysql)](https://www.mysql.com/)

Meal Planner is a full-stack web application developed as my final diploma project for the Coding Factory Bootcamp.

It is a secure, full featured web application for managing your meals. 

It features user authentication, CRUD operations, and a responsive frontend.

---

## ✨ Features

- **🔐 User Authentication & Authorization**: Full registration and login system with Spring Security
- **🍽️ Meal Management**: Complete CRUD operations for meals
- **🏷️ Categorization**: Organize meals by type (Breakfast, Lunch, Dinner...)
- **⭐ Favorites System**: Mark meals as favorites
- **✅ Input Validation**: Server-side validation for all forms
- **📱 Responsive Frontend**: Clean UI with Thymeleaf and CSS
- **🗄️ Database Persistence**: MySQL database storage

## 🏗️ Technology Stack

- **Backend**: Spring Boot 3.5.7, Java 17
- **Security**: Spring Security 6
- **Database**: MySQL, Spring Data JPA (Hibernate)
- **Frontend**: Thymeleaf, Bootstrap 5, HTML5, CSS3, JavaScript
- **Build Tool**: Apache Maven
- **Utilities**: Project Lombok, Spring Validation

## 📁 Project Structure
```
src/main/java/com/example/Meal_Planner/
├── authentication/ # Security configuration
├── controller/ # Web controllers
├── core/ # Core application logic
│ ├── enums/ # MealType enum
│ ├── dto/ # Data Transfer Objects
│ ├── model/ # JPA Entities
│ ├── repository/ # Spring Data JPA repositories
│ ├── service/ # Business logic layer
│ └── validator/ # Custom validators
├── resources/
│ ├── static/css/ # Stylesheets
│ ├── templates/ # Thymeleaf HTML templates
│ ├── application.properties
│ └── messages.properties
└── MealPlannerApplication.java
```

## ⚙️ Configuration & Setup

### 📋 1. Prerequisites
- Java 17 or higher
- Apache Maven 3.6+
- MySQL Server (5.7 or 8.0)

## 🔧 2. Lombok Configuration

This project uses Lombok annotations. Enable annotation processing in your IDE:

- **IntelliJ:** Install Lombok plugin + Enable annotation processing in Settings
- **Eclipse:** Run lombok.jar installer
- **VS Code:** Install "Lombok Annotations Support" extension

Without proper setup, compilation may fail due to missing getters/setters.

### 3. Database Setup
Create a MySQL database:
```sql
   CREATE DATABASE meal_planner CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```
       
### 4. Configure Database @ src/main/resources/application.properties
```properties
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:${MYSQL_PORT:3306}/${MYSQL_DB:meal_planner}?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=Europe/Athens
spring.datasource.username=${MYSQL_USER:'admin'}           # Example, 'admin'
spring.datasource.password=${MYSQL_PASSWORD:'password'}    # Example, 'password'
spring.jpa.hibernate.ddl-auto=update                       # Automatic table creation
```

### 5. Build & Run
```bash
# Clone the repository:
git clone https://github.com/CRellox/Meal-Planner.git
cd Meal-Planner

# Build the project:
./mvnw clean package

# Run the application:
java -jar target/Meal-Planner-0.0.1-SNAPSHOT.jar

# Access the application at:
http://localhost:8080
```

---

## 🎯 Implementation Learnings

```markdown
This project served as a practical exploration of:
*   **Layered Architecture:** Clear separation between Controllers, Services, Repositories, and Models.
*   **Spring Security:** Implementation of custom authentication providers, user details services, and password encryption.
*   **Data Transfer Objects (DTOs):** Use of `MealInsertDTO` and `MealEditDTO` to decouple API from persistence layer.
*   **Thymeleaf Templating:** Dynamic HTML generation with fragment reuse (`header.html`, `footer.html`).
```



