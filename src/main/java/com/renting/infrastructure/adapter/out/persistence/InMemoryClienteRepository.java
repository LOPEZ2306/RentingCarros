package com.renting.infrastructure.adapter.out.persistence;

import com.renting.domain.model.Cliente;
import com.renting.domain.repository.ClienteRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

<<<<<<< HEAD
// Este adaptador conecta la lógica del dominio con la base de datos MySQL.
// Antes usaba un ArrayList en memoria; ahora delega cada operación a ClienteJpaRepository
// que es quien realmente habla con la base de datos.
// Los nombres de los métodos no cambian para que los servicios sigan funcionando igual.
@Repository
public class InMemoryClienteRepository implements ClienteRepository {

    // Spring inyecta automáticamente la interfaz JPA que creamos
    private final ClienteJpaRepository clienteJpaRepository;

    public InMemoryClienteRepository(ClienteJpaRepository clienteJpaRepository) {
        this.clienteJpaRepository = clienteJpaRepository;
    }
=======
@Repository
public class InMemoryClienteRepository implements ClienteRepository {
    private final List<Cliente> vectorClientes = new ArrayList<>();
>>>>>>> 3a5c6a9f2f7ba38d35bfa1134f211ec473dec470

    @Override
    public void guardar(Cliente cliente) {
        // save() de JPA inserta si no existe, o actualiza si ya existe (upsert)
        clienteJpaRepository.save(cliente);
    }

    @Override
    public void modificar(Cliente cliente) {
        // save() también funciona para actualizar, JPA detecta que ya existe por la cédula (ID)
        clienteJpaRepository.save(cliente);
    }

    @Override
    public void eliminar(String cedula) {
        // deleteById() borra la fila cuya cédula coincide con el ID recibido
        clienteJpaRepository.deleteById(cedula);
    }

    @Override
    public Cliente buscarPorCedula(String cedula) {
        // findById() devuelve un Optional; si no encuentra nada, retornamos null
        // (igual que antes con el ArrayList)
        return clienteJpaRepository.findById(cedula).orElse(null);
    }

    @Override
    public List<Cliente> listarTodos() {
        // findAll() devuelve todos los clientes guardados en la base de datos
        return clienteJpaRepository.findAll();
    }
}
