package br.com.joaomonteiro.restaurantBlue.service;

import br.com.joaomonteiro.restaurantBlue.auxiliar.Categoria;
import br.com.joaomonteiro.restaurantBlue.dto.ProdutoDTO;
import br.com.joaomonteiro.restaurantBlue.exception.EntidadeNaoEncontradaException;
import br.com.joaomonteiro.restaurantBlue.mapper.ProdutoMapper;
import br.com.joaomonteiro.restaurantBlue.model.Produto;
import br.com.joaomonteiro.restaurantBlue.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProdutoService {

    private final ProdutoRepository repository;
    private final ProdutoMapper mapper;

    public ProdutoDTO criarProduto(ProdutoDTO produtoDTO) {
        Produto produto = mapper.toEntity(produtoDTO);
        Produto produtoSalvo = repository.save(produto);
        log.info("Produto criado: ID {}", produtoSalvo.getId());
        return mapper.toDTO(produtoSalvo);
    }

    public List<ProdutoDTO> listarProdutos() {
        return mapper.toDTOList(repository.findAll());
    }

    public ProdutoDTO buscarPorID(Long id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Produto Não Registrado"));
        return mapper.toDTO(produto);
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
         log.info("Produto atualizado: ID {}", id);
         return mapper.toDTO(produtoAtualizado);
     }

    public void excluirProduto(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new EntidadeNaoEncontradaException("Produto Não Registrado");
        } else {
            repository.deleteById(id);
            log.info("Produto excluído: ID {}", id);
        }
    }

    public List<ProdutoDTO> buscarPorCategoria(Categoria categoria) {
        return mapper.toDTOList(repository.buscarPorCategoria(categoria));
    }

    public List<ProdutoDTO> buscarDisponiveis() {
        return mapper.toDTOList(repository.buscarDisponiveis());
    }

    public List<ProdutoDTO> buscarPorPrecoMenorOuIgual(Double preco) {
        return mapper.toDTOList(repository.buscarPorPrecoMenorOuIgual(preco));
    }

}