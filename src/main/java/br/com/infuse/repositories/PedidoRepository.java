package br.com.infuse.repositories;

import br.com.infuse.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("SELECT p FROM Pedido p WHERE (:numeroPedido IS NULL OR p.numeroControle = :numeroPedido) " +
            "AND (:dataCadastro IS NULL OR p.dataCadastro = :dataCadastro)")
    List<Pedido> consultarPedidos(@Param("numeroPedido") Integer numeroPedido,
                                  @Param("dataCadastro") LocalDate dataCadastro);

    default void salvarPedido(Pedido pedido) {
        save(pedido);
    }

    @Query("SELECT p FROM Pedido p WHERE p.numeroControle = :numeroControle")
    List<Pedido> consultarPorNumeroControle(@Param("numeroControle") Integer numeroControle);
}
