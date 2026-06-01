package com.renting.infrastructure.adapter.out.persistence;

import com.renting.domain.model.ContratoRenting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Esta interfaz le dice a Spring Data JPA que genere automáticamente
// los métodos básicos de base de datos para ContratoRenting.
// El método adicional 'findByCedulaClienteAndEstadoIgnoreCase' lo genera Spring
// automáticamente a partir del nombre del método: busca por cedulaCliente y por estado
// sin importar si está en mayúsculas o minúsculas.
public interface ContratoJpaRepository extends JpaRepository<ContratoRenting, String> {

    // Busca un contrato que coincida con la cédula del cliente Y tenga ese estado (activo/finalizado)
    Optional<ContratoRenting> findByCedulaClienteAndEstadoIgnoreCase(String cedulaCliente, String estado);
}
