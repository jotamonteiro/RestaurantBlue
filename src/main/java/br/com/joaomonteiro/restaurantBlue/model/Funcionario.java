package br.com.joaomonteiro.restaurantBlue.model;


import br.com.joaomonteiro.restaurantBlue.auxiliar.Cargo;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import java.time.LocalDate;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class Funcionario extends Pessoa{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Cargo cargo;

    @NotNull
    @Past
    private LocalDate dataAdm;

    private String cep;

    @JsonProperty("numero")
    private String numero;

    private String logradouro;

    private String bairro;

    private String localidade;

    private String uf;

}
