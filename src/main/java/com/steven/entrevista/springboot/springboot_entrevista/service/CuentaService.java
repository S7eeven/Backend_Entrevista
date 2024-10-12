package com.steven.entrevista.springboot.springboot_entrevista.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.steven.entrevista.springboot.springboot_entrevista.exception.ResourceNotFoundException;
import com.steven.entrevista.springboot.springboot_entrevista.entity.Cuenta;
import com.steven.entrevista.springboot.springboot_entrevista.repository.CuentaRepository;
import java.util.List;

@Service
public class CuentaService {
    
    @Autowired
    private CuentaRepository cuentaRepository;

    public Cuenta crearCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    public List<Cuenta> listarCuentas() {
        return cuentaRepository.findAll();
    }

    public Cuenta obtenerCuentaPorId(Long id) {
        return cuentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con id " + id));
    }

    public Cuenta actualizarCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    public void eliminarCuenta(Long id) {
        cuentaRepository.deleteById(id);
    }
}
