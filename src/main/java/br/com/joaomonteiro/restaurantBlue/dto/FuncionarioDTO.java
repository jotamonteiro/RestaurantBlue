package br.com.joaomonteiro.restaurantBlue.dto;

import br.com.joaomonteiro.restaurantBlue.auxiliar.Cargo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioDTO extends PessoaDTO {

    private Long id;
    
    @NotNull(message = "Cargo não pode ser nulo")
    private Cargo cargo;
    
    @NotNull(message = "Data de admissão não pode ser nula")
    @PastOrPresent(message = "Data de admissão não pode ser futura")
    private LocalDate dataAdm;
    
    private String cep;
    @JsonProperty("numero")
    private String numero;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;

    public FuncionarioDTO(String nome, LocalDate datanasc, String cpf, long id, Cargo cargo, LocalDate dataAdm,
                          String cep, String logradouro, String bairro, String localidade, String uf, String numero) {
        super(nome, datanasc, cpf);
        this.id = id;
        this.cargo = cargo;
        this.dataAdm = dataAdm;
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
        this.numero = numero;
    }

}

