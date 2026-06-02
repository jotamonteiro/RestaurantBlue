package br.com.joaomonteiro.restaurantBlue.mapper;

import br.com.joaomonteiro.restaurantBlue.dto.PedidoDTO;
import br.com.joaomonteiro.restaurantBlue.model.Pedido;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
    Pedido toEntity(PedidoDTO pedido);
    PedidoDTO toDTO(Pedido pedido);
    List<PedidoDTO> toDTOList(List<Pedido> pedidos);
}
