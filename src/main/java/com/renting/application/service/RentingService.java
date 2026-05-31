package com.renting.application.service;

import com.renting.domain.model.ContratoRenting;
import com.renting.domain.model.Vehiculo;
import com.renting.domain.repository.ContratoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentingService {

    private final ContratoRepository contratoRepository;
    private final VehiculoService vehiculoService;
    private final ClienteService clienteService;

    public RentingService(ContratoRepository contratoRepository, VehiculoService vehiculoService, ClienteService clienteService) {
        this.contratoRepository = contratoRepository;
        this.vehiculoService = vehiculoService;
        this.clienteService = clienteService;
    }

    public void registrarContrato(ContratoRenting contrato) {
        
        if (contratoRepository.buscarPorId(contrato.getIdContrato()) != null) {
            throw new IllegalArgumentException("Ya existe un contrato con ese ID.");
        }

        if (clienteService.buscarCliente(contrato.getCedulaCliente()) == null) {
            throw new IllegalArgumentException("El cliente no existe en el sistema.");
        }
        
        Vehiculo vehiculo = vehiculoService.buscarVehiculo(contrato.getPlacaVehiculo());
        if (vehiculo == null) {
            throw new IllegalArgumentException("El vehículo no existe en el sistema.");
        }

        if (contratoRepository.buscarActivoPorCliente(contrato.getCedulaCliente()) != null) {
            throw new IllegalArgumentException("El cliente ya tiene un vehículo alquilado actualmente.");
        }

        
        if ("alquilado".equalsIgnoreCase(vehiculo.getEstado())) {
            throw new IllegalArgumentException("El vehículo solicitado ya se encuentra alquilado.");
        }

        contrato.setEstado("activo");

        contratoRepository.guardar(contrato);

        vehiculo.setEstado("alquilado");
        vehiculoService.modificarVehiculo(vehiculo);
    }

    public void modificarContrato(ContratoRenting contratoModificado) {
        ContratoRenting contratoActual = contratoRepository.buscarPorId(contratoModificado.getIdContrato());
        if (contratoActual == null) {
            throw new IllegalArgumentException("El contrato que intenta modificar no existe.");
        }

        contratoActual.setFechaInicio(contratoModificado.getFechaInicio());
        contratoActual.setFechaFin(contratoModificado.getFechaFin());
        contratoActual.setTotalDias(contratoModificado.getTotalDias());
        contratoActual.setValorTotal(contratoModificado.getValorTotal());

        contratoRepository.modificar(contratoActual);
    }

    public void finalizarContrato(String idContrato) {
        ContratoRenting contrato = contratoRepository.buscarPorId(idContrato);
        if (contrato == null) {
            throw new IllegalArgumentException("El contrato no existe.");
        }
        if ("finalizado".equalsIgnoreCase(contrato.getEstado())) {
            throw new IllegalArgumentException("El contrato ya fue finalizado anteriormente.");
        }

        contrato.setEstado("finalizado");
        contratoRepository.modificar(contrato);

        Vehiculo vehiculo = vehiculoService.buscarVehiculo(contrato.getPlacaVehiculo());
        if (vehiculo != null) {
            vehiculo.setEstado("disponible");
            vehiculoService.modificarVehiculo(vehiculo);
        }
    }

    public ContratoRenting buscarContrato(String idContrato) {
        return contratoRepository.buscarPorId(idContrato);
    }

    public List<ContratoRenting> listarContratos() {
        return contratoRepository.listarTodos();
    }
}
