package com.renting.application.service;

import com.renting.domain.model.Vehiculo;
import com.renting.domain.repository.VehiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    public VehiculoService(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    public void registrarVehiculo(Vehiculo vehiculo) {
        if (vehiculoRepository.buscarPorPlaca(vehiculo.getPlaca()) != null) {
            throw new IllegalArgumentException("Ya existe un vehículo con esa placa.");
        }
        vehiculoRepository.guardar(vehiculo);
    }

    public void modificarVehiculo(Vehiculo vehiculo) {
        if (vehiculoRepository.buscarPorPlaca(vehiculo.getPlaca()) == null) {
            throw new IllegalArgumentException("El vehículo no existe.");
        }
        vehiculoRepository.modificar(vehiculo);
    }

    public void eliminarVehiculo(String placa) {
        Vehiculo v = vehiculoRepository.buscarPorPlaca(placa);
        if (v == null) {
            throw new IllegalArgumentException("El vehículo no existe.");
        }
        if ("alquilado".equalsIgnoreCase(v.getEstado())) {
            throw new IllegalArgumentException("No se puede eliminar un vehículo que está alquilado.");
        }
        vehiculoRepository.eliminar(placa);
    }

    public Vehiculo buscarVehiculo(String placa) {
        return vehiculoRepository.buscarPorPlaca(placa);
    }

    public List<Vehiculo> listarVehiculos() {
        return vehiculoRepository.listarTodos();
    }
}
