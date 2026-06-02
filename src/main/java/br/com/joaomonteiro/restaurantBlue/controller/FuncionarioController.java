package br.com.joaomonteiro.restaurantBlue.controller;


import br.com.joaomonteiro.restaurantBlue.auxiliar.Cargo;
import br.com.joaomonteiro.restaurantBlue.dto.FuncionarioDTO;
import br.com.joaomonteiro.restaurantBlue.service.FuncionarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
@RequiredArgsConstructor
@CrossOrigin

public class FuncionarioController {

    private final FuncionarioService funcService;

    @PostMapping
    public ResponseEntity<FuncionarioDTO> criarFunc(@Valid @RequestBody FuncionarioDTO funcionarioDTO){
        FuncionarioDTO resultado = funcService.criarFunc(funcionarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    @GetMapping
    public List<FuncionarioDTO> listarFunc(){
        return funcService.listarFunc();
    }

    @GetMapping("/{id}")
    public FuncionarioDTO buscarPorId(@PathVariable Long id){
        return funcService.buscarPorID(id);
    }

    @PutMapping("/{id}")
    public FuncionarioDTO atualizarFunc(@Valid @RequestBody FuncionarioDTO funcionarioDTO, @PathVariable Long id){
        return funcService.atualizarFunc(funcionarioDTO,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirFunc(@PathVariable Long id){
        funcService.excluirFunc(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cargo/{cargo}")
    public List<FuncionarioDTO> buscarPorCargo(@PathVariable Cargo cargo) {
        return funcService.buscarPorCargo(cargo);
    }

}
