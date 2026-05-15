package br.com.joaomonteiro.restaurantBlue.service;

import br.com.joaomonteiro.restaurantBlue.auxiliar.StatusMesa;
import br.com.joaomonteiro.restaurantBlue.dto.MesaDTO;
import br.com.joaomonteiro.restaurantBlue.exception.EntidadeNaoEncontradaException;
import br.com.joaomonteiro.restaurantBlue.model.Mesa;
import br.com.joaomonteiro.restaurantBlue.repository.MesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MesaService {

    public final MesaRepository repository;

    public MesaDTO criarMesa(MesaDTO mesaDTO){
        Mesa mesa = dtoToEntity(mesaDTO);
        Mesa mesaSalva = repository.save(mesa);
        return entityToDTO(mesaSalva);
    }

    public MesaDTO buscarPorID(Long id){
        Mesa mesa = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Mesa não encontrada"));
        return entityToDTO(mesa);
    }

    public List<MesaDTO> listarMesas() {
        return repository.findAll().stream()
                .map(this::entityToDTO)
                .toList();
    }

    public MesaDTO atualizarMesa(MesaDTO mesaDTO, Long id){
        Mesa mesaExistente = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Mesa não encontrada"));
        
        if(mesaDTO.getNumero() > 0) {
            mesaExistente.setNumero(mesaDTO.getNumero());
        }
        
        if(mesaDTO.getCapacidade() > 0) {
            mesaExistente.setCapacidade(mesaDTO.getCapacidade());
        }
        
        if(mesaDTO.getStatus() != null) {
            mesaExistente.setStatus(mesaDTO.getStatus());
        }
        
        Mesa mesaAtualizada = repository.save(mesaExistente);
        return entityToDTO(mesaAtualizada);
    }

    public void excluirMesa(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new EntidadeNaoEncontradaException("Mesa Não Registrada");
        } else {
            repository.deleteById(id);
        }
    }

    private MesaDTO entityToDTO(Mesa mesa) {
        MesaDTO dto = new MesaDTO();
        dto.setId(mesa.getId());
        dto.setNumero(mesa.getNumero());
        dto.setCapacidade(mesa.getCapacidade());
        dto.setStatus(mesa.getStatus());
        return dto;
    }

    private Mesa dtoToEntity(MesaDTO dto) {
        Mesa mesa = new Mesa();
        if (dto.getId() != null) {
            mesa.setId(dto.getId());
        }
        mesa.setNumero(dto.getNumero());
        mesa.setCapacidade(dto.getCapacidade());
        mesa.setStatus(dto.getStatus());
        return mesa;
    }
}
