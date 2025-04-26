# Kafka Spring Boot Application

This project demonstrates how to send messages to Kafka using Spring Boot. Below is a brief overview of the key classes and their functionality.

Start Zookeeper (only if not already running)
./zookeeper-server-start.sh ../config/zookeeper.properties

Start Kafka Server (Broker)
./kafka-server-start.sh ../config/server.properties

---

## 1. **KafkaProducerConfig.java**

- **Purpose**: Configures Kafka Producer in a Spring Boot application.
- **Key Features**:
  - Creates Kafka Topic `rezatechie-demo-3` with 3 partitions and 1 replica.
  - Configures Kafka Producer settings such as the Kafka broker address and serializers.
  - Sets up the `KafkaTemplate` to send messages to Kafka.

---

## 2. **EventController.java**

- **Purpose**: REST controller that handles HTTP requests to send messages to Kafka.
- **Key Features**:
  - **GET /producer-app/publish/{message}**: Sends a string message to Kafka.
  - **POST /producer-app/publish**: Accepts a `Customer` object and sends it to Kafka.

---

## 3. **Customer.java**

- **Purpose**: A Data Transfer Object (DTO) to represent customer data.
- **Key Features**:
  - Contains fields: `id`, `name`, `email`, and `contactNo`.
  - Uses Lombok annotations (`@Data`, `@AllArgsConstructor`, `@NoArgsConstructor`) to reduce boilerplate code.

---

## 4. **KafkaMessagePublisher.java**

- **Purpose**: Service that sends messages to Kafka.
- **Key Features**:
  - **sendMessageToTopic**: Sends a string message to Kafka topic `rezatechie-demo-3`.
  - **sendEventsToTopic**: Sends a `Customer` object to Kafka topic `rezatechie2`.
  - Uses `KafkaTemplate` to send messages asynchronously with `CompletableFuture`.

---

### Summary

This application allows sending both string messages and customer data to Kafka topics using Spring Boot. The configuration, controller, DTO, and service classes work together to ensure efficient communication with Kafka.

---

