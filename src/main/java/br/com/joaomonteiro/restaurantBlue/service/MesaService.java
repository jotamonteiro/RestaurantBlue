package br.com.joaomonteiro.restaurantBlue.service;

import br.com.joaomonteiro.restaurantBlue.model.Mesa;
import br.com.joaomonteiro.restaurantBlue.repository.MesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MesaService {

    public final MesaRepository repository;

    public Mesa criarMesa(Mesa mesa){
        return repository.save(mesa);
    }

    public Mesa buscarPorID(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));
    }

    public List<Mesa> listarMesas() {
        return repository.findAll();
    }

    public Mesa atualizarMesa(Mesa mesa, Long id){
        Mesa mesaExistente = buscarPorID(id);
        
        if(mesa.getNumero() > 0) {
            mesaExistente.setNumero(mesa.getNumero());
        }
        
        if(mesa.getCapacidade() > 0) {
            mesaExistente.setCapacidade(mesa.getCapacidade());
        }
        
        if(mesa.getStatus() != null && !mesa.getStatus().isEmpty()) {
            mesaExistente.setStatus(mesa.getStatus());
        }
        
        return repository.save(mesaExistente);
    }

    public void excluirMesa(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new RuntimeException("Mesa Não Registrada");
        } else {
            repository.deleteById(id);
        }
    }
}
