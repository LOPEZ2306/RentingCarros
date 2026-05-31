package com.renting.infrastructure.adapter.out.persistence;

import com.renting.domain.model.ContratoRenting;
import com.renting.domain.repository.ContratoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Este adaptador conecta la lógica del dominio con la base de datos MySQL.
// Antes usaba un ArrayList en memoria; ahora delega cada operación a ContratoJpaRepository
// que es quien realmente habla con la base de datos.
// Los nombres de los métodos no cambian para que los servicios sigan funcionando igual.
@Repository
public class InMemoryContratoRepository implements ContratoRepository {

    // Spring inyecta automáticamente la interfaz JPA que creamos
    private final ContratoJpaRepository contratoJpaRepository;

    public InMemoryContratoRepository(ContratoJpaRepository contratoJpaRepository) {
        this.contratoJpaRepository = contratoJpaRepository;
    }

    @Override
    public void guardar(ContratoRenting contrato) {
        // save() de JPA inserta si no existe, o actualiza si ya existe (upsert)
        contratoJpaRepository.save(contrato);
    }

    @Override
    public void modificar(ContratoRenting contrato) {
        // save() también funciona para actualizar, JPA detecta que ya existe por el id (ID)
        contratoJpaRepository.save(contrato);
    }

    @Override
    public ContratoRenting buscarPorId(String idContrato) {
        // findById() devuelve un Optional; si no encuentra nada, retornamos null
        // (igual que antes con el ArrayList)
        return contratoJpaRepository.findById(idContrato).orElse(null);
    }

    @Override
    public ContratoRenting buscarActivoPorCliente(String cedulaCliente) {
        // Usamos el método personalizado de la interfaz JPA para buscar
        // un contrato que sea del cliente indicado y que esté en estado "activo"
        return contratoJpaRepository
                .findByCedulaClienteAndEstadoIgnoreCase(cedulaCliente, "activo")
                .orElse(null);
    }

    @Override
    public List<ContratoRenting> listarTodos() {
        // findAll() devuelve todos los contratos guardados en la base de datos
        return contratoJpaRepository.findAll();
    }
}
