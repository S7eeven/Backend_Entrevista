package com.steven.entrevista.springboot.springboot_entrevista;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.steven.entrevista.springboot.springboot_entrevista.controller.ClienteController;
import com.steven.entrevista.springboot.springboot_entrevista.entity.Cliente;
import com.steven.entrevista.springboot.springboot_entrevista.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCrearCliente() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setClienteid(1L);
        cliente.setContraseña("1234");
        cliente.setEstado(true);

        Mockito.when(clienteService.crearCliente(Mockito.any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clienteid").value(1L))
                .andExpect(jsonPath("$.contraseña").value("1234"))
                .andExpect(jsonPath("$.estado").value(true));
    }

    @Test
    public void testListarClientes() throws Exception {
        Cliente cliente1 = new Cliente();
        cliente1.setClienteid(1L);
        cliente1.setContraseña("1234");
        cliente1.setEstado(true);

        Cliente cliente2 = new Cliente();
        cliente2.setClienteid(2L);
        cliente2.setContraseña("5678");
        cliente2.setEstado(true);

        Mockito.when(clienteService.listarClientes()).thenReturn(Arrays.asList(cliente1, cliente2));

        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

}
