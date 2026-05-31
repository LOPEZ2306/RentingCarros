package com.renting.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

// CamionetaSUV hereda de Vehiculo.
// Con DiscriminatorValue("suv"), JPA guarda "suv" en la columna "tipo" de la tabla vehiculo
// cuando guarda un objeto de esta clase.
@Entity
@DiscriminatorValue("suv")
public class CamionetaSUV extends Vehiculo {

    @Column(name = "traccion", length = 10)
    private String traccion;

    @Column(name = "capacidad_maletero")
    private float capacidadMaletero;

    // Constructor vacío requerido por JPA
    public CamionetaSUV() {
        super();
    }

    public CamionetaSUV(String placa, String marca, int modelo, float precioDiario, String estado,
                        String traccion, float capacidadMaletero) {
        super(placa, marca, modelo, precioDiario, estado);
        setTraccion(traccion);
        setCapacidadMaletero(capacidadMaletero);
    }

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
