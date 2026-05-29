package com.renting.domain.model;

/**
 * Representa un vehículo tipo SUV.
 * Cumple con HU3: Herencia (extiende de Vehiculo) y Polimorfismo.
 */
public class CamionetaSUV extends Vehiculo {
    
    // Atributos específicos
    private String traccion;          // "4x2" o "4x4"
    private float capacidadMaletero;  // En litros

    public CamionetaSUV() {
        super();
    }

    public CamionetaSUV(String placa, String marca, int modelo, float precioDiario, String estado,
                        String traccion, float capacidadMaletero) {
        super(placa, marca, modelo, precioDiario, estado);
        setTraccion(traccion);
        setCapacidadMaletero(capacidadMaletero);
    }

    // Getters y Setters
    public String getTraccion() {
        return traccion;
    }

    public void setTraccion(String traccion) {
        if (traccion == null || (!traccion.equalsIgnoreCase("4x2") && !traccion.equalsIgnoreCase("4x4"))) {
            throw new IllegalArgumentException("Tracción inválida (4x2 o 4x4).");
        }
        this.traccion = traccion;
    }

    public float getCapacidadMaletero() {
        return capacidadMaletero;
    }

    public void setCapacidadMaletero(float capacidadMaletero) {
        if (capacidadMaletero <= 0) {
            throw new IllegalArgumentException("La capacidad del maletero debe ser un número positivo.");
        }
        this.capacidadMaletero = capacidadMaletero;
    }
}
