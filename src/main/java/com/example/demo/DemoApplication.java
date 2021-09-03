package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*Bootstrapping a spring application as a stand-alone application from the main method.
 * This helps to create an appropriate ApplicationContext instance and load beans*/
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
