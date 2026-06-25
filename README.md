# 🚀 Order Notification Demo
![Java](https://img.shields.io/badge/Java-17-orange)

![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2-green)

![Kafka](https://img.shields.io/badge/Kafka-Event_Driven-black)

![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)

![Docker](https://img.shields.io/badge/Docker-Compose-2496ED)

A demo project that demonstrates **event-driven microservice architecture** using **Spring Boot**, **Apache Kafka**, and **PostgreSQL**.

The application consists of two independent microservices communicating asynchronously through Kafka.

---

# Architecture

```text
                +----------------------+
                |    Order Service     |
                |----------------------|
                | REST API             |
                | Business Logic       |
                | PostgreSQL           |
                +----------+-----------+
                           |
                    Kafka Producer
                           |
                           ▼
                  Apache Kafka Broker
                           ▲
                    Kafka Consumer
                           |
                +----------+-----------+
                | Notification Service |
                |----------------------|
                | Event Processing     |
                | PostgreSQL           |
                +----------------------+
```

---

# Project Structure

```text
order-notification-demo
│
├── docker-compose.yml
│
├── order-service
│   ├── config
│   ├── controller
│   ├── dto
│   ├── event
│   ├── model
│   ├── repository
│   ├── service
│   └── resources
│
└── notification-service
    ├── event
    ├── model
    ├── repository
    ├── service
    └── resources
```

---

# Tech Stack

* Java 17
* Spring Boot 3
* Spring Data JPA
* Apache Kafka
* PostgreSQL
* Docker
* Docker Compose
* Maven
* Lombok

---

# Features

* REST API for order creation
* Event-driven communication
* Kafka Producer
* Kafka Consumer
* Independent microservices
* Separate PostgreSQL databases
* Asynchronous notification processing

---

# How It Works

1. A client sends an HTTP request to **Order Service**.
2. The order is saved into PostgreSQL.
3. Order Service publishes an event to Kafka.
4. Notification Service consumes the event.
5. Notification Service creates and stores a notification.

---

# REST API

### Create Order

```http
POST /api/orders
```

Request:

```json
{
  "customerName": "John",
  "product": "Laptop",
  "quantity": 1,
  "price": 50000
}
```

---

# Running the Project

## Requirements

* Java 17
* Maven
* Docker
* Docker Compose

### Start infrastructure

```bash
docker-compose up -d
```

### Start Order Service

```bash
cd order-service
mvn spring-boot:run
```

### Start Notification Service

```bash
cd notification-service
mvn spring-boot:run
```

---

# Testing

Create a new order:

```http
POST http://localhost:8081/api/orders
```

Expected output in Notification Service:

```text
NOTIFICATION:
Order #10 created for John:
Laptop x1 - $50000
```

You can also verify saved notifications in PostgreSQL.

---

# What This Project Demonstrates

* Microservice architecture
* Event-driven design
* Apache Kafka Producer & Consumer
* Asynchronous communication
* Spring Boot development
* Docker Compose environment
* Layered architecture
* Spring Data JPA

---

# Future Improvements

* Swagger / OpenAPI
* Integration tests
* Testcontainers
* Retry mechanism
* Dead Letter Queue
* Centralized logging
* Monitoring with Prometheus & Grafana

---

# Author

**Yulia Skulkina**

Java Backend Developer

GitHub: https://github.com/YSSkulkina
