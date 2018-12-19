package com.microservices.backendninja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//habilitamos las tareas automaticas, estan definidas en TaskComponent
@EnableScheduling
public class BackendninjaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendninjaApplication.class, args);
	}
}
