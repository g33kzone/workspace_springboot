package com.g33kzone.sbjunit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.g33kzone.sbjunit.repository.HelloRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HelloServiceMockTest {
    
    @Mock
    private HelloRepository helloRepository;

    @InjectMocks
    private HelloService helloService = new HelloServiceImpl();

    @BeforeEach
    void setMockOutput(){
        when(helloRepository.get()).thenReturn("Hello Mockito From Repository");
    }
    @Test
    @DisplayName("Test Mock helloService + helloRepository")
    void testGet(){
        assertEquals("Hello Mockito From Repository", helloService.get());
    }
}