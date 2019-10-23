package com.g33kzone.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.g33kzone.kafka.service.ProducerService;

@RestController
@RequestMapping(value = "/kafka-producer/")
public class KafkaController {
	
	@Autowired
	ProducerService ProducerService;

	@GetMapping(value = "/producer")
	public String producer(@RequestParam("message") String message) {
		ProducerService.send(message);

		return "Message sent to the Kafka Topic myTopic Successfully";
	}
	
	@GetMapping(value = "/producer-bulk")
	public String producerBulk() {
		ProducerService.sendBulk();

		return "Message sent to the Kafka Topic myTopic in Bulk Successfully";
	}

}
