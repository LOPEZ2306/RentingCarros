package com.renting.domain.repository;

import com.renting.domain.model.ContratoRenting;
import java.util.List;

public interface ContratoRepository {
    void guardar(ContratoRenting contrato);
    void modificar(ContratoRenting contrato);
    ContratoRenting buscarPorId(String idContrato);
    ContratoRenting buscarActivoPorCliente(String cedulaCliente);
    List<ContratoRenting> listarTodos();
}
