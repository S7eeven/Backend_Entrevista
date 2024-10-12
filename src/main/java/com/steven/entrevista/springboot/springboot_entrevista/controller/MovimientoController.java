package com.steven.entrevista.springboot.springboot_entrevista.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.steven.entrevista.springboot.springboot_entrevista.entity.Movimiento;
import com.steven.entrevista.springboot.springboot_entrevista.service.MovimientoService;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
    @Autowired
    private MovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<Movimiento> registrarMovimiento(@RequestParam String numeroCuenta,
                                                           @RequestParam String tipoMovimiento,
                                                           @RequestParam BigDecimal valor) {
        Movimiento movimiento = movimientoService.registrarMovimiento(numeroCuenta, tipoMovimiento, valor);
        return ResponseEntity.ok(movimiento);
    }

    @GetMapping
    public ResponseEntity<List<Movimiento>> listarMovimientos(@RequestParam String numeroCuenta) {
        List<Movimiento> movimientos = movimientoService.listarMovimientosPorCuenta(numeroCuenta);
        return ResponseEntity.ok(movimientos);
    }
}