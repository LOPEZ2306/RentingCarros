package com.renting.infrastructure.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> manejarErrorDeParseoJson(
            HttpMessageNotReadableException ex) {

        Throwable causaRaiz = ex.getCause();
        boolean tieneMensajeDeDominio = causaRaiz != null
                && causaRaiz.getCause() instanceof IllegalArgumentException;

        if (tieneMensajeDeDominio) {
            String mensajeDeDominio = causaRaiz.getCause().getMessage();
            return respuestaError(mensajeDeDominio, HttpStatus.BAD_REQUEST);
        }

        return respuestaError(
                "Datos inválidos. Revisa que los campos tengan el formato correcto.",
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> manejarErrorDeValidacion(
            IllegalArgumentException ex) {

        return respuestaError(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Map<String, String>> respuestaError(String mensaje, HttpStatus estado) {
        Map<String, String> cuerpo = new HashMap<>();
        cuerpo.put("error", mensaje);
        return ResponseEntity.status(estado).body(cuerpo);
    }
}