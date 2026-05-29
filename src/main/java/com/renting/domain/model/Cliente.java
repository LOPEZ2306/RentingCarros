package com.renting.domain.model;

/**
 * Representa a un cliente del sistema de renting.
 * Cumple con HU2: Encapsulamiento y atributos especificados.
 */
public class Cliente {
    
    // Atributos privados (Encapsulamiento)
    private String cedula;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private String licenciaConduccion;

    public Cliente() {
    }

    public Cliente(String cedula, String nombre, String apellido, String telefono, String direccion, String licenciaConduccion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.licenciaConduccion = licenciaConduccion;
    }

    // Getters y Setters
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
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
