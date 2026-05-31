package com.renting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Punto de entrada de la aplicación.
// Ya no excluimos DataSourceAutoConfiguration porque ahora sí tenemos MySQL configurado.
@SpringBootApplication
public class RentingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentingSystemApplication.class, args);
    }
}