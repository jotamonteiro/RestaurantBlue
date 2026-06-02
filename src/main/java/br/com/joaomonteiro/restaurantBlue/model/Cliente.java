package br.com.joaomonteiro.restaurantBlue.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Cliente extends Pessoa{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String telefone;
    private String email;
    
    private String cep;

    @JsonProperty("numero")
    private String numero;

    private String logradouro;

    private String bairro;

    private String localidade;

    private String uf;

}
