# Kafka Spring Boot Consumer Application

This project demonstrates how to consume messages from Kafka using Spring Boot. Below is a brief overview of the key classes and their functionality.

Start Zookeeper (only if not already running):

```bash
./zookeeper-server-start.sh ../config/zookeeper.properties
```

Start Kafka Server (Broker):

```bash
./kafka-server-start.sh ../config/server.properties
```

---

## 1. **KafkaConsumerConfig.java**

- **Purpose**: Configures Kafka Consumer in a Spring Boot application.
- **Key Features**:
  - Configures Kafka consumer settings such as the Kafka broker address, deserializers, and trusted packages.
  - Sets up the `ConsumerFactory` and `KafkaListenerContainerFactory` beans.
  - Ensures messages are consumed asynchronously from the Kafka topic.

---

## 2. **KafkaMessageListener.java**

- **Purpose**: Service that listens to Kafka messages.
- **Key Features**:
  - **@KafkaListener** annotation to consume messages from the `rezatechie-demo-3` topic.
  - Configures the consumer group and listens to specific topic partitions (e.g., Partition 2).
  - Logs the consumed messages.
  
Sample Listener Method:

```java
@KafkaListener(topics = "rezatechie-demo-3", groupId = "rt-group-1", 
        topicPartitions = {@TopicPartition(topic = "rezatechie-demo-3", partitions = {"2"})})
public void consume1(String message) {
    log.info("consumer1 consume the message {} ", message);
}
```

---

## 3. **Customer.java**

- **Purpose**: A Data Transfer Object (DTO) representing customer data.
- **Key Features**:
  - Contains fields: `id`, `name`, `email`, and `contactNo`.
  - Uses Lombok annotations (`@Data`, `@AllArgsConstructor`, `@NoArgsConstructor`) to reduce boilerplate code.

---

## 4. **application.properties** (you can either configure using Java or properties file)

- **Purpose**: Configuration for the Spring Boot application and Kafka consumer.
- **Key Features**:
  - Configures Kafka consumer properties, including broker address and consumer group ID.
  - Sets up the trusted package for deserialization.

```properties
spring.application.name=kafka-consumer-example
server.port=9292
spring.kafka.consumer.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=rt-group
spring.kafka.consumer.properties.spring.json.trusted.packages=com.rezatechie.dto
```

---

## How to Run

1. Make sure **Kafka** and **Zookeeper** are running locally.
2. Start the Spring Boot consumer application.
3. Kafka consumer will start listening to the `rezatechie-demo-3` topic.
4. Produce messages to the topic, and the consumer will log the consumed messages.
5. Use Kafka offset explorer to analyse more details.

---
