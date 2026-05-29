package com.renting.domain.repository;

import com.renting.domain.model.Cliente;
import java.util.List;

public interface ClienteRepository {
    void guardar(Cliente cliente);
    void modificar(Cliente cliente);
    void eliminar(String cedula);
    Cliente buscarPorCedula(String cedula);
    List<Cliente> listarTodos();
}
