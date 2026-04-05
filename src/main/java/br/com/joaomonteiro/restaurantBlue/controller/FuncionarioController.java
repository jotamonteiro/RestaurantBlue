package br.com.joaomonteiro.restaurantBlue.controller;


import br.com.joaomonteiro.restaurantBlue.model.Funcionario;
import br.com.joaomonteiro.restaurantBlue.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioService funcService;

    @PostMapping
    public Funcionario criarFunc(@RequestBody Funcionario funcionario){
        return funcService.criarFunc(funcionario);
    }

    @GetMapping
    public List<Funcionario> listarFunc(){
        return funcService.listarFunc();
    }

    @GetMapping("/{id}")
    public Funcionario buscarPorId(@PathVariable Long id){
        return funcService.buscarPorID(id);
    }

    @PutMapping("/{id}")
    public Funcionario atualizarFunc(@RequestBody Funcionario funcionario, @PathVariable Long id){
        return funcService.atualizarFunc(funcionario,id);
    }

    @DeleteMapping("/{id}")
    public void excluirFunc(@PathVariable Long id){
        funcService.excluirFunc(id);
    }

}
