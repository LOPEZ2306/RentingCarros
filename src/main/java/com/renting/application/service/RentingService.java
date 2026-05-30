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
        // Validar que el ID del contrato no exista
        if (contratoRepository.buscarPorId(contrato.getIdContrato()) != null) {
            throw new IllegalArgumentException("Ya existe un contrato con ese ID.");
        }

        // Validar existencia de cliente y vehiculo
        if (clienteService.buscarCliente(contrato.getCedulaCliente()) == null) {
            throw new IllegalArgumentException("El cliente no existe en el sistema.");
        }
        
        Vehiculo vehiculo = vehiculoService.buscarVehiculo(contrato.getPlacaVehiculo());
        if (vehiculo == null) {
            throw new IllegalArgumentException("El vehículo no existe en el sistema.");
        }

        // Regla 1: No alquilar más de un vehículo por cliente al mismo tiempo
        if (contratoRepository.buscarActivoPorCliente(contrato.getCedulaCliente()) != null) {
            throw new IllegalArgumentException("El cliente ya tiene un vehículo alquilado actualmente.");
        }

        // Regla 2: No alquilar vehículo en estado 'alquilado'
        if ("alquilado".equalsIgnoreCase(vehiculo.getEstado())) {
            throw new IllegalArgumentException("El vehículo solicitado ya se encuentra alquilado.");
        }

        // Establecer estado inicial
        contrato.setEstado("activo");

        // Guardar contrato
        contratoRepository.guardar(contrato);

        // Actualizar estado del vehículo a alquilado
        vehiculo.setEstado("alquilado");
        vehiculoService.modificarVehiculo(vehiculo);
    }

    public void modificarContrato(ContratoRenting contratoModificado) {
        ContratoRenting contratoActual = contratoRepository.buscarPorId(contratoModificado.getIdContrato());
        if (contratoActual == null) {
            throw new IllegalArgumentException("El contrato que intenta modificar no existe.");
        }

        // Regla 3: Los contratos pueden modificarse solo en los campos de fecha o duración
        // El IdContrato, Cédula y Placa no pueden modificarse.
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

        // Finalizar el contrato
        contrato.setEstado("finalizado");
        contratoRepository.modificar(contrato);

        // Regla 4: Volver el vehículo a disponible
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
