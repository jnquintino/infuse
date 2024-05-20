package br.com.infuse.services;

import br.com.infuse.entities.Pedido;
import br.com.infuse.repositories.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class PedidoServiceTest {

    @InjectMocks
    private PedidoService pedidoService;

    @Mock
    private PedidoRepository pedidoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConsultarPedidos() {
        Integer numeroPedido = 123;
        LocalDate dataCadastro = LocalDate.of(2024, 5, 16);
        Pedido pedido = new Pedido();
        pedido.setNumeroControle(numeroPedido);
        pedido.setDataCadastro(dataCadastro);

        when(pedidoRepository.consultarPedidos(numeroPedido, dataCadastro))
                .thenReturn(Collections.singletonList(pedido));

        List<Pedido> pedidos = pedidoService.consultarPedidos(numeroPedido, dataCadastro);
        assertEquals(1, pedidos.size());
        assertEquals(numeroPedido, pedidos.get(0).getNumeroControle());
        assertEquals(dataCadastro, pedidos.get(0).getDataCadastro());
    }
}

