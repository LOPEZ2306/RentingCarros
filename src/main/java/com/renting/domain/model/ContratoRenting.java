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
    private String estado; // "activo" o "finalizado"

    public ContratoRenting() {
    }

    public ContratoRenting(String idContrato, String cedulaCliente, String placaVehiculo, 
                           String fechaInicio, String fechaFin, int totalDias, float valorTotal, String estado) {
        setIdContrato(idContrato);
        setCedulaCliente(cedulaCliente);
        setPlacaVehiculo(placaVehiculo);
        setFechaInicio(fechaInicio);
        setFechaFin(fechaFin);
        setTotalDias(totalDias);
        setValorTotal(valorTotal);
        setEstado(estado);
    }

    // Getters y Setters
    public String getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(String idContrato) {
        if (!com.renting.infrastructure.util.RecursiveValidator.sinCaracteresEspeciales(idContrato)) {
            throw new IllegalArgumentException("El ID del contrato no debe tener caracteres especiales.");
        }
        this.idContrato = idContrato;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        if (!com.renting.infrastructure.util.RecursiveValidator.sinCaracteresEspeciales(cedulaCliente)) {
            throw new IllegalArgumentException("La cédula no debe tener caracteres especiales.");
        }
        this.cedulaCliente = cedulaCliente;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        if (!com.renting.infrastructure.util.RecursiveValidator.sinCaracteresEspeciales(placaVehiculo)) {
            throw new IllegalArgumentException("La placa no debe tener caracteres especiales.");
        }
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
        if (totalDias <= 0) {
            throw new IllegalArgumentException("El total de días debe ser un número positivo.");
        }
        this.totalDias = totalDias;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        if (valorTotal < 0) {
            throw new IllegalArgumentException("El valor total no puede ser negativo.");
        }
        this.valorTotal = valorTotal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if (estado == null || (!estado.equalsIgnoreCase("activo") && !estado.equalsIgnoreCase("finalizado"))) {
            throw new IllegalArgumentException("El estado debe ser 'activo' o 'finalizado'.");
        }
        this.estado = estado;
    }
}
