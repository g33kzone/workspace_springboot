package com.g33kzone.kaftwit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import twitter4j.Status;

@Service
public class KafkaStreamService {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value(value = "${g33kzone.topic.name}")
	String kafkaTopic;

	public void sendToKafka(Status status) {
		System.out.println("Tweet" + status.getPlace());
		kafkaTemplate.send(kafkaTopic, kafkaTopic, status.getText());
		
		
	}
}
