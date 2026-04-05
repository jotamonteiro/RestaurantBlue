package br.com.joaomonteiro.restaurantBlue.model;

import br.com.joaomonteiro.restaurantBlue.auxiliar.Categoria;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    @NotBlank
    private Double preco;
    @NotBlank
    private Categoria categoria;
    private boolean disponivel = false;
}
