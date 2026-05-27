# 🚀 Order Notification Demo - Микросервисы + Kafka

## 📋 О проекте
Проект демонстрирует базовую микросервисную архитектуру с использованием Apache Kafka для асинхронного обмена сообщениями.

### Архитектура
- **Order Service** (порт 8081) - создаёт заказы и публикует события
- **Notification Service** (порт 8082) - читает события и сохраняет уведомления
- **Kafka** - брокер сообщений
- **PostgreSQL** - отдельные БД для каждого сервиса

## 🛠 Технологии
- Spring Boot 3.2
- Apache Kafka
- PostgreSQL
- Docker / Docker Compose
- Lombok
- JPA / Hibernate

## 🚀 Запуск проекта

### Предварительные требования
- Docker и Docker Compose
- Java 17
- Maven

### Шаги для запуска

1. **Запустить инфраструктуру (Kafka + PostgreSQL)**
```bash
docker-compose up -d
```

2. **Запустить Order Service**
```bash
cd order-service
mvn spring-boot:run
```

3. **Запустить Notification Service**
```bash
cd notification-service
mvn spring-boot:run
```
4. **Как проверить**
   Создать заказ через POST /order
```bash
http://localhost:8081/api/orders
   {"customerName":"Тест",
    "product":"Ноутбук",
    "quantity":1,
    "price":50000
    }
   ```
Посмотреть консоль Notification Service — должно появиться сообщение
📧 NOTIFICATION: Order #10 created for Тест: Ноутбук x1 - $50000,00
(Опционально) проверить в БД, что событие сохранено
```bash
docker exec -it postgres-notification psql -U postgres -d notificationdb -c "SELECT * FROM notifications;"
```

5. ***Что демонстрируется***
- Взаимодействие микросервисов через брокер сообщений

- Асинхронная обработка событий

- Базовая работа с Kafka (продюсер + консьюмер)

Автор
Юлия Скулкина
GitHub: https://github.com/YSSkulkina
