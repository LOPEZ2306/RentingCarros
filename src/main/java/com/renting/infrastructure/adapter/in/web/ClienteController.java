package com.renting.infrastructure.adapter.in.web;

import com.renting.application.service.ClienteService;
import com.renting.domain.model.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    @GetMapping("/{cedula}")
    public ResponseEntity<?> buscarCliente(@PathVariable String cedula) {
        Cliente c = clienteService.buscarCliente(cedula);
        if (c != null) {
            return ResponseEntity.ok(c);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> registrarCliente(@RequestBody Cliente cliente) {
        try {
            clienteService.registrarCliente(cliente);
            return ResponseEntity.ok("Cliente registrado exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> modificarCliente(@RequestBody Cliente cliente) {
        try {
            clienteService.modificarCliente(cliente);
            return ResponseEntity.ok("Cliente modificado exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{cedula}")
    public ResponseEntity<String> eliminarCliente(@PathVariable String cedula) {
        try {
            clienteService.eliminarCliente(cedula);
            return ResponseEntity.ok("Cliente eliminado exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
