package com.renting.infrastructure.adapter.out.persistence;

import com.renting.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

// Esta interfaz le dice a Spring Data JPA que genere automáticamente
// los métodos básicos de base de datos para Cliente:
// save(), findById(), findAll(), delete(), etc.
// No hay que escribir nada más aquí; Spring lo hace solo.
public interface ClienteJpaRepository extends JpaRepository<Cliente, String> {
}
