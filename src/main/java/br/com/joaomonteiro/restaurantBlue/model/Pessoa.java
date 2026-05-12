package br.com.joaomonteiro.restaurantBlue.model;


import br.com.joaomonteiro.restaurantBlue.validation.CpfValidation;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class Pessoa {

    protected String nome;
    protected LocalDate datanasc;
    @Column(unique = true)
    @CpfValidation
    private String cpf;

}
