package com.muhammed.sabry.musalaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class MusalaApiApplication {
	
	public static void main(String[] args) {
		createDb();
		SpringApplication.run(MusalaApiApplication.class, args);
	}
	
	private static void createDb() {
	
	}
	
}
