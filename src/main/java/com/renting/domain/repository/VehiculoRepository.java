package com.renting.domain.repository;

import com.renting.domain.model.Vehiculo;
import java.util.List;

public interface VehiculoRepository {
    void guardar(Vehiculo vehiculo);
    void modificar(Vehiculo vehiculo);
    void eliminar(String placa);
    Vehiculo buscarPorPlaca(String placa);
    List<Vehiculo> listarTodos();
}
