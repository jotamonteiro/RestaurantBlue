package br.com.joaomonteiro.restaurantBlue.dto;

import br.com.joaomonteiro.restaurantBlue.auxiliar.StatusMesa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MesaDTO {

    private Long id;
    private int numero;
    private int capacidade;
    private StatusMesa status;

}

