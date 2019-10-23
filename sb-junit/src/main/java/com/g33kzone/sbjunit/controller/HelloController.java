package com.g33kzone.sbjunit.controller;

import com.g33kzone.sbjunit.service.HelloService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/hello")
    public String get(){
        return helloService.get();
    }
}
