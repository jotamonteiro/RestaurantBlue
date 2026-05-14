package br.com.joaomonteiro.restaurantBlue.controller;

import br.com.joaomonteiro.restaurantBlue.auxiliar.Categoria;
import br.com.joaomonteiro.restaurantBlue.dto.ProdutoDTO;
import br.com.joaomonteiro.restaurantBlue.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor

public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ProdutoDTO criarProduto(@Valid @RequestBody ProdutoDTO produtoDTO){
        return produtoService.criarProduto(produtoDTO);
    }

    @GetMapping
    public List<ProdutoDTO> listarProdutos(){
        return produtoService.listarProdutos();
    }

    @GetMapping("/{id}")
    public ProdutoDTO buscarPorID(@PathVariable Long id) {
        return produtoService.buscarPorID(id);
    }

    @PutMapping("/{id}")
    public ProdutoDTO atualizarProduto(@Valid @RequestBody ProdutoDTO produtoDTO, @PathVariable Long id) {
        return produtoService.atualizarProduto(produtoDTO, id);
    }

    @DeleteMapping("/{id}")
    public void excluirProduto(@PathVariable Long id) {
        produtoService.excluirProduto(id);
    }

    @GetMapping("/categoria/{categoria}")
    public List<ProdutoDTO> buscarPorCategoria(@PathVariable Categoria categoria) {
        return produtoService.buscarPorCategoria(categoria);
    }

    @GetMapping("/disponivel/todos")
    public List<ProdutoDTO> buscarDisponibles() {
        return produtoService.buscarDisponibles();
    }

    @GetMapping("/preco/menores-iguais")
    public List<ProdutoDTO> buscarPorPrecoMenorOuIgual(@RequestParam Double preco) {
        return produtoService.buscarPorPrecoMenorOuIgual(preco);
    }

}
