package com.renting.infrastructure.adapter.out.persistence;

import com.renting.domain.model.Cliente;
import com.renting.domain.repository.ClienteRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryClienteRepository implements ClienteRepository {

    private final ClienteJpaRepository clienteJpaRepository;

    public InMemoryClienteRepository(ClienteJpaRepository clienteJpaRepository) {
        this.clienteJpaRepository = clienteJpaRepository;
    }

    @Override
    public void guardar(Cliente cliente) {
        clienteJpaRepository.save(cliente);
    }

    @Override
    public void modificar(Cliente cliente) {
        clienteJpaRepository.save(cliente);
    }

    @Override
    public void eliminar(String cedula) {
        clienteJpaRepository.deleteById(cedula);
    }

    @Override
    public Cliente buscarPorCedula(String cedula) {
        return clienteJpaRepository.findById(cedula).orElse(null);
    }

    @Override
    public List<Cliente> listarTodos() {
        return clienteJpaRepository.findAll();
    }
}
