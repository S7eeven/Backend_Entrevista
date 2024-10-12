package com.steven.entrevista.springboot.springboot_entrevista.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.steven.entrevista.springboot.springboot_entrevista.entity.Cuenta;
import com.steven.entrevista.springboot.springboot_entrevista.service.CuentaService;
import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @PostMapping
    public ResponseEntity<Cuenta> crearCuenta(@RequestBody Cuenta cuenta) {
        try {
            Cuenta creado = cuentaService.crearCuenta(cuenta);
            return ResponseEntity.ok(creado);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el cuenta: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Cuenta>> listarCuentas() {
        List<Cuenta> cuentas = cuentaService.listarCuentas();
        return ResponseEntity.ok(cuentas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> obtenerCuenta(@PathVariable Long id) {
        Cuenta cuenta = cuentaService.obtenerCuentaPorId(id);
        return ResponseEntity.ok(cuenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> actualizarCuenta(@PathVariable Long id, @RequestBody Cuenta cuentaDetalles) {
        Cuenta cuenta = cuentaService.obtenerCuentaPorId(id);
        
        cuenta.setNumeroCuenta(cuentaDetalles.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaDetalles.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaDetalles.getSaldoInicial());
        cuenta.setEstado(cuentaDetalles.getEstado());

        Cuenta actualizado = cuentaService.actualizarCuenta(cuenta);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable Long id) {
        cuentaService.eliminarCuenta(id);
        return ResponseEntity.noContent().build();
    }
}
