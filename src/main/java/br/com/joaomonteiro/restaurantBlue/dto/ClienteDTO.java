package br.com.joaomonteiro.restaurantBlue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO extends PessoaDTO {

    private Long id;
    private String telefone;
    private String email;
    private String endereco;

    public ClienteDTO(String nome, LocalDate datanasc, String cpf, Long id, String telefone, String email, String endereco) {
        super(nome, datanasc, cpf);
        this.id = id;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

}

