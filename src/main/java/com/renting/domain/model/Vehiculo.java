package com.renting.domain.model;

/**
 * Clase base para los vehículos.
 * Cumple con HU3: Abstracción y base para la Herencia.
 */
public abstract class Vehiculo {
    
    // Atributos privados (Encapsulamiento)
    private String placa;
    private String marca;
    private int modelo;
    private float precioDiario;
    private String estado; // "disponible" o "alquilado"

    public Vehiculo() {
    }

    public Vehiculo(String placa, String marca, int modelo, float precioDiario, String estado) {
        setPlaca(placa);
        setMarca(marca);
        setModelo(modelo);
        setPrecioDiario(precioDiario);
        setEstado(estado);
    }

    // Getters y Setters
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        if (!com.renting.infrastructure.util.RecursiveValidator.sinCaracteresEspeciales(placa)) {
            throw new IllegalArgumentException("Placa inválida: sin caracteres especiales.");
        }
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        if (!com.renting.infrastructure.util.RecursiveValidator.sinNumerosNiEspeciales(marca)) {
            throw new IllegalArgumentException("Marca inválida: sin números ni símbolos.");
        }
        this.marca = marca;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        if (modelo <= 0) {
            throw new IllegalArgumentException("El modelo debe ser un número positivo.");
        }
        this.modelo = modelo;
    }

    public float getPrecioDiario() {
        return precioDiario;
    }

    public void setPrecioDiario(float precioDiario) {
        if (precioDiario <= 0) {
            throw new IllegalArgumentException("El precio diario debe ser un número positivo.");
        }
        this.precioDiario = precioDiario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if (estado == null || (!estado.equalsIgnoreCase("disponible") && !estado.equalsIgnoreCase("alquilado"))) {
            throw new IllegalArgumentException("El estado debe ser 'disponible' o 'alquilado'.");
        }
        this.estado = estado;
    }
}
