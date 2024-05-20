package br.com.infuse.services;

import br.com.infuse.entities.Pedido;
import br.com.infuse.mappers.PedidoMapper;
import br.com.infuse.repositories.PedidoRepository;
import br.com.infuse.vos.PedidoVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class PedidoService {
    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public String receberPedido(List<PedidoVO> pedidos) {
        if (pedidos == null || pedidos.isEmpty()) {
            return "Nenhum pedido recebido.";
        }
        if (pedidos.size() > 10) {
            return "Número máximo de pedidos excedido.";
        }
        for (PedidoVO pedidoVO : pedidos) {
            if (!pedidoRepository.consultarPorNumeroControle(pedidoVO.getNumeroControle()).isEmpty()) {
                return "Pedido com número de controle " + pedidoVO.getNumeroControle() + " já cadastrado.";
            }
            Pedido pedido = PedidoMapper.INSTANCE.toEntity(pedidoVO);
            if (pedido.getQuantidade() == null) {
                pedido.setQuantidade(1);
            }
            double desconto = calcularDesconto(pedido.getQuantidade());
            double valorTotal = calculateValorTotal(pedido.getValor(), pedido.getQuantidade(), desconto);
            pedido.setValorTotal(valorTotal);
            pedidoRepository.salvarPedido(pedido);
        }
        return "Pedidos recebidos com sucesso.";
    }

    private double calcularDesconto(int quantidade) {
        return quantidade >= 10 ? 10 : quantidade > 5 ? 5 : 0;
    }

    private double calculateValorTotal(double valor, int quantidade, double desconto) {
        BigDecimal valorBD = BigDecimal.valueOf(valor);
        BigDecimal quantidadeBD = BigDecimal.valueOf(quantidade);
        BigDecimal descontoBD = BigDecimal.valueOf(desconto).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        BigDecimal total = valorBD.multiply(quantidadeBD).multiply(BigDecimal.ONE.subtract(descontoBD));
        return total.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public List<Pedido> consultarPedidos(Integer numeroPedido, LocalDate dataCadastro) {
        return pedidoRepository.consultarPedidos(numeroPedido, dataCadastro);
    }
}
