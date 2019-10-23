package com.g33kzone.k8.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class HelloController {

	@GetMapping(path="/hello",produces=MediaType.TEXT_PLAIN_VALUE)
	public String helloWorld() {
		return "Hello World....Welcome to the world of K8s...";
		
	}
}
