package com.renting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RentingSystemApplication {

	public static void main(String[] args) {
		// El main solo inicia la aplicacion Spring Boot.
		// Las funcionalidades se acceden via API REST (Cumpliendo HU1/HU5 de forma moderna).
		SpringApplication.run(RentingSystemApplication.class, args);
	}

}
