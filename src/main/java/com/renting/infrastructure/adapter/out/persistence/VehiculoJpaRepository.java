package com.renting.infrastructure.adapter.out.persistence;

import com.renting.domain.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

// Esta interfaz le dice a Spring Data JPA que genere automáticamente
// los métodos básicos de base de datos para Vehiculo:
// save(), findById(), findAll(), delete(), etc.
// No hay que escribir nada más aquí; Spring lo hace solo.
public interface VehiculoJpaRepository extends JpaRepository<Vehiculo, String> {
}
