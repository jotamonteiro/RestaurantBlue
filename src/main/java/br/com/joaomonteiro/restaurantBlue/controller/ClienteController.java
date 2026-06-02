package br.com.joaomonteiro.restaurantBlue.controller;


import br.com.joaomonteiro.restaurantBlue.dto.ClienteDTO;
import br.com.joaomonteiro.restaurantBlue.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
@CrossOrigin

public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDTO> criarCliente(@Valid @RequestBody ClienteDTO clienteDTO){
        ClienteDTO resultado = clienteService.criarCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
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
    public ResponseEntity<Void> excluirCliente(@PathVariable Long id){
        clienteService.excluirCliente(id);
        return ResponseEntity.noContent().build();
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
