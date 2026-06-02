package br.com.joaomonteiro.restaurantBlue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import br.com.joaomonteiro.restaurantBlue.validation.CpfValidation;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {

    @NotBlank(message = "Nome não pode ser vazio")
    protected String nome;
    
    @NotNull(message = "Data de nascimento não pode ser nula")
    @Past(message = "Data de nascimento deve ser uma data anterior a hoje")
    protected LocalDate datanasc;
    
    @CpfValidation
    private String cpf;

}

