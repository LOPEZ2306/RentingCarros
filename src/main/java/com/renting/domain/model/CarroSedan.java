package com.renting.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

// CarroSedan hereda de Vehiculo.
// Con DiscriminatorValue("sedan"), JPA guarda "sedan" en la columna "tipo" de la tabla vehiculo
// cuando guarda un objeto de esta clase.
@Entity
@DiscriminatorValue("sedan")
public class CarroSedan extends Vehiculo {

    @Column(name = "tipo_combustible", length = 20)
    private String tipoCombustible;

    @Column(name = "transmision", length = 20)
    private String transmision;

    // Constructor vacío requerido por JPA
    public CarroSedan() {
        super();
    }

    public CarroSedan(String placa, String marca, int modelo, float precioDiario, String estado,
                      String tipoCombustible, String transmision) {
        super(placa, marca, modelo, precioDiario, estado);
        setTipoCombustible(tipoCombustible);
        setTransmision(transmision);
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        if (tipoCombustible == null || (!tipoCombustible.equalsIgnoreCase("gasolina")
            && !tipoCombustible.equalsIgnoreCase("diésel")
            && !tipoCombustible.equalsIgnoreCase("eléctrico")
            && !tipoCombustible.equalsIgnoreCase("diesel")
            && !tipoCombustible.equalsIgnoreCase("electrico"))) {
            throw new IllegalArgumentException("Tipo de combustible inválido (gasolina, diésel, eléctrico).");
        }
        this.tipoCombustible = tipoCombustible;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        if (transmision == null || (!transmision.equalsIgnoreCase("automática")
            && !transmision.equalsIgnoreCase("manual")
            && !transmision.equalsIgnoreCase("automatica"))) {
            throw new IllegalArgumentException("Transmisión inválida (automática o manual).");
        }
        this.transmision = transmision;
    }
}
