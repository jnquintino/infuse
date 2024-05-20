package br.com.infuse.controllers;

import br.com.infuse.entities.Pedido;
import br.com.infuse.services.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PedidoController.class)
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    @BeforeEach
    void setUp() {
        Pedido pedido = new Pedido();
        pedido.setNumeroControle(123);
        pedido.setDataCadastro(LocalDate.of(2024, 5, 16));
        when(pedidoService.consultarPedidos(anyInt(), any(LocalDate.class)))
                .thenReturn(Collections.singletonList(pedido));
    }

    @Test
    void testConsultarPedidos() throws Exception {
        mockMvc.perform(get("/pedidos/consultar")
                        .param("numeroPedido", "123")
                        .param("dataCadastro", "2024-05-16"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'numeroControle':123,'dataCadastro':'2024-05-16'}]"));
    }
}

