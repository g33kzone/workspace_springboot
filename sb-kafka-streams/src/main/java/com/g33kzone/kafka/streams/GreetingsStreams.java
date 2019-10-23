package com.g33kzone.kafka.streams;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface GreetingsStreams {

	String INPUT = "greetings-in";
	String OUTPUT = "greetings-out";

	@Input(INPUT)
	SubscribableChannel inboundGreetings();

	@Output(OUTPUT)
	SubscribableChannel outboundGreetings();
}
