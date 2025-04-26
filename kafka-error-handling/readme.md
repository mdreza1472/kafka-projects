# Kafka Producer/Consumer with Retry and DLT in Spring Boot

This project demonstrates how to send messages to Kafka using Spring Boot while showcasing Kafka's **Retryable** and **Dead Letter Topic (DLT)** features.

---

## Key Features

1. **Retryable Feature**: 
   - Uses Spring Kafka's `@RetryableTopic` annotation to retry message consumption up to 4 times.
   - If a message fails after retries, it is moved to a Dead Letter Topic (DLT).

2. **Dead Letter Topic (DLT)**: 
   - If the consumer fails to process a message after multiple retries, the message is forwarded to the DLT.
   - Handled using the `@DltHandler` annotation.

---

## Key Files

1. **KafkaConfig.java**: Configures Kafka topic and producer settings.
2. **KafkaMessagePublisher.java**: Publishes messages to Kafka topics.
3. **EventController.java**: Provides an endpoint to publish `User` objects or CSV data to Kafka.
4. **User.java**: DTO for user data with Lombok annotations.
5. **CsvReaderUtils.java**: Utility to read user data from a CSV file and send it to Kafka.

---

## How to Run

1. **Start Kafka**:
   - Use Testcontainers to spin up Kafka or start it manually using:
     ```
     ./zookeeper-server-start.sh ../config/zookeeper.properties
     ./kafka-server-start.sh ../config/server.properties
     ```

2. **Run the Spring Boot Application**:
   - Execute the application with:
     ```
     mvn spring-boot:run
     ```

3. **Publish Events**:
   - Use the **POST /producer-app/publishNew** endpoint to send `User` objects to Kafka.

---

## Testing

- **KafkaProducerApplicationTests.java**: 
   - Tests Kafka producer using **Testcontainers**.
   - Sends `User` objects to Kafka and ensures they are successfully published.

---

This project highlights Kafka's retryable message handling and the use of Dead Letter Topics for message failure recovery.