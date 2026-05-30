package com.renting.infrastructure.adapter.out.persistence;

import com.renting.domain.model.ContratoRenting;
import com.renting.domain.repository.ContratoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Adaptador en memoria usando vector dinámico (ArrayList) para HU6.
 */
@Repository
public class InMemoryContratoRepository implements ContratoRepository {

    private final List<ContratoRenting> vectorContratos = new ArrayList<>();

    @Override
    public void guardar(ContratoRenting contrato) {
        vectorContratos.add(contrato);
    }

    @Override
    public void modificar(ContratoRenting contrato) {
        for (int i = 0; i < vectorContratos.size(); i++) {
            if (vectorContratos.get(i).getIdContrato().equals(contrato.getIdContrato())) {
                vectorContratos.set(i, contrato);
                return;
            }
        }
    }

    @Override
    public ContratoRenting buscarPorId(String idContrato) {
        for (int i = 0; i < vectorContratos.size(); i++) {
            ContratoRenting contrato = vectorContratos.get(i);
            if (contrato.getIdContrato().equals(idContrato)) {
                return contrato;
            }
        }
        return null;
    }

    @Override
    public ContratoRenting buscarActivoPorCliente(String cedulaCliente) {
        for (int i = 0; i < vectorContratos.size(); i++) {
            ContratoRenting contrato = vectorContratos.get(i);
            if (contrato.getCedulaCliente().equals(cedulaCliente) && "activo".equalsIgnoreCase(contrato.getEstado())) {
                return contrato;
            }
        }
        return null;
    }

    @Override
    public List<ContratoRenting> listarTodos() {
        return new ArrayList<>(vectorContratos);
    }
}
