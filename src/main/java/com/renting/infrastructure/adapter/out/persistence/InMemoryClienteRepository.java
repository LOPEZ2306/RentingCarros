package com.renting.infrastructure.adapter.out.persistence;

import com.renting.domain.model.Cliente;
import com.renting.domain.repository.ClienteRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Adaptador de persistencia en memoria usando Vectores Dinámicos (ArrayList).
 * Cumple con HU6: "requiero que los registros se almacenen en vectores dinámicos"
 */
@Repository
public class InMemoryClienteRepository implements ClienteRepository {

    // Vector dinámico para almacenar clientes (HU6)
    private final List<Cliente> vectorClientes = new ArrayList<>();

    @Override
    public void guardar(Cliente cliente) {
        vectorClientes.add(cliente);
    }

    @Override
    public void modificar(Cliente cliente) {
        for (int i = 0; i < vectorClientes.size(); i++) {
            if (vectorClientes.get(i).getCedula().equals(cliente.getCedula())) {
                vectorClientes.set(i, cliente);
                return;
            }
        }
    }

    @Override
    public void eliminar(String cedula) {
        for (int i = 0; i < vectorClientes.size(); i++) {
            if (vectorClientes.get(i).getCedula().equals(cedula)) {
                vectorClientes.remove(i);
                break; // Rompemos el ciclo porque ya lo eliminó
            }
        }
    }

    @Override
    public Cliente buscarPorCedula(String cedula) {
        for (int i = 0; i < vectorClientes.size(); i++) {
            Cliente cliente = vectorClientes.get(i);
            if (cliente.getCedula().equals(cedula)) {
                return cliente;
            }
        }
        return null; // Si no lo encuentra
    }

    @Override
    public List<Cliente> listarTodos() {
        return new ArrayList<>(vectorClientes);
    }
}
