package br.com.joaomonteiro.restaurantBlue.service;

import br.com.joaomonteiro.restaurantBlue.model.Produto;
import br.com.joaomonteiro.restaurantBlue.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;

    public Produto criarProduto(Produto produto) {
        return repository.save(produto);
    }

    public List<Produto> listarProdutos() {
        return repository.findAll();
    }

    public Produto buscarPorID(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new RuntimeException("Produto Não Registrado");
        } else {
            return repository.findById(id).get();
        }
    }

    public Produto atualizarProduto(Produto produto, Long id) {
        Produto produtoExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto Não Registrado"));

        if (produto.getNome() != null) {
            produtoExistente.setNome(produto.getNome());
        }

        if (produto.getDescricao() != null) {
            produtoExistente.setDescricao(produto.getDescricao());
        }

        if (produto.getPreco() != null) {
            produtoExistente.setPreco(produto.getPreco());
        }

        if (produto.getCategoria() != null) {
            produtoExistente.setCategoria(produto.getCategoria());
        }

        if (produto.isDisponivel()) {
            produtoExistente.setDisponivel(true);
        } else {
            produtoExistente.setDisponivel(false);
        }

        return repository.save(produtoExistente);
    }

    public void excluirProduto(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new RuntimeException("Produto Não Registrado");
        } else {
            repository.deleteById(id);
        }
    }


}