package com.renting.infrastructure.adapter.in.web;

import com.renting.application.service.InformeService;
import com.renting.domain.model.ContratoRenting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/informes")
public class InformeController {

    private final InformeService informeService;

    public InformeController(InformeService informeService) {
        this.informeService = informeService;
    }

    @GetMapping("/contratos")
    public Map<String, List<ContratoRenting>> obtenerInformeContratos() {
        Map<String, List<ContratoRenting>> informe = new HashMap<>();
        informe.put("activos",     informeService.obtenerContratosActivos());
        informe.put("finalizados", informeService.obtenerContratosFinalizados());
        return informe;
    }

    @GetMapping("/vehiculos")
    public Map<String, Integer> obtenerInformeVehiculos() {
        InformeService.ResumenVehiculos resumen = informeService.obtenerResumenVehiculos();

        Map<String, Integer> informe = new HashMap<>();
        informe.put("disponibles", resumen.getDisponibles());
        informe.put("alquilados",  resumen.getAlquilados());
        return informe;
    }

    @GetMapping("/ingresos")
    public Map<String, Float> obtenerInformeIngresos() {
        Map<String, Float> informe = new HashMap<>();
        informe.put("ingresosTotales", informeService.calcularIngresosTotales());
        return informe;
    }
}