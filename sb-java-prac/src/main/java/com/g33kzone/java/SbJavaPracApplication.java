package com.g33kzone.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SbJavaPracApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbJavaPracApplication.class, args);

		Singleton x = Singleton.getSingleInstance();

		Singleton y = Singleton.getSingleInstance();

		Singleton z = Singleton.getSingleInstance();
		
		
		x.s = (x.s).toUpperCase();
		
		System.out.println("String from x is " + x.s); 
        System.out.println("String from y is " + y.s); 
        System.out.println("String from z is " + z.s); 
        System.out.println("\n"); 
  
        // changing variable of instance z 
        z.s = (z.s).toLowerCase(); 
  
        System.out.println("String from x is " + x.s); 
        System.out.println("String from y is " + y.s); 
        System.out.println("String from z is " + z.s); 

	}

}
