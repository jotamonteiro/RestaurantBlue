package br.com.joaomonteiro.restaurantBlue.model;

import br.com.joaomonteiro.restaurantBlue.auxiliar.StatusPagamento;
import br.com.joaomonteiro.restaurantBlue.auxiliar.StatusPedido;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @NotNull
    @Positive
    private Double valor;

    @Enumerated(EnumType.STRING)
    @NotNull
    private StatusPedido status;

    @Enumerated(EnumType.STRING)
    @NotNull
    private StatusPagamento statusPagamento;

    @ManyToOne
    @JoinColumn(name = "mesa_id", nullable = true)
    private Mesa mesa;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = true)
    private Cliente cliente;

}
