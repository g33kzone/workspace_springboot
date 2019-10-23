package com.g33kzone.kafka.streams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
public class GreetingsController {
	
	@Autowired
	private GreetingsService greetingsService;

	
	@GetMapping("/greetings")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void greetings(@RequestParam("message") String message) {
		
		Greetings greetings = Greetings.builder()
	            .message(message)
	            .timestamp(System.currentTimeMillis())
	            .build();
		
		greetingsService.sendGreeting(greetings);
	}
}
