package br.com.joaomonteiro.restaurantBlue.controller;


import br.com.joaomonteiro.restaurantBlue.model.Cliente;
import br.com.joaomonteiro.restaurantBlue.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")

public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public Cliente criarCliente(@RequestBody Cliente cliente){
        return clienteService.criarCliente(cliente);
    }

    @GetMapping
    public List<Cliente> listarClientes(){
        return clienteService.listarClientes();
    }

    @GetMapping("/{id}")
    public Cliente buscarPorID(@PathVariable Long id){
        return clienteService.buscarPorID(id);
    }

    @PutMapping("/{id}")
    public Cliente atualizarCliente(@RequestBody Cliente cliente,@PathVariable Long id) {
        return clienteService.atualizarCliente(cliente, id);
    }

    @DeleteMapping("/{id}")
    public void excluirCliente(@PathVariable Long id){
        clienteService.excluirCliente(id);
    }

}
