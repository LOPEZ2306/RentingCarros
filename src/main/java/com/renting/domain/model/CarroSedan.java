package com.renting.domain.model;

/**
 * Representa un vehículo tipo Sedán.
 * Cumple con HU3: Herencia (extiende de Vehiculo) y Polimorfismo.
 */
public class CarroSedan extends Vehiculo {
    
    // Atributos específicos
    private String tipoCombustible; // "gasolina", "diésel", "eléctrico"
    private String transmision;     // "automática", "manual"

    public CarroSedan() {
        super();
    }

    public CarroSedan(String placa, String marca, int modelo, float precioDiario, String estado, 
                      String tipoCombustible, String transmision) {
        super(placa, marca, modelo, precioDiario, estado);
        this.tipoCombustible = tipoCombustible;
        this.transmision = transmision;
    }

    // Getters y Setters
    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }
}
