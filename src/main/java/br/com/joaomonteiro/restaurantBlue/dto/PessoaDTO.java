package br.com.joaomonteiro.restaurantBlue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {

    protected String nome;
    protected LocalDate datanasc;
    private String cpf;

}

