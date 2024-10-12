package com.steven.entrevista.springboot.springboot_entrevista;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import com.steven.entrevista.springboot.springboot_entrevista.controller.MovimientoController;
import com.steven.entrevista.springboot.springboot_entrevista.entity.Movimiento;
import com.steven.entrevista.springboot.springboot_entrevista.service.MovimientoService;

@WebMvcTest(MovimientoController.class)
public class MovimientoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovimientoService movimientoService;

    @Test
    public void testRegistrarMovimiento() throws Exception {
        Movimiento movimiento = new Movimiento();
        movimiento.setId(1L);
        movimiento.setFecha(java.time.LocalDate.now());
        movimiento.setTipoMovimiento("Deposito");
        movimiento.setValor(new BigDecimal("600"));
        movimiento.setSaldoDisponible(new BigDecimal("700"));

        Mockito.when(movimientoService.registrarMovimiento(
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.any(BigDecimal.class)))
                .thenReturn(movimiento);

        mockMvc.perform(post("/movimientos")
                .param("numeroCuenta", "225487")
                .param("tipoMovimiento", "Deposito")
                .param("valor", "600")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.tipoMovimiento").value("Deposito"))
                .andExpect(jsonPath("$.valor").value(600))
                .andExpect(jsonPath("$.saldoDisponible").value(700));
    }

}
