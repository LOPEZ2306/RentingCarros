package com.renting.infrastructure.adapter.in.web;

import com.renting.application.service.VehiculoService;
import com.renting.domain.model.Vehiculo;
import com.renting.domain.model.CarroSedan;
import com.renting.domain.model.CamionetaSUV;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    private final VehiculoService vehiculoService;

    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    @GetMapping
    public List<Vehiculo> listarVehiculos() {
        return vehiculoService.listarVehiculos();
    }

    @GetMapping("/{placa}")
    public ResponseEntity<?> buscarVehiculo(@PathVariable String placa) {
        Vehiculo v = vehiculoService.buscarVehiculo(placa);
        if (v != null) {
            return ResponseEntity.ok(v);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> registrarVehiculo(@RequestBody Map<String, Object> payload) {
        try {
            String tipo = (String) payload.get("tipo");
            Vehiculo v;
            if ("sedan".equalsIgnoreCase(tipo)) {
                v = new CarroSedan(
                    (String) payload.get("placa"),
                    (String) payload.get("marca"),
                    Integer.parseInt(payload.get("modelo").toString()),
                    Float.parseFloat(payload.get("precioDiario").toString()),
                    (String) payload.get("estado"),
                    (String) payload.get("tipoCombustible"),
                    (String) payload.get("transmision")
                );
            } else if ("suv".equalsIgnoreCase(tipo)) {
                v = new CamionetaSUV(
                    (String) payload.get("placa"),
                    (String) payload.get("marca"),
                    Integer.parseInt(payload.get("modelo").toString()),
                    Float.parseFloat(payload.get("precioDiario").toString()),
                    (String) payload.get("estado"),
                    (String) payload.get("traccion"),
                    Float.parseFloat(payload.get("capacidadMaletero").toString())
                );
            } else {
                return ResponseEntity.badRequest().body("Tipo de vehículo inválido.");
            }
            
            vehiculoService.registrarVehiculo(v);
            return ResponseEntity.ok("Vehículo registrado exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Datos inválidos.");
        }
    }

    @PutMapping
    public ResponseEntity<String> modificarVehiculo(@RequestBody Map<String, Object> payload) {
        try {
            String tipo = (String) payload.get("tipo");
            Vehiculo v;
            if ("sedan".equalsIgnoreCase(tipo)) {
                v = new CarroSedan(
                    (String) payload.get("placa"),
                    (String) payload.get("marca"),
                    Integer.parseInt(payload.get("modelo").toString()),
                    Float.parseFloat(payload.get("precioDiario").toString()),
                    (String) payload.get("estado"),
                    (String) payload.get("tipoCombustible"),
                    (String) payload.get("transmision")
                );
            } else if ("suv".equalsIgnoreCase(tipo)) {
                v = new CamionetaSUV(
                    (String) payload.get("placa"),
                    (String) payload.get("marca"),
                    Integer.parseInt(payload.get("modelo").toString()),
                    Float.parseFloat(payload.get("precioDiario").toString()),
                    (String) payload.get("estado"),
                    (String) payload.get("traccion"),
                    Float.parseFloat(payload.get("capacidadMaletero").toString())
                );
            } else {
                return ResponseEntity.badRequest().body("Tipo de vehículo inválido.");
            }

            vehiculoService.modificarVehiculo(v);
            return ResponseEntity.ok("Vehículo modificado exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Datos inválidos.");
        }
    }

    @DeleteMapping("/{placa}")
    public ResponseEntity<String> eliminarVehiculo(@PathVariable String placa) {
        try {
            vehiculoService.eliminarVehiculo(placa);
            return ResponseEntity.ok("Vehículo eliminado exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
