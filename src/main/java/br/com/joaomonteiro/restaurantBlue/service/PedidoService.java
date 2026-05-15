package br.com.joaomonteiro.restaurantBlue.service;

import br.com.joaomonteiro.restaurantBlue.auxiliar.StatusPagamento;
import br.com.joaomonteiro.restaurantBlue.auxiliar.StatusPedido;
import br.com.joaomonteiro.restaurantBlue.dto.PedidoDTO;
import br.com.joaomonteiro.restaurantBlue.exception.EntidadeNaoEncontradaException;
import br.com.joaomonteiro.restaurantBlue.model.Cliente;
import br.com.joaomonteiro.restaurantBlue.model.Mesa;
import br.com.joaomonteiro.restaurantBlue.model.Pedido;
import br.com.joaomonteiro.restaurantBlue.repository.ClienteRepository;
import br.com.joaomonteiro.restaurantBlue.repository.MesaRepository;
import br.com.joaomonteiro.restaurantBlue.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository repository;
    private final MesaRepository mesaRepository;
    private final ClienteRepository clienteRepository;

    public PedidoDTO criarPedido(PedidoDTO pedidoDTO){
        Pedido pedido = dtoToEntity(pedidoDTO);
        Pedido pedidoSalvo = repository.save(pedido);
        return entityToDTO(pedidoSalvo);
    }

    public List<PedidoDTO> listarPedidos(){
        return repository.findAll().stream()
                .map(this::entityToDTO)
                .toList();
    }

    public PedidoDTO buscarPorID(Long id) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pedido Não Registrado"));
        return entityToDTO(pedido);
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
        return entityToDTO(pedidoAtualizado);
    }

    public void excluirPedido(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new EntidadeNaoEncontradaException("Pedido Não Registrado");
        } else {
            repository.deleteById(id);
        }
    }

    public List<PedidoDTO> buscarPorStatus(StatusPedido status) {
        return repository.buscarPorStatus(status).stream()
                .map(this::entityToDTO)
                .toList();
    }

    public List<PedidoDTO> buscarPorStatusPagamento(StatusPagamento statusPagamento) {
        return repository.buscarPorStatusPagamento(statusPagamento).stream()
                .map(this::entityToDTO)
                .toList();
    }

    private PedidoDTO entityToDTO(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();
        dto.setId(pedido.getId());
        dto.setDescricao(pedido.getDescricao());
        dto.setValor(pedido.getValor());
        dto.setStatus(pedido.getStatus());
        dto.setStatusPagamento(pedido.getStatusPagamento());
        if (pedido.getMesa() != null) {
            dto.setMesaId(pedido.getMesa().getId());
        }
        if (pedido.getCliente() != null) {
            dto.setClienteId(pedido.getCliente().getId());
        }
        return dto;
    }

    private Pedido dtoToEntity(PedidoDTO dto) {
        Pedido pedido = new Pedido();
        if (dto.getId() != null) {
            pedido.setId(dto.getId());
        }
        pedido.setDescricao(dto.getDescricao());
        pedido.setValor(dto.getValor());
        pedido.setStatus(dto.getStatus());
        pedido.setStatusPagamento(dto.getStatusPagamento());
        
        if (dto.getMesaId() != null) {
            Mesa mesa = mesaRepository.findById(dto.getMesaId())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Mesa Não Registrada"));
            pedido.setMesa(mesa);
        }
        
        if (dto.getClienteId() != null) {
            Cliente cliente = clienteRepository.findById(dto.getClienteId())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente Não Registrado"));
            pedido.setCliente(cliente);
        }
        
        return pedido;
    }

}
