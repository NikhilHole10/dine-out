package com.spring.dineout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class DineOutApplication {

	public static void main(String[] args) {
		SpringApplication.run(DineOutApplication.class, args);
	}

}
