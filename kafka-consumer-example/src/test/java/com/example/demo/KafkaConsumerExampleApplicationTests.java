package com.example.demo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.kafka.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import com.rezatechie.dto.Customer;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@Slf4j
public class KafkaConsumerExampleApplicationTests {

	@Container
	static final KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));

	@DynamicPropertySource
	static void overridePropertiesInternal(DynamicPropertyRegistry registry) {
		registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
	}
	
	@BeforeAll
	static void beforeAll() {
	    log.info("Kafka bootstrap server: {}", kafka.getBootstrapServers());
	}

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@Test
	public void testConsumeEvents() {
		log.info("testConsumeEvents method execution started...");
		Customer customer = new Customer(263, "test user", "test@gmail.com", "564782542752");
		kafkaTemplate.send("rezatechie-demo", customer);
		log.info("testConsumeEvents method execution ended...");
		await().pollInterval(Duration.ofSeconds(3)).atMost(10, TimeUnit.SECONDS).untilAsserted(() -> {
			// assert statement
		});
	}
	
	@KafkaListener(topics = "rezatechie-demo", groupId = "test-group")
	public void listen(Customer customer) {
	    log.info("Received customer: {}", customer);
	}

}
