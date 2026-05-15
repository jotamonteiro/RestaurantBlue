package br.com.joaomonteiro.restaurantBlue.dto;

import br.com.joaomonteiro.restaurantBlue.auxiliar.Cargo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioDTO extends PessoaDTO {

    private long id;
    private Cargo cargo;
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

