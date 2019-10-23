package com.g33kzone.helloworld.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping(path="/hello",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> greetings() {
		
		return ResponseEntity.status(HttpStatus.OK).body("Hello World - Welcome to GCP Cloud Build using K8s...");
	}
}
