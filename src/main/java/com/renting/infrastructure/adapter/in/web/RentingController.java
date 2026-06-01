package com.renting.infrastructure.adapter.in.web;

import com.renting.application.service.RentingService;
import com.renting.domain.model.ContratoRenting;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/renting")
public class RentingController {

    private final RentingService rentingService;

    public RentingController(RentingService rentingService) {
        this.rentingService = rentingService;
    }

    @GetMapping
    public List<ContratoRenting> listarContratos() {
        return rentingService.listarContratos();
    }

    @GetMapping("/{idContrato}")
    public ResponseEntity<?> buscarContrato(@PathVariable String idContrato) {
        ContratoRenting c = rentingService.buscarContrato(idContrato);
        if (c != null) {
            return ResponseEntity.ok(c);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/preview")
    public ResponseEntity<?> previewContrato(
            @RequestParam String placa,
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin) {
        try {
            Map<String, Object> preview = rentingService.calcularPreviewContrato(placa, fechaInicio, fechaFin);
            return ResponseEntity.ok(preview);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<String> registrarContrato(@RequestBody ContratoRenting contrato) {
        try {
            rentingService.registrarContrato(contrato);
            return ResponseEntity.ok("Contrato registrado exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> modificarContrato(@RequestBody ContratoRenting contrato) {
        try {
            rentingService.modificarContrato(contrato);
            return ResponseEntity.ok("Contrato modificado exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{idContrato}/finalizar")
    public ResponseEntity<String> finalizarContrato(@PathVariable String idContrato) {
        try {
            rentingService.finalizarContrato(idContrato);
            return ResponseEntity.ok("Contrato finalizado y vehículo devuelto.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}