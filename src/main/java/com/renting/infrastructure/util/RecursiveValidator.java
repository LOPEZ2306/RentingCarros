package com.renting.infrastructure.util;

/**
 * Validador recursivo puro para cumplir HU2/HU8 en un entorno web.
 */
public class RecursiveValidator {

    /**
     * Valida que el texto no tenga caracteres especiales (letras, numeros, espacios son permitidos).
     */
    public static boolean sinCaracteresEspeciales(String text) {
        if (text == null || text.trim().isEmpty()) return false;
        return checkSinCaracteresEspeciales(text, 0);
    }

    private static boolean checkSinCaracteresEspeciales(String text, int index) {
        if (index == text.length()) return true;
        char c = text.charAt(index);
        if (!Character.isLetterOrDigit(c) && c != ' ') return false;
        return checkSinCaracteresEspeciales(text, index + 1);
    }

    /**
     * Valida que el texto no tenga numeros ni caracteres especiales (solo letras y espacios).
     */
    public static boolean sinNumerosNiEspeciales(String text) {
        if (text == null || text.trim().isEmpty()) return false;
        return checkSinNumerosNiEspeciales(text, 0);
    }

    private static boolean checkSinNumerosNiEspeciales(String text, int index) {
        if (index == text.length()) return true;
        char c = text.charAt(index);
        if (!Character.isLetter(c) && c != ' ') return false;
        return checkSinNumerosNiEspeciales(text, index + 1);
    }

    /**
     * Valida que el texto contenga solo numeros.
     */
    public static boolean soloNumeros(String text) {
        if (text == null || text.trim().isEmpty()) return false;
        return checkSoloNumeros(text, 0);
    }

    private static boolean checkSoloNumeros(String text, int index) {
        if (index == text.length()) return true;
        char c = text.charAt(index);
        if (!Character.isDigit(c)) return false;
        return checkSoloNumeros(text, index + 1);
    }
}
