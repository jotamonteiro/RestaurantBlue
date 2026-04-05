package br.com.joaomonteiro.restaurantBlue.model;


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
    private String cpf;

}
