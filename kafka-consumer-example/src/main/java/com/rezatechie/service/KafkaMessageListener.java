package com.rezatechie.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

import com.rezatechie.dto.Customer;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaMessageListener {

//    @KafkaListener(topics = "rezatechie2",groupId = "rt-group")
//    public void consumeEvents(Customer customer) {
//        log.info("consumer consume the events {} ", customer.toString());
//    }
    
    //group id is must even if there is a single consumer.
    //as there are only 3 partitions, one of the below consumer will remain idle.
	//you can read from a specific partition as well
    @KafkaListener(topics = "rezatechie-demo-3",groupId = "rt-group-1", 
    		topicPartitions = {  @TopicPartition(topic = "rezatechie-demo-3", partitions = {"2"}) })
    public void consume1(String message) {
        log.info("consumer1 consume the message {} ", message);
    }    
//    
//    @KafkaListener(topics = "rezatechie-demo-3",groupId = "rt-group-1")
//    public void consume2(String message) {
//        log.info("consumer2 consume the message {} ", message);
//    }
//
//    @KafkaListener(topics = "rezatechie-demo-3",groupId = "rt-group-1")
//    public void consume3(String message) {
//        log.info("consumer3 consume the message {} ", message);
//    }
//
//    @KafkaListener(topics = "rezatechie-demo-3",groupId = "rt-group-1")
//    public void consume4(String message) {
//        log.info("consumer4 consume the message {} ", message);
//    }
}