package com.steven.entrevista.springboot.springboot_entrevista.service;

import java.time.LocalDate;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.steven.entrevista.springboot.springboot_entrevista.dto.CuentaDTO;
import com.steven.entrevista.springboot.springboot_entrevista.dto.EstadoCuentaDTO;
import com.steven.entrevista.springboot.springboot_entrevista.dto.MovimientoDTO;
import com.steven.entrevista.springboot.springboot_entrevista.entity.Cliente;
import com.steven.entrevista.springboot.springboot_entrevista.exception.ResourceNotFoundException;
import com.steven.entrevista.springboot.springboot_entrevista.repository.ClienteRepository;

@Service
public class ReporteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public EstadoCuentaDTO generarEstadoCuenta(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id " + clienteId));

        EstadoCuentaDTO estadoCuenta = new EstadoCuentaDTO();
        estadoCuenta.setClienteId(cliente.getClienteid());
        estadoCuenta.setNombreCliente(cliente.getPersona().getNombre());

        estadoCuenta.setCuentas(
                cliente.getCuentas().stream().map(cuenta -> {
                    CuentaDTO cuentaDTO = new CuentaDTO();
                    cuentaDTO.setNumeroCuenta(cuenta.getNumeroCuenta());
                    cuentaDTO.setTipoCuenta(cuenta.getTipoCuenta());
                    cuentaDTO.setSaldoInicial(cuenta.getSaldoInicial());

                    cuentaDTO.setMovimientos(
                            cuenta.getMovimientos().stream()
                                    .filter(mov -> !mov.getFecha().isBefore(fechaInicio) && !mov.getFecha().isAfter(fechaFin))
                                    .map(mov -> {
                                        MovimientoDTO movimientoDTO = new MovimientoDTO();
                                        movimientoDTO.setId(mov.getId());
                                        movimientoDTO.setFecha(mov.getFecha());
                                        movimientoDTO.setTipoMovimiento(mov.getTipoMovimiento());
                                        movimientoDTO.setValor(mov.getValor());
                                        movimientoDTO.setSaldoDisponible(mov.getSaldoDisponible());
                                        return movimientoDTO;
                                    })
                                    .collect(Collectors.toList())
                    );

                    return cuentaDTO;
                }).collect(Collectors.toList())
        );

        return estadoCuenta;
    }
}
