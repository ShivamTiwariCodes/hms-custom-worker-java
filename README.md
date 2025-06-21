
---

# HMS Auth Service

**HMS Auth Service** is a Spring Boot microservice responsible for managing authentication in the **Hospital Management System (HMS)**. It issues and validates JWT tokens, enabling secure, stateless access across other HMS services.

---

## 📦 Features

* User login and JWT issuance
* Token validation and expiration handling
* Stateless authentication with Spring Security
* Configurable MySQL and MongoDB integrations
* Thymeleaf support (UI templates, if used)
* Built with Spring Boot and Gradle

---

## 🚀 Getting Started

Follow these instructions to run the Auth Service locally.

### ✅ Prerequisites

* Java 17+ (or Java 11+)
* Gradle 7+
* MySQL (running on `localhost:3306`)
* MongoDB (running on `localhost:27017`)

---

### ⚙️ Setup Instructions

1. **Clone the Repository**

   ```bash
   git clone https://github.com/your-org/hms-auth-service.git
   cd hms-auth-service
   ```

2. **Configure Application Properties**

   The application uses Spring’s default property files. Below is the essential config (`application.properties` or `.yml`):

   ```properties
   spring.application.name=authService
   server.port=8081

   # MySQL Database Configuration
   spring.datasource.url=jdbc:mysql://localhost:3306/hms
   spring.datasource.username=root
   spring.datasource.password=root
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true

   # MongoDB Configuration
   spring.data.mongodb.host=localhost
   spring.data.mongodb.port=27017
   spring.data.mongodb.database=hms

   # Thymeleaf
   spring.thymeleaf.cache=false
   ```

3. **Build the Application**

   ```bash
   ./gradlew build
   ```

4. **Run the Service**

   ```bash
   ./gradlew bootRun
   ```

---

## 📁 Project Structure

```text
hms-auth-service/
├── src/main/java/
│   ├── com/hms/auth/         # Main package
│   ├── config/               # JWT and Spring Security configuration
│   ├── controller/           # REST endpoints
│   ├── service/              # Auth logic and token generation
│   ├── model/                # DTOs, entities
├── src/test/                # Unit and integration tests
├── build.gradle             # Gradle build script
├── application.properties   # Application configuration
```

---

## 🔐 Authentication Flow

1. `POST /auth/login`: Accepts credentials and issues JWT
2. `Authorization: Bearer <token>`: Token passed in header for subsequent requests
3. Spring Security filter validates the token and sets user context
4. Optional: `POST /auth/refresh` if refresh token support is added

---

## 🛠️ Useful Commands

| Command             | Description                  |
| ------------------- | ---------------------------- |
| `./gradlew build`   | Build the application        |
| `./gradlew bootRun` | Run locally with Spring Boot |
| `./gradlew test`    | Run all tests                |
| `./gradlew clean`   | Clean build artifacts        |

---

## 📚 Documentation

* **API Reference**: See [`docs/api.md`](docs/api.md)
* **Auth Flow Diagram**: See [`docs/auth-flow.md`](docs/auth-flow.md)

---

## 🙋 Support

* Open an [issue](https://github.com/your-org/hms-auth-service/issues)
* Contact project maintainers (see `build.gradle`)

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).

---

Let me know if you'd like to include:

* Swagger/OpenAPI docs
* OAuth or refresh token support
* CI/CD or Docker setup instructions

Ready to move to the next README?
