package br.com.joaomonteiro.restaurantBlue.model;


import br.com.joaomonteiro.restaurantBlue.validation.CpfValidation;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class Pessoa {

    @NotBlank
    protected String nome;

    @NotNull
    @Past
    protected LocalDate datanasc;

    @Column(unique = true)
    @CpfValidation
    private String cpf;

}
