package br.com.joaomonteiro.restaurantBlue.client;

import br.com.joaomonteiro.restaurantBlue.dto.BrasilApiCepResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="BrasilApiClient", url = "${brasilapi.url:https://brasilapi.com.br/api}")
public interface BrasilApiClient {

    @GetMapping("/cep/v2/{cep}")
    BrasilApiCepResponseDTO buscarPorCep(@PathVariable("cep") String cep);

}

