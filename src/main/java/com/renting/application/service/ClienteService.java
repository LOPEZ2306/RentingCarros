package com.renting.application.service;

import com.renting.domain.model.Cliente;
import com.renting.domain.repository.ClienteRepository;
import com.renting.domain.repository.ContratoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ContratoRepository contratoRepository;

    public ClienteService(ClienteRepository clienteRepository,
                          ContratoRepository contratoRepository) {
        this.clienteRepository = clienteRepository;
        this.contratoRepository = contratoRepository;
    }

    public void registrarCliente(Cliente cliente) {
        boolean yaExiste = clienteRepository.buscarPorCedula(cliente.getCedula()) != null;
        if (yaExiste) {
            throw new IllegalArgumentException("Ya existe un cliente con la cédula ingresada.");
        }
        clienteRepository.guardar(cliente);
    }

    public void modificarCliente(Cliente cliente) {
        boolean noExiste = clienteRepository.buscarPorCedula(cliente.getCedula()) == null;
        if (noExiste) {
            throw new IllegalArgumentException("No se encontró el cliente a modificar.");
        }
        clienteRepository.modificar(cliente);
    }

    public void eliminarCliente(String cedula) {
        boolean noExiste = clienteRepository.buscarPorCedula(cedula) == null;
        if (noExiste) {
            throw new IllegalArgumentException("No se encontró el cliente a eliminar.");
        }

        boolean tieneContratoActivo = contratoRepository.buscarActivoPorCliente(cedula) != null;
        if (tieneContratoActivo) {
            throw new IllegalArgumentException(
                "No se puede eliminar el cliente porque tiene un contrato activo. " +
                "Finalice el contrato primero."
            );
        }

        clienteRepository.eliminar(cedula);
    }

    public Cliente buscarCliente(String cedula) {
        return clienteRepository.buscarPorCedula(cedula);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.listarTodos();
    }
}