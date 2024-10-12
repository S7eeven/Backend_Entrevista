package com.steven.entrevista.springboot.springboot_entrevista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.steven.entrevista.springboot.springboot_entrevista.entity.Persona;
import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    Optional<Persona> findByIdentificacion(String identificacion);
}
