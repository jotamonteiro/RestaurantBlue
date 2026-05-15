package br.com.joaomonteiro.restaurantBlue.controller;

import br.com.joaomonteiro.restaurantBlue.dto.MesaDTO;
import br.com.joaomonteiro.restaurantBlue.service.MesaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mesas")
@RequiredArgsConstructor
@CrossOrigin
public class MesaController {

    private final MesaService service;

    @PostMapping
    public ResponseEntity<MesaDTO> criarMesa(@Valid @RequestBody MesaDTO mesaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarMesa(mesaDTO));
    }

    @GetMapping
    public List<MesaDTO> listarMesas() {
        return service.listarMesas();
    }

    @GetMapping("/{id}")
    public MesaDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorID(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MesaDTO> atualizarMesa(@Valid @RequestBody MesaDTO mesaDTO, @PathVariable Long id) {
        return ResponseEntity.ok(service.atualizarMesa(mesaDTO, id));
    }

    @DeleteMapping("/{id}")
    public void excluirMesa(@PathVariable Long id) {
        service.excluirMesa(id);
    }

}

