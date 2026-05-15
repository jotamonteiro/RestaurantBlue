package br.com.joaomonteiro.restaurantBlue.service;

import br.com.joaomonteiro.restaurantBlue.dto.ProdutoDTO;
import br.com.joaomonteiro.restaurantBlue.exception.EntidadeNaoEncontradaException;
import br.com.joaomonteiro.restaurantBlue.model.Produto;
import br.com.joaomonteiro.restaurantBlue.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoDTO criarProduto(ProdutoDTO produtoDTO) {
        Produto produto = dtoToEntity(produtoDTO);
        Produto produtoSalvo = repository.save(produto);
        return entityToDTO(produtoSalvo);
    }

    public List<ProdutoDTO> listarProdutos() {
        return repository.findAll().stream()
                .map(this::entityToDTO)
                .toList();
    }

    public ProdutoDTO buscarPorID(Long id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Produto Não Registrado"));
        return entityToDTO(produto);
    }

    public ProdutoDTO atualizarProduto(ProdutoDTO produtoDTO, Long id) {
        Produto produtoExistente = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Produto Não Registrado"));

        if (produtoDTO.getNome() != null) {
            produtoExistente.setNome(produtoDTO.getNome());
        }

        if (produtoDTO.getDescricao() != null) {
            produtoExistente.setDescricao(produtoDTO.getDescricao());
        }

        if (produtoDTO.getPreco() != null) {
            produtoExistente.setPreco(produtoDTO.getPreco());
        }

        if (produtoDTO.getCategoria() != null) {
            produtoExistente.setCategoria(produtoDTO.getCategoria());
        }

        produtoExistente.setDisponivel(produtoDTO.isDisponivel());

        Produto produtoAtualizado = repository.save(produtoExistente);
        return entityToDTO(produtoAtualizado);
    }

    public void excluirProduto(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new EntidadeNaoEncontradaException("Produto Não Registrado");
        } else {
            repository.deleteById(id);
        }
    }

    public List<ProdutoDTO> buscarPorCategoria(br.com.joaomonteiro.restaurantBlue.auxiliar.Categoria categoria) {
        return repository.buscarPorCategoria(categoria).stream()
                .map(this::entityToDTO)
                .toList();
    }

    public List<ProdutoDTO> buscarDisponibles() {
        return repository.buscarDisponibles().stream()
                .map(this::entityToDTO)
                .toList();
    }

    public List<ProdutoDTO> buscarPorPrecoMenorOuIgual(Double preco) {
        return repository.buscarPorPrecoMenorOuIgual(preco).stream()
                .map(this::entityToDTO)
                .toList();
    }

    private ProdutoDTO entityToDTO(Produto produto) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setDescricao(produto.getDescricao());
        dto.setPreco(produto.getPreco());
        dto.setCategoria(produto.getCategoria());
        dto.setDisponivel(produto.isDisponivel());
        return dto;
    }

    private Produto dtoToEntity(ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setId(dto.getId());
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setCategoria(dto.getCategoria());
        produto.setDisponivel(dto.isDisponivel());
        return produto;
    }

}