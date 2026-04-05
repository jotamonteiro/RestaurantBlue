package br.com.joaomonteiro.restaurantBlue.model;


import br.com.joaomonteiro.restaurantBlue.auxiliar.Cargo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.time.LocalDate;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Funcionario extends Pessoa{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private Cargo cargo;
    private LocalDate dataAdm;

}
