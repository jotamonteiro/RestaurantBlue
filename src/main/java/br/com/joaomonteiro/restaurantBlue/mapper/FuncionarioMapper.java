package br.com.joaomonteiro.restaurantBlue.mapper;

import br.com.joaomonteiro.restaurantBlue.dto.FuncionarioDTO;
import br.com.joaomonteiro.restaurantBlue.model.Funcionario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FuncionarioMapper {
    FuncionarioDTO toDTO(Funcionario funcionario);

    Funcionario toEntity(FuncionarioDTO funcionarioDTO);

    List<FuncionarioDTO> toDTOList(List<Funcionario> funcionarios);
}
