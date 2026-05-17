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
        this.traccion = traccion;
        this.capacidadMaletero = capacidadMaletero;
    }

    // Getters y Setters
    public String getTraccion() {
        return traccion;
    }

    public void setTraccion(String traccion) {
        this.traccion = traccion;
    }

    public float getCapacidadMaletero() {
        return capacidadMaletero;
    }

    public void setCapacidadMaletero(float capacidadMaletero) {
        this.capacidadMaletero = capacidadMaletero;
    }
}
