package com.renting.infrastructure.adapter.in.console;

import com.renting.infrastructure.util.RecursiveValidator;
import org.springframework.stereotype.Component;

/**
 * Adapter for the console menu, fulfilling HU1 and HU5 requirements.
 */
@Component
public class ConsoleMenuAdapter {

    public void start() {
        boolean exit = false;
        
        while (!exit) {
            printHeader();
            int option = RecursiveValidator.validatePositiveInt("Seleccione una opcion (1-5): ");
            
            switch (option) {
                case 1:
                    System.out.println("\n--- Gestion de Clientes (Proximamente) ---");
                    break;
                case 2:
                    System.out.println("\n--- Gestion de Vehiculos (Proximamente) ---");
                    break;
                case 3:
                    System.out.println("\n--- Gestion de Contratos (Proximamente) ---");
                    break;
                case 4:
                    System.out.println("\n--- Informe General (Proximamente) ---");
                    break;
                case 5:
                    System.out.println("Saliendo del sistema...");
                    exit = true;
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
            }
        }
    }

    private void printHeader() {
        System.out.println("\n========================================");
        System.out.println("    SISTEMA DE RENTING DE CARROS - ITM");
        System.out.println("========================================");
        System.out.println("1. Gestion de Clientes");
        System.out.println("2. Gestion de Vehiculos");
        System.out.println("3. Gestion de Contratos");
        System.out.println("4. Imprimir Informe General");
        System.out.println("5. Salir");
        System.out.println("========================================");
    }
}
