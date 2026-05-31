package com.renting.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Esta clase representa un contrato de renting.
// Con JPA, cada objeto ContratoRenting se guarda como una fila en la tabla "contrato" de MySQL.
@Entity
@Table(name = "contrato")
public class ContratoRenting {

    @Id
    @Column(name = "id_contrato", nullable = false, length = 36)
    private String idContrato;

    @Column(name = "cedula_cliente", nullable = false, length = 20)
    private String cedulaCliente;

    @Column(name = "placa_vehiculo", nullable = false, length = 10)
    private String placaVehiculo;

    @Column(name = "fecha_inicio", length = 20)
    private String fechaInicio;

    @Column(name = "fecha_fin", length = 20)
    private String fechaFin;

    @Column(name = "total_dias")
    private int totalDias;

    @Column(name = "valor_total")
    private float valorTotal;

    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    // Constructor vacío requerido por JPA
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
