package br.com.joaomonteiro.restaurantBlue.controller;


import br.com.joaomonteiro.restaurantBlue.dto.ClienteDTO;
import br.com.joaomonteiro.restaurantBlue.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
@CrossOrigin

public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ClienteDTO criarCliente(@Valid @RequestBody ClienteDTO clienteDTO){
        return clienteService.criarCliente(clienteDTO);
    }

    @GetMapping
    public List<ClienteDTO> listarClientes(){
        return clienteService.listarClientes();
    }

    @GetMapping("/{id}")
    public ClienteDTO buscarPorID(@PathVariable Long id){
        return clienteService.buscarPorID(id);
    }

    @PutMapping("/{id}")
    public ClienteDTO atualizarCliente(@Valid @RequestBody ClienteDTO clienteDTO,@PathVariable Long id) {
        return clienteService.atualizarCliente(clienteDTO, id);
    }

    @DeleteMapping("/{id}")
    public void excluirCliente(@PathVariable Long id){
        clienteService.excluirCliente(id);
    }

    @GetMapping("/nome/buscar")
    public List<ClienteDTO> buscarPorNome(@RequestParam String nome) {
        return clienteService.buscarPorNome(nome);
    }

    @GetMapping("/email/buscar")
    public List<ClienteDTO> buscarPorEmail(@RequestParam String email) {
        return clienteService.buscarPorEmail(email);
    }

}
