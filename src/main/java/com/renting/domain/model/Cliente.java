package com.renting.domain.model;

import com.renting.infrastructure.util.RecursiveValidator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Esta clase representa a un cliente del sistema de renting.
// Con JPA, cada objeto Cliente se guarda como una fila en la tabla "cliente" de MySQL.
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @Column(name = "cedula", nullable = false, length = 20)
    private String cedula;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "direccion", length = 100)
    private String direccion;

    @Column(name = "licencia_conduccion", length = 30)
    private String licenciaConduccion;

    // Constructor vacío requerido por JPA
    public Cliente() {
    }

    public Cliente(String cedula, String nombre, String apellido,
                   String telefono, String direccion, String licenciaConduccion) {
        setCedula(cedula);
        setNombre(nombre);
        setApellido(apellido);
        setTelefono(telefono);
        setDireccion(direccion);
        setLicenciaConduccion(licenciaConduccion);
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        if (!RecursiveValidator.soloNumeros(cedula)) {
            throw new IllegalArgumentException("Cédula inválida: solo se permiten números.");
        }
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (!RecursiveValidator.sinNumerosNiEspeciales(nombre)) {
            throw new IllegalArgumentException("Nombre inválido: solo se permiten letras y espacios.");
        }
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        if (!RecursiveValidator.sinNumerosNiEspeciales(apellido)) {
            throw new IllegalArgumentException("Apellido inválido: solo se permiten letras y espacios.");
        }
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (!RecursiveValidator.soloNumeros(telefono)) {
            throw new IllegalArgumentException("Teléfono inválido: solo se permiten números.");
        }
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLicenciaConduccion() {
        return licenciaConduccion;
    }

    public void setLicenciaConduccion(String licenciaConduccion) {
        this.licenciaConduccion = licenciaConduccion;
    }
}