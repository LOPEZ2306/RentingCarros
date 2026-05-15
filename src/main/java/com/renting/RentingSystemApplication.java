package com.renting;

import com.renting.infrastructure.adapter.in.console.ConsoleMenuAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RentingSystemApplication {

	public static void main(String[] args) {
		// Iniciar el contexto de Spring Boot
		ConfigurableApplicationContext context = SpringApplication.run(RentingSystemApplication.class, args);
		
		// Llamar al menu principal (Cumple HU1: Main solo llama metodos externos)
		ConsoleMenuAdapter menu = context.getBean(ConsoleMenuAdapter.class);
		menu.start();
		
		// Cerrar la aplicacion al salir del menu
		System.exit(SpringApplication.exit(context));
	}

}
