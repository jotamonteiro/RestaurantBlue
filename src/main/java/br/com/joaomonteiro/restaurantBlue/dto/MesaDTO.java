package br.com.joaomonteiro.restaurantBlue.dto;

import br.com.joaomonteiro.restaurantBlue.auxiliar.StatusMesa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MesaDTO {

    private Long id;
    
    @Positive(message = "Número da mesa deve ser maior que zero")
    private int numero;
    
    @Min(value = 1, message = "Capacidade mínima é 1")
    private int capacidade;
    
    private StatusMesa status;

}

