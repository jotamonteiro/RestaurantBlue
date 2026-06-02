package br.com.joaomonteiro.restaurantBlue.mapper;

import br.com.joaomonteiro.restaurantBlue.dto.ClienteDTO;
import br.com.joaomonteiro.restaurantBlue.model.Cliente;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteDTO toDTO(Cliente cliente);
    Cliente toEntity(ClienteDTO clienteDTO);
    List<ClienteDTO> toDTOList(List<Cliente> clientes);
}
