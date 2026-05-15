package com.renting.infrastructure.util;

import java.util.Scanner;

/**
 * Utility class for recursive validations as required by HU1.
 * Designed to be easy for juniors to understand.
 */
public class RecursiveValidator {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Recursively asks for a non-empty string input.
     * @param prompt The message to show the user.
     * @return A valid non-empty string.
     */
    public static String validateString(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            System.out.println("Error: El campo no puede estar vacio. Intente de nuevo.");
            return validateString(prompt); // Recursive call
        }
        return input;
    }

    /**
     * Recursively asks for a positive integer.
     * @param prompt The message to show the user.
     * @return A valid positive integer.
     */
    public static int validatePositiveInt(String prompt) {
        System.out.print(prompt);
        try {
            int input = Integer.parseInt(scanner.nextLine().trim());
            if (input <= 0) {
                System.out.println("Error: Debe ser un numero positivo mayor a cero.");
                return validatePositiveInt(prompt); // Recursive call
            }
            return input;
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un numero valido.");
            return validatePositiveInt(prompt); // Recursive call
        }
    }
    
    // Add more validation methods as needed in future HUs
}
