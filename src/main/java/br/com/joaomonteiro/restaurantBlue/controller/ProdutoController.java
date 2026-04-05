package br.com.joaomonteiro.restaurantBlue.controller;

import br.com.joaomonteiro.restaurantBlue.model.Produto;
import br.com.joaomonteiro.restaurantBlue.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor

public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public Produto criarProduto(@RequestBody Produto produto){
        return produtoService.criarProduto(produto);
    }

    @GetMapping
    public List<Produto> listarProdutos(){
        return produtoService.listarProdutos();
    }

    @GetMapping("/{id}")
    public Produto buscarPorID(@PathVariable Long id) {
        return produtoService.buscarPorID(id);
    }

    @PutMapping("/{id}")
    public Produto atualizarProduto(@RequestBody Produto produto, @PathVariable Long id) {
        return produtoService.atualizarProduto(produto, id);
    }

    @DeleteMapping("/{id}")
    public void excluirProduto(@PathVariable Long id) {
        produtoService.excluirProduto(id);
    }

}
