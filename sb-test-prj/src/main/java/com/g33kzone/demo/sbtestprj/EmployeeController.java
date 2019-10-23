package com.g33kzone.demo.sbtestprj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
public class EmployeeController {

    @Autowired
    private IEmployeeService empService;

    @GetMapping("/welcome")
    public ResponseEntity<String> greetings(){
        empService.displayHello();
        return ResponseEntity.status(HttpStatus.OK).body("Hello World !!");
    }
}