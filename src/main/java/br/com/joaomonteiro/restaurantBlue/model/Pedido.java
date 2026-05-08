package br.com.joaomonteiro.restaurantBlue.model;

import br.com.joaomonteiro.restaurantBlue.auxiliar.StatusPagamento;
import br.com.joaomonteiro.restaurantBlue.auxiliar.StatusPedido;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private Double valor;
    @Enumerated(EnumType.STRING)
    private StatusPedido status;
    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;

}
