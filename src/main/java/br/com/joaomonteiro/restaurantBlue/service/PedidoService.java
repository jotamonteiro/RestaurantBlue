package br.com.joaomonteiro.restaurantBlue.service;

import br.com.joaomonteiro.restaurantBlue.model.Pedido;
import br.com.joaomonteiro.restaurantBlue.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository repository;

    public Pedido criarPedido(Pedido pedido){
        return repository.save(pedido);
    }

    public List<Pedido> listarPedidos(){
        return repository.findAll();
    }

    public Pedido buscarPorID(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new RuntimeException("Pedido Não Registrado");
        } else {
            return repository.findById(id).get();
        }
    }

    public Pedido atualizarPedido(Pedido pedido, Long id) {
        Pedido pedidoExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido Não Registrado"));

        if (pedido.getDescricao() != null) {
            pedidoExistente.setDescricao(pedido.getDescricao());
        }

        if (pedido.getValor() != null) {
            pedidoExistente.setValor(pedido.getValor());
        }

        if (pedido.getStatus() != null) {
            pedidoExistente.setStatus(pedido.getStatus());
        }

        return repository.save(pedidoExistente);
    }

    public void excluirPedido(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new RuntimeException("Pedido Não Registrado");
        } else {
            repository.deleteById(id);
        }
    }

}
