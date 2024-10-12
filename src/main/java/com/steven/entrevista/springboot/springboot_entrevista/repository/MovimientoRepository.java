package com.steven.entrevista.springboot.springboot_entrevista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.steven.entrevista.springboot.springboot_entrevista.entity.Movimiento;
import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findByCuentaNumeroCuenta(String numeroCuenta);
}
