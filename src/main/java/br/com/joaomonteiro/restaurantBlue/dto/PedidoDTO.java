package br.com.joaomonteiro.restaurantBlue.dto;

import br.com.joaomonteiro.restaurantBlue.auxiliar.StatusPagamento;
import br.com.joaomonteiro.restaurantBlue.auxiliar.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    private Long id;
    private String descricao;
    
    @NotNull(message = "Valor não pode ser nulo")
    @Positive(message = "Valor deve ser maior que zero")
    private Double valor;
    
    @NotNull(message = "Status não pode ser nulo")
    private StatusPedido status;
    
    @NotNull(message = "Status de pagamento não pode ser nulo")
    private StatusPagamento statusPagamento;
    
    private Long mesaId;
    private Long clienteId;

}

