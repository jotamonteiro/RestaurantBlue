package br.com.joaomonteiro.restaurantBlue.controller;

import br.com.joaomonteiro.restaurantBlue.auxiliar.Categoria;
import br.com.joaomonteiro.restaurantBlue.dto.ProdutoDTO;
import br.com.joaomonteiro.restaurantBlue.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
@CrossOrigin

public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoDTO> criarProduto(@Valid @RequestBody ProdutoDTO produtoDTO){
        ProdutoDTO resultado = produtoService.criarProduto(produtoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
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
    public ResponseEntity<Void> excluirProduto(@PathVariable Long id) {
        produtoService.excluirProduto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categoria/{categoria}")
    public List<ProdutoDTO> buscarPorCategoria(@PathVariable Categoria categoria) {
        return produtoService.buscarPorCategoria(categoria);
    }

    @GetMapping("/disponivel/todos")
    public List<ProdutoDTO> buscarDisponibles() {
        return produtoService.buscarDisponiveis();
    }

    @GetMapping("/preco/menores-iguais")
    public List<ProdutoDTO> buscarPorPrecoMenorOuIgual(@RequestParam Double preco) {
        return produtoService.buscarPorPrecoMenorOuIgual(preco);
    }

}
