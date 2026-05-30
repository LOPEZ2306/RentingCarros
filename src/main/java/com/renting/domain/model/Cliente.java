package com.renting.domain.model;

import com.renting.infrastructure.util.RecursiveValidator;

public class Cliente {

    private String cedula;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private String licenciaConduccion;

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

    /** La dirección no tiene restricciones especiales */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLicenciaConduccion() {
        return licenciaConduccion;
    }

    /** La licencia no tiene restricciones especiales */
    public void setLicenciaConduccion(String licenciaConduccion) {
        this.licenciaConduccion = licenciaConduccion;
    }
}