package com.digitalharbor.backend_tasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BackendTasksApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendTasksApplication.class, args);
	}

}
