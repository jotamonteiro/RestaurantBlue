package br.com.joaomonteiro.restaurantBlue.mapper;

import br.com.joaomonteiro.restaurantBlue.dto.ProdutoDTO;
import br.com.joaomonteiro.restaurantBlue.model.Produto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {
    ProdutoDTO toDTO(Produto produto);
    Produto toEntity(ProdutoDTO produtoDTO);
    List<ProdutoDTO> toDTOList(List<Produto> produtos);
}
