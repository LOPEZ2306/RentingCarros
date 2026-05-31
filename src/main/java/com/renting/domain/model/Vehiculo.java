package com.renting.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

// Clase abstracta base para todos los tipos de vehículos.
// Usamos SINGLE_TABLE: todos los vehículos (sedan, suv) se guardan en una sola tabla "vehiculo"
// con una columna "tipo" que indica de qué clase es cada fila.
@Entity
@Table(name = "vehiculo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class Vehiculo {

    @Id
    @Column(name = "placa", nullable = false, length = 10)
    private String placa;

    @Column(name = "marca", nullable = false, length = 30)
    private String marca;

    @Column(name = "modelo", nullable = false)
    private int modelo;

    @Column(name = "precio_diario", nullable = false)
    private float precioDiario;

    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    // Constructor vacío requerido por JPA
    public Vehiculo() {
    }

    public Vehiculo(String placa, String marca, int modelo, float precioDiario, String estado) {
        setPlaca(placa);
        setMarca(marca);
        setModelo(modelo);
        setPrecioDiario(precioDiario);
        setEstado(estado);
    }

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
