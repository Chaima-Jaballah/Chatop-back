## ReadMe File ##

# ChâTop Backend

## Technologies Used

- Java 17
- Spring Boot 3.4.4
- Spring Security
- Spring Data JPA
- JWT (Java Web Token)
- Lombok
- MySQL
- Maven
- Swagger / OpenAPI 3

---

## Install & Run the Project

### 1. Clone the repository

git clone https://github.com/your-username/chatop-backend.git  
cd chatop-backend

### 2. Configure your database connection

Edit the file: src/main/resources/application.properties  
Update the following lines:

spring.datasource.url=jdbc:mysql://localhost:3306/<your_database_name>  
spring.datasource.username=<your_database_username>  
spring.datasource.password=<your_password>

### 3. Run the application

With Spring Tool Suite or Eclipse:  
Right-click the project → Run As → Spring Boot App

Or using terminal:

./mvnw spring-boot:run

---

## Database Setup

### 1. Make sure MySQL is installed and running.

### 2. Create your database:

CREATE DATABASE your_database_name;

---

## Swagger Documentation

Once the application is running, visit the URL below in your browser to view the API docs:

http://localhost:8080/swagger-ui/index.html#/
