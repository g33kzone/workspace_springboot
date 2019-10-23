package com.g33kzone.sbjunit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HelloServiceTest {

    @Autowired
    private HelloService helloService;

    @Test
    @DisplayName("Test Spring @Autowired Integration")
    public void testGet(){
        assertEquals("Hello JUnit 5", helloService.get());
    }
}