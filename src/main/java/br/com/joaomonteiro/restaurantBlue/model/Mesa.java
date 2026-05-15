package br.com.joaomonteiro.restaurantBlue.model;

import br.com.joaomonteiro.restaurantBlue.auxiliar.StatusMesa;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive
    private int numero;

    @Min(1)
    private int capacidade;

    @Enumerated(EnumType.STRING)
    private StatusMesa status;
}