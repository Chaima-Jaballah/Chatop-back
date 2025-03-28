## ReadMe File ##

# ChaTop Backend

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
```bash
git clone https://github.com/Chaima-Jaballah/Chatop-back.git  
cd chatop-backend
```

### 2. Configure your database connection

The database connection in this project uses environment variables.
You need to set the following variables:
DB_URL
DB_USERNAME
DB_PASSWORD

These variables are already configured in the application.properties file.
You can choose one of the following methods:
#### Method 1 – Set environment variables (Windows CMD):
```bash
set DB_URL=jdbc:mysql://localhost:3306/<your_database_name> 
set DB_USERNAME=<your_database_username>
set DB_PASSWORD=<your_password>
mvn spring-boot:run
```

**For Bash/Linux/Mac:**
```bash
export DB_URL=jdbc:mysql://localhost:3306/<your_database_name>
export DB_USERNAME=<your_database_username>
export DB_PASSWORD=<your_password>
mvn spring-boot:run
```

#### Method 2 – Set environment variables in one line (Windows CMD):
```bash
set DB_URL=jdbc:mysql://localhost:3306/<your_database_name> && set DB_USERNAME=<your_database_username> && set DB_PASSWORD=<your_password> && mvn spring-boot:run
```

**For Bash/Linux/Mac:**
```bash
DB_URL=jdbc:mysql://localhost:3306/<your_database_name> DB_USERNAME=<your_database_username> DB_PASSWORD=<your_password> mvn spring-boot:run
```

#### Method 3 – Pass variables as system properties:
**CMD / Bash**
```bash
mvn spring-boot:run -Dspring.datasource.url=jdbc:mysql://localhost:3306/<your_database_name> -Dspring.datasource.username=<your_database_name> -Dspring.datasource.password=<your_database_name>
```


### 3. Run the application

With Spring Tool Suite or Eclipse:  
Right-click the project → Run As → Spring Boot App

Or using terminal:
```bash
./mvnw spring-boot:run
```

---

## Database Setup

### 1. Make sure MySQL is installed and running:
You can download and install MySQL Community Server from the official website:
https://dev.mysql.com/downloads/mysql/

After installation:
- Start MySQL server.
- Note your MySQL username and password (default: `root` / no password or your custom password).

### 2. Create your database:
Open a terminal (CMD or Bash) and connect to MySQL:

**On Windows CMD or Bash/Linux/Mac:**
```bash
mysql -u root -p
```

Enter your password, then run:
```bash
CREATE DATABASE your_database_name;
```

---

## Swagger Documentation

Once the application is running, visit the URL below in your browser to view the API docs:

http://localhost:3001/swagger-ui/index.html#/
