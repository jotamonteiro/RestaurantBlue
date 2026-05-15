package br.com.joaomonteiro.restaurantBlue.controller;


import br.com.joaomonteiro.restaurantBlue.auxiliar.Cargo;
import br.com.joaomonteiro.restaurantBlue.dto.FuncionarioDTO;
import br.com.joaomonteiro.restaurantBlue.service.FuncionarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
@RequiredArgsConstructor
@CrossOrigin

public class FuncionarioController {

    private final FuncionarioService funcService;

    @PostMapping
    public FuncionarioDTO criarFunc(@Valid @RequestBody FuncionarioDTO funcionarioDTO){
        return funcService.criarFunc(funcionarioDTO);
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
    public void excluirFunc(@PathVariable Long id){
        funcService.excluirFunc(id);
    }

    @GetMapping("/cargo/{cargo}")
    public List<FuncionarioDTO> buscarPorCargo(@PathVariable Cargo cargo) {
        return funcService.buscarPorCargo(cargo);
    }

}
