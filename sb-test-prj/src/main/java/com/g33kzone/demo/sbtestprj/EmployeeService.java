package com.g33kzone.demo.sbtestprj;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements IEmployeeService{

    @Override
    public void displayHello() {
        System.out.println("In Employee 0");
    }
}