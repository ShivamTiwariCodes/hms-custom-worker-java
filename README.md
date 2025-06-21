---

# HMS Temporal Worker

**HMS Temporal Worker** is a Spring Boot-based Temporal microservice responsible for executing asynchronous workflows and activities in the **Hospital Management System (HMS)**. It enables long-running, fault-tolerant tasks like patient processing, appointment handling, and custom SDK-based actions using Temporal.io.

---

## 📦 Features

* Java-based Temporal Workflow & Activity execution
* Custom SDK handler invocation per hospital
* Dynamic code loading (via JARs)
* Workflow-task queue based execution
* Built with Spring Boot, Temporal SDK, and Gradle

---

## 🚀 Getting Started

Follow these instructions to run the HMS Temporal Worker locally.

### ✅ Prerequisites

* Java 17+
* Gradle 7+
* [Temporal Server](https://docs.temporal.io) running locally or remotely
* MongoDB (if persisting workflow input/output)

---

### ⚙️ Setup Instructions

1. **Clone the Repository**

   ```bash
   git clone https://github.com/your-org/hms-temporal-worker.git
   cd hms-temporal-worker
   ```

2. **Configure Application Properties**

   Update `application.properties` to match your environment:

   ```properties
   spring.application.name=hmsTemporalWorker
   server.port=8083

   # Temporal Configuration
   temporal.namespace=default
   temporal.taskQueue=hms-task-queue

   # MongoDB (if required)
   spring.data.mongodb.host=localhost
   spring.data.mongodb.port=27017
   spring.data.mongodb.database=hms
   ```

3. **Build the Application**

   ```bash
   ./gradlew build
   ```

4. **Run the Worker**

   ```bash
   ./gradlew bootRun
   ```

---

## 🔁 HMS Temporal Workflow Flowchart

```mermaid
graph TD
    A[Client Service (e.g. Core)] -->|REST Call| B[WorkflowClient]
    B -->|Start Workflow| C[HmsWorkflowImpl]
    C -->|Delegates| D[CustomHandler (from SDK JAR)]
    C -->|Invokes| E[Activities Bean]
    E -->|Performs| F[External Operation or MongoDB Save]
    F -->|Returns Result| E --> C --> B --> A
```

---

## 📁 Project Structure

```text
hms-temporal-worker/
├── src/main/java/
│   ├── com/hms/worker/            # Entry point
│   ├── workflow/                  # Workflow interfaces and impl
│   ├── activity/                  # Activity interfaces and impl
│   ├── config/                    # Temporal config beans
│   ├── handler/                  # Custom JAR loader and SDK dispatching
├── src/test/                     # Unit tests
├── build.gradle                  # Gradle config
├── application.properties        # Configuration
```

---

## 🧠 Key Concepts

| Component         | Description                                                     |
| ----------------- | --------------------------------------------------------------- |
| `HmsWorkflow`     | Defines the main Temporal workflow contract                     |
| `HmsWorkflowImpl` | Implements the workflow logic; delegates to activities/handlers |
| `HmsActivities`   | Contains long-running or side-effect operations                 |
| `CustomHandler`   | SDK-based handler logic dynamically loaded per hospital         |
| `taskQueue`       | Temporal Task Queue the worker listens on                       |

---

## 🛠️ Useful Commands

| Command             | Description               |
| ------------------- | ------------------------- |
| `./gradlew build`   | Build the worker          |
| `./gradlew bootRun` | Start the Temporal worker |
| `./gradlew test`    | Run unit tests            |
| `./gradlew clean`   | Remove build artifacts    |

---

## 📚 Documentation

* **Workflow Lifecycle**: See [`docs/workflow-flow.md`](docs/workflow-flow.md)
* **Handler SDK Usage**: See [`docs/custom-handler.md`](docs/custom-handler.md)

---

## 🙋 Support

* Open an [issue](https://github.com/your-org/hms-temporal-worker/issues)
* Contact project maintainers in the `build.gradle`

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).

---