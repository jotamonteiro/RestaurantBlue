package br.com.joaomonteiro.restaurantBlue.service;

import br.com.joaomonteiro.restaurantBlue.dto.MesaDTO;
import br.com.joaomonteiro.restaurantBlue.exception.EntidadeNaoEncontradaException;
import br.com.joaomonteiro.restaurantBlue.mapper.MesaMapper;
import br.com.joaomonteiro.restaurantBlue.model.Mesa;
import br.com.joaomonteiro.restaurantBlue.repository.MesaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MesaService {

    private final MesaRepository repository;
    private final MesaMapper mapper;

    public MesaDTO criarMesa(MesaDTO mesaDTO){
        Mesa mesa = mapper.toEntity(mesaDTO);
        Mesa mesaSalva = repository.save(mesa);
        log.info("Mesa criada: ID {}", mesaSalva.getId());
        return mapper.toDTO(mesaSalva);
    }

    public MesaDTO buscarPorID(Long id){
        Mesa mesa = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Mesa não encontrada"));
        return mapper.toDTO(mesa);
    }

    public List<MesaDTO> listarMesas() {
        return mapper.toDTOList(repository.findAll());
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
        log.info("Mesa atualizada: ID {}", id);
        return mapper.toDTO(mesaAtualizada);
    }

    public void excluirMesa(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new EntidadeNaoEncontradaException("Mesa Não Registrada");
        } else {
            repository.deleteById(id);
            log.info("Mesa excluída: ID {}", id);
        }
    }

}
