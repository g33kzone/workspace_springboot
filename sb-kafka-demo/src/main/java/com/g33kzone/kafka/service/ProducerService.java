package com.g33kzone.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value(value = "${g33kzone.topic.name}")
	String kafkaTopic;

	public void send(String message) {

		kafkaTemplate.send(kafkaTopic,message, message);
	}

	public void sendBulk() {
		
		for(int i = 0; i < 10; i++){
            System. out.println(i);
            kafkaTemplate.send(kafkaTopic, Integer.toString(i),  "test message - " + i );
        }
	}
}
