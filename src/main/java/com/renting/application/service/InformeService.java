package com.renting.application.service;

import com.renting.domain.model.ContratoRenting;
import com.renting.domain.model.Vehiculo;
import com.renting.domain.repository.ContratoRepository;
import com.renting.domain.repository.VehiculoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InformeService {

    private final ContratoRepository contratoRepository;
    private final VehiculoRepository vehiculoRepository;

    public InformeService(ContratoRepository contratoRepository,
                          VehiculoRepository vehiculoRepository) {
        this.contratoRepository = contratoRepository;
        this.vehiculoRepository = vehiculoRepository;
    }

    public List<ContratoRenting> obtenerContratosActivos() {
        List<ContratoRenting> todosLosContratos = contratoRepository.listarTodos();
        List<ContratoRenting> activos = new ArrayList<>();

        for (int i = 0; i < todosLosContratos.size(); i++) {
            ContratoRenting contrato = todosLosContratos.get(i);
            if ("activo".equalsIgnoreCase(contrato.getEstado())) {
                activos.add(contrato);
            }
        }
        return activos;
    }

    public List<ContratoRenting> obtenerContratosFinalizados() {
        List<ContratoRenting> todosLosContratos = contratoRepository.listarTodos();
        List<ContratoRenting> finalizados = new ArrayList<>();

        for (int i = 0; i < todosLosContratos.size(); i++) {
            ContratoRenting contrato = todosLosContratos.get(i);
            if ("finalizado".equalsIgnoreCase(contrato.getEstado())) {
                finalizados.add(contrato);
            }
        }
        return finalizados;
    }

    public int contarContratosActivos() {
        return obtenerContratosActivos().size();
    }

    public ResumenVehiculos obtenerResumenVehiculos() {
        List<Vehiculo> todosLosVehiculos = vehiculoRepository.listarTodos();

        int disponibles = 0;
        int alquilados  = 0;

        for (int i = 0; i < todosLosVehiculos.size(); i++) {
            String estado = todosLosVehiculos.get(i).getEstado();
            if ("disponible".equalsIgnoreCase(estado)) {
                disponibles++;
            } else if ("alquilado".equalsIgnoreCase(estado)) {
                alquilados++;
            }
        }

        return new ResumenVehiculos(disponibles, alquilados);
    }

    public float calcularIngresosTotales() {
        List<ContratoRenting> activos = obtenerContratosActivos();
        float total = 0;

        for (int i = 0; i < activos.size(); i++) {
            total += activos.get(i).getValorTotal();
        }
        return total;
    }

    public String generarResumen(ContratoRenting contrato) {
        return "Contrato " + contrato.getIdContrato()
             + " | Cliente: " + contrato.getCedulaCliente()
             + " | Vehículo: " + contrato.getPlacaVehiculo()
             + " | Estado: " + contrato.getEstado()
             + " | Valor: $" + contrato.getValorTotal();
    }

    public String generarResumen(Vehiculo vehiculo) {
        return "Vehículo " + vehiculo.getPlaca()
             + " | Marca: " + vehiculo.getMarca()
             + " | Modelo: " + vehiculo.getModelo()
             + " | Estado: " + vehiculo.getEstado()
             + " | Precio/día: $" + vehiculo.getPrecioDiario();
    }

    public String generarResumen(List<ContratoRenting> contratos) {
        if (contratos.isEmpty()) {
            return "No hay contratos para mostrar.";
        }

        StringBuilder resumen = new StringBuilder();
        for (int i = 0; i < contratos.size(); i++) {
            resumen.append(generarResumen(contratos.get(i)));
            resumen.append("\n");
        }
        return resumen.toString();
    }

    public static class ResumenVehiculos {
        private final int disponibles;
        private final int alquilados;

        public ResumenVehiculos(int disponibles, int alquilados) {
            this.disponibles = disponibles;
            this.alquilados  = alquilados;
        }

        public int getDisponibles() { return disponibles; }
        public int getAlquilados()  { return alquilados;  }
    }
}