package com.renting.application.service;

import com.renting.domain.model.Cliente;
import com.renting.domain.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void registrarCliente(Cliente cliente) {
        if (clienteRepository.buscarPorCedula(cliente.getCedula()) != null) {
            throw new IllegalArgumentException("Ya existe un cliente con la cédula ingresada.");
        }
        clienteRepository.guardar(cliente);
    }

    public void modificarCliente(Cliente cliente) {
        if (clienteRepository.buscarPorCedula(cliente.getCedula()) == null) {
            throw new IllegalArgumentException("No se encontró el cliente a modificar.");
        }
        clienteRepository.modificar(cliente);
    }

    public void eliminarCliente(String cedula) {
        if (clienteRepository.buscarPorCedula(cedula) == null) {
            throw new IllegalArgumentException("No se encontró el cliente a eliminar.");
        }
        // Nota: En HU4 se debe validar que el cliente no tenga contratos activos antes de eliminar.
        clienteRepository.eliminar(cedula);
    }

    public Cliente buscarCliente(String cedula) {
        return clienteRepository.buscarPorCedula(cedula);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.listarTodos();
    }
}
