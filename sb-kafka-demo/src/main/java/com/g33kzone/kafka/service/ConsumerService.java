package com.g33kzone.kafka.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

	
	@Value(value = "${g33kzone.topic.name}")
	String kafkaTopic;
	
	@KafkaListener(topics="${g33kzone.topic.name}")
	public void consume(ConsumerRecord record) {
		System.out.println(String.format("Topic - %s, Partition - %d, Value: %s", kafkaTopic, record.partition(), record.value()));
	}

}
