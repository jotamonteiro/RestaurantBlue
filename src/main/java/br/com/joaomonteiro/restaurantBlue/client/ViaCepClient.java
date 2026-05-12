package br.com.joaomonteiro.restaurantBlue.client;


import br.com.joaomonteiro.restaurantBlue.dto.ViaCepResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="ViaCepClient", url = "${viacep.url:https://viacep.com.br/ws}")
public interface ViaCepClient {

    @GetMapping("/{cep}/json/")
    ViaCepResponseDTO buscarPorCep(@PathVariable("cep") String cep);

}
