package br.com.joaomonteiro.restaurantBlue.mapper;

import br.com.joaomonteiro.restaurantBlue.dto.MesaDTO;
import br.com.joaomonteiro.restaurantBlue.model.Mesa;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MesaMapper {
    MesaDTO toDTO(Mesa mesa);
    Mesa toEntity(MesaDTO mesaDTO);
    List<MesaDTO> toDTOList(List<Mesa> mesas);
}
