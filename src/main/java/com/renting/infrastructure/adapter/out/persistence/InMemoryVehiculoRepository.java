package com.renting.infrastructure.adapter.out.persistence;

import com.renting.domain.model.Vehiculo;
import com.renting.domain.repository.VehiculoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Este adaptador conecta la lógica del dominio con la base de datos MySQL.
// Antes usaba un ArrayList en memoria; ahora delega cada operación a VehiculoJpaRepository
// que es quien realmente habla con la base de datos.
// Los nombres de los métodos no cambian para que los servicios sigan funcionando igual.
@Repository
public class InMemoryVehiculoRepository implements VehiculoRepository {

    // Spring inyecta automáticamente la interfaz JPA que creamos
    private final VehiculoJpaRepository vehiculoJpaRepository;

    public InMemoryVehiculoRepository(VehiculoJpaRepository vehiculoJpaRepository) {
        this.vehiculoJpaRepository = vehiculoJpaRepository;
    }

    @Override
    public void guardar(Vehiculo vehiculo) {
        // save() de JPA inserta si no existe, o actualiza si ya existe (upsert)
        vehiculoJpaRepository.save(vehiculo);
    }

    @Override
    public void modificar(Vehiculo vehiculo) {
        // save() también funciona para actualizar, JPA detecta que ya existe por la placa (ID)
        vehiculoJpaRepository.save(vehiculo);
    }

    @Override
    public void eliminar(String placa) {
        // deleteById() borra la fila cuya placa coincide con el ID recibido
        vehiculoJpaRepository.deleteById(placa);
    }

    @Override
    public Vehiculo buscarPorPlaca(String placa) {
        // findById() devuelve un Optional; si no encuentra nada, retornamos null
        // (igual que antes con el ArrayList)
        return vehiculoJpaRepository.findById(placa).orElse(null);
    }

    @Override
    public List<Vehiculo> listarTodos() {
        // findAll() devuelve todos los vehículos guardados en la base de datos
        return vehiculoJpaRepository.findAll();
    }
}
