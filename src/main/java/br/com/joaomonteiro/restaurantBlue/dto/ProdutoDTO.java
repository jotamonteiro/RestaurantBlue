package br.com.joaomonteiro.restaurantBlue.dto;

import br.com.joaomonteiro.restaurantBlue.auxiliar.Categoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

    private Long id;
    
    @NotBlank(message = "Nome do produto não pode ser vazio")
    private String nome;
    
    private String descricao;
    
    @NotNull(message = "Preço não pode ser nulo")
    @Positive(message = "Preço deve ser maior que zero")
    private Double preco;
    
    @NotNull(message = "Categoria não pode ser nula")
    private Categoria categoria;
    
    private boolean disponivel;

}

