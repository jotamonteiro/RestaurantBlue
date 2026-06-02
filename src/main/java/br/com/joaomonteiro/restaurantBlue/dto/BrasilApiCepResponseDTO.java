package br.com.joaomonteiro.restaurantBlue.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BrasilApiCepResponseDTO {

    private String cep;
    
    @JsonProperty("street")
    private String logradouro;
    
    @JsonProperty("neighborhood")
    private String bairro;
    
    @JsonProperty("city")
    private String localidade;
    
    @JsonProperty("state")
    private String uf;

}

