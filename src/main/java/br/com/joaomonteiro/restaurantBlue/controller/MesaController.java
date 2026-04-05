package br.com.joaomonteiro.restaurantBlue.controller;

import br.com.joaomonteiro.restaurantBlue.model.Mesa;
import br.com.joaomonteiro.restaurantBlue.service.MesaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mesas")
@RequiredArgsConstructor
public class MesaController {

    private final MesaService service;

    @PostMapping
    public ResponseEntity<Mesa> criarMesa(@RequestBody Mesa mesa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarMesa(mesa));
    }

    @GetMapping
    public List<Mesa> listarMesas() {
        return service.listarMesas();
    }

    @GetMapping("/{id}")
    public Mesa buscarPorId(@PathVariable Long id) {
        return service.buscarPorID(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mesa> atualizarMesa(@RequestBody Mesa mesa, @PathVariable Long id) {
        return ResponseEntity.ok(service.atualizarMesa(mesa, id));
    }

    @DeleteMapping("/{id}")
    public void excluirMesa(@PathVariable Long id) {
        service.excluirMesa(id);
    }

}

