Library System (Microservices)
This project is a microservices-based Library System built with Java and Spring Boot.

📦 Services
user-app → Handles users (registration, authentication)
book-service2 → Manages books (CRUD operations)
api-gateway → Routes requests to services
🛠️ Technologies
Java
Spring Boot
Spring Cloud (Eureka, Gateway)
PostgreSQL
▶️ How to Run
Start Eureka Server (if you have one)
Run services:
user-app
book-service2
api-gateway
Access API via Gateway (e.g., localhost:8085)
📌 Notes
Each service has its own database
Services communicate via REST
