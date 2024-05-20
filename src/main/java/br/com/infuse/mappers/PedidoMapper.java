package br.com.infuse.mappers;

import br.com.infuse.entities.Pedido;
import br.com.infuse.vos.PedidoVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PedidoMapper {
    PedidoMapper INSTANCE = Mappers.getMapper(PedidoMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "valorTotal", ignore = true)
    @Mapping(target = "dataCadastro", source = "dataCadastro")
    @Mapping(target = "codigoCliente", source = "codigoCliente")
    @Mapping(target = "numeroControle", source = "numeroControle")
    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "valor", source = "valor")
    @Mapping(target = "quantidade", source = "quantidade")
    Pedido toEntity(PedidoVO vo);

    PedidoVO toVO(Pedido pedido);
}
