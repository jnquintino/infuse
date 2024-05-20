package br.com.infuse.controllers;

import br.com.infuse.entities.Pedido;
import br.com.infuse.services.PedidoService;
import br.com.infuse.vos.PedidoVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping(value = "/receber", consumes = {"application/json", "application/xml"})
    public String receberPedido(@RequestBody List<PedidoVO> pedidos) {
        return pedidoService.receberPedido(pedidos);
    }

    @GetMapping("/consultar")
    public List<Pedido> consultarPedidos(
            @RequestParam(required = false) Integer numeroPedido,
            @RequestParam(required = false) LocalDate dataCadastro) {
        return pedidoService.consultarPedidos(numeroPedido, dataCadastro);
    }
}

