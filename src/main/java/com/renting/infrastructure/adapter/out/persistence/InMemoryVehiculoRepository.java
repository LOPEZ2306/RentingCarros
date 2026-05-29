package com.renting.infrastructure.adapter.out.persistence;

import com.renting.domain.model.Vehiculo;
import com.renting.domain.repository.VehiculoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Adaptador de persistencia en memoria usando Vectores Dinámicos (ArrayList).
 * Cumple con HU6: vector_vehiculos.
 */
@Repository
public class InMemoryVehiculoRepository implements VehiculoRepository {

    private final List<Vehiculo> vectorVehiculos = new ArrayList<>();

    @Override
    public void guardar(Vehiculo vehiculo) {
        vectorVehiculos.add(vehiculo);
    }

    @Override
    public void modificar(Vehiculo vehiculo) {
        for (int i = 0; i < vectorVehiculos.size(); i++) {
            if (vectorVehiculos.get(i).getPlaca().equals(vehiculo.getPlaca())) {
                vectorVehiculos.set(i, vehiculo);
                return;
            }
        }
    }

    @Override
    public void eliminar(String placa) {
        vectorVehiculos.removeIf(v -> v.getPlaca().equals(placa));
    }

    @Override
    public Vehiculo buscarPorPlaca(String placa) {
        return vectorVehiculos.stream()
            .filter(v -> v.getPlaca().equals(placa))
            .findFirst()
            .orElse(null);
    }

    @Override
    public List<Vehiculo> listarTodos() {
        return new ArrayList<>(vectorVehiculos);
    }
}
