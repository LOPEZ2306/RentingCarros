package com.renting.domain.model;

/**
 * Representa un contrato de alquiler en el sistema.
 * Cumple con HU4: Modelo de datos para la gestión del renting.
 */
public class ContratoRenting {
    
    // Atributos privados (Encapsulamiento)
    private String idContrato;
    private String cedulaCliente;
    private String placaVehiculo;
    private String fechaInicio;
    private String fechaFin;
    private int totalDias;
    private float valorTotal;

    public ContratoRenting() {
    }

    public ContratoRenting(String idContrato, String cedulaCliente, String placaVehiculo, 
                           String fechaInicio, String fechaFin, int totalDias, float valorTotal) {
        this.idContrato = idContrato;
        this.cedulaCliente = cedulaCliente;
        this.placaVehiculo = placaVehiculo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.totalDias = totalDias;
        this.valorTotal = valorTotal;
    }

    // Getters y Setters
    public String getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(String idContrato) {
        this.idContrato = idContrato;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getTotalDias() {
        return totalDias;
    }

    public void setTotalDias(int totalDias) {
        this.totalDias = totalDias;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }
}
