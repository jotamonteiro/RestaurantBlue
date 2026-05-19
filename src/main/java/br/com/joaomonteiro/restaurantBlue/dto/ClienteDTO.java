package br.com.joaomonteiro.restaurantBlue.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO extends PessoaDTO {

    private Long id;
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

