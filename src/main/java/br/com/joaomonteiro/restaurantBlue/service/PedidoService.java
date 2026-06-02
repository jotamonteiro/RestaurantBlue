package br.com.joaomonteiro.restaurantBlue.service;

import br.com.joaomonteiro.restaurantBlue.auxiliar.StatusPagamento;
import br.com.joaomonteiro.restaurantBlue.auxiliar.StatusPedido;
import br.com.joaomonteiro.restaurantBlue.dto.PedidoDTO;
import br.com.joaomonteiro.restaurantBlue.exception.EntidadeNaoEncontradaException;
import br.com.joaomonteiro.restaurantBlue.mapper.PedidoMapper;
import br.com.joaomonteiro.restaurantBlue.model.Cliente;
import br.com.joaomonteiro.restaurantBlue.model.Mesa;
import br.com.joaomonteiro.restaurantBlue.model.Pedido;
import br.com.joaomonteiro.restaurantBlue.repository.ClienteRepository;
import br.com.joaomonteiro.restaurantBlue.repository.MesaRepository;
import br.com.joaomonteiro.restaurantBlue.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PedidoService {

    private final PedidoRepository repository;
    private final MesaRepository mesaRepository;
    private final ClienteRepository clienteRepository;
    private final PedidoMapper mapper;

    public PedidoDTO criarPedido(PedidoDTO pedidoDTO) {
        Pedido pedido = mapper.toEntity(pedidoDTO);

        if (pedidoDTO.getMesaId() != null) {
            Mesa mesa = mesaRepository.findById(pedidoDTO.getMesaId())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Mesa Não Registrada"));
            pedido.setMesa(mesa);
        }

        if (pedidoDTO.getClienteId() != null) {
            Cliente cliente = clienteRepository.findById(pedidoDTO.getClienteId())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente Não Registrado"));
            pedido.setCliente(cliente);
        }

        Pedido pedidoSalvo = repository.save(pedido);
        log.info("Pedido criado: ID {}", pedidoSalvo.getId());
        return mapper.toDTO(pedidoSalvo);
    }

    public List<PedidoDTO> listarPedidos(){
        return mapper.toDTOList(repository.findAll());
    }

    public PedidoDTO buscarPorID(Long id) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pedido Não Registrado"));
        return mapper.toDTO(pedido);
    }

    public PedidoDTO atualizarPedido(PedidoDTO pedidoDTO, Long id) {
        Pedido pedidoExistente = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pedido Não Registrado"));

         if (pedidoDTO.getDescricao() != null) {
             pedidoExistente.setDescricao(pedidoDTO.getDescricao());
         }

         if (pedidoDTO.getValor() != null) {
             pedidoExistente.setValor(pedidoDTO.getValor());
         }

         if (pedidoDTO.getStatus() != null) {
             pedidoExistente.setStatus(pedidoDTO.getStatus());
         }

         if (pedidoDTO.getStatusPagamento() != null) {
             pedidoExistente.setStatusPagamento(pedidoDTO.getStatusPagamento());
         }

         if (pedidoDTO.getMesaId() != null) {
             Mesa mesa = mesaRepository.findById(pedidoDTO.getMesaId())
                     .orElseThrow(() -> new EntidadeNaoEncontradaException("Mesa Não Registrada"));
             pedidoExistente.setMesa(mesa);
         }

         if (pedidoDTO.getClienteId() != null) {
             Cliente cliente = clienteRepository.findById(pedidoDTO.getClienteId())
                     .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente Não Registrado"));
             pedidoExistente.setCliente(cliente);
         }

         Pedido pedidoAtualizado = repository.save(pedidoExistente);
         log.info("Pedido atualizado: ID {}", id);
         return mapper.toDTO(pedidoAtualizado);
     }

    public void excluirPedido(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new EntidadeNaoEncontradaException("Pedido Não Registrado");
        } else {
            repository.deleteById(id);
            log.info("Pedido excluído: ID {}", id);
        }
    }

    public List<PedidoDTO> buscarPorStatus(StatusPedido status) {
        return mapper.toDTOList(repository.buscarPorStatus(status));
    }

    public List<PedidoDTO> buscarPorStatusPagamento(StatusPagamento statusPagamento) {
        return mapper.toDTOList(repository.buscarPorStatusPagamento(statusPagamento));
    }


}
