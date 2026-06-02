package br.com.joaomonteiro.restaurantBlue.service;

import br.com.joaomonteiro.restaurantBlue.client.BrasilApiClient;
import br.com.joaomonteiro.restaurantBlue.dto.BrasilApiCepResponseDTO;
import br.com.joaomonteiro.restaurantBlue.dto.FuncionarioDTO;
import br.com.joaomonteiro.restaurantBlue.exception.EntidadeNaoEncontradaException;
import br.com.joaomonteiro.restaurantBlue.mapper.FuncionarioMapper;
import br.com.joaomonteiro.restaurantBlue.model.Funcionario;
import br.com.joaomonteiro.restaurantBlue.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FuncionarioService {

    private final FuncionarioRepository repository;
    private final BrasilApiClient brasilapiclient;
    private final FuncionarioMapper mapper;

    public FuncionarioDTO criarFunc(FuncionarioDTO funcionarioDTO){
        Funcionario funcionario = mapper.toEntity(funcionarioDTO);
        
        if (funcionario.getCep() != null && !funcionario.getCep().isBlank()) {
            String cepLimpo = funcionario.getCep().replaceAll("\\D", "");
            BrasilApiCepResponseDTO endereco = brasilapiclient.buscarPorCep(cepLimpo);

            funcionario.setCep(endereco.getCep());
            funcionario.setLogradouro(endereco.getLogradouro());
            funcionario.setBairro(endereco.getBairro());
            funcionario.setLocalidade(endereco.getLocalidade());
            funcionario.setUf(endereco.getUf());
        }

        Funcionario funcionarioSalvo = repository.save(funcionario);
        log.info("Funcionário criado: ID {}", funcionarioSalvo.getId());
        return mapper.toDTO(funcionarioSalvo);
    }

    public List<FuncionarioDTO> listarFunc(){
        return mapper.toDTOList(repository.findAll());
    }

    public FuncionarioDTO buscarPorID(Long id){
        Funcionario funcionario = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Funcionario Não Registrado"));
        return mapper.toDTO(funcionario);
    }

    public FuncionarioDTO atualizarFunc(FuncionarioDTO funcionarioDTO, Long id) {
        Funcionario funcionarioExistente = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Funcionario Não Registrado"));

         if (funcionarioDTO.getNome() != null) {
             funcionarioExistente.setNome(funcionarioDTO.getNome());
         }

         if (funcionarioDTO.getDatanasc() != null) {
             funcionarioExistente.setDatanasc(funcionarioDTO.getDatanasc());
         }

         if (funcionarioDTO.getCpf() != null) {
             funcionarioExistente.setCpf(funcionarioDTO.getCpf());
         }

         if (funcionarioDTO.getCargo() != null) {
             funcionarioExistente.setCargo(funcionarioDTO.getCargo());
         }

         if (funcionarioDTO.getDataAdm() != null) {
             funcionarioExistente.setDataAdm(funcionarioDTO.getDataAdm());
         }

         if (funcionarioDTO.getCep() != null && !funcionarioDTO.getCep().isEmpty() && 
             !funcionarioDTO.getCep().equals(funcionarioExistente.getCep())) {
             String cepLimpo = funcionarioDTO.getCep().replaceAll("\\D", "");
             BrasilApiCepResponseDTO endereco = brasilapiclient.buscarPorCep(cepLimpo);

             funcionarioExistente.setCep(endereco.getCep());
             funcionarioExistente.setLogradouro(endereco.getLogradouro());
             funcionarioExistente.setBairro(endereco.getBairro());
             funcionarioExistente.setLocalidade(endereco.getLocalidade());
             funcionarioExistente.setUf(endereco.getUf());
         }

         if (funcionarioDTO.getNumero() != null) {
             funcionarioExistente.setNumero(funcionarioDTO.getNumero());
         }

         if (funcionarioDTO.getCep() == null || funcionarioDTO.getCep().isEmpty() || 
             funcionarioDTO.getCep().equals(funcionarioExistente.getCep())) {
             
             if (funcionarioDTO.getLogradouro() != null) {
                 funcionarioExistente.setLogradouro(funcionarioDTO.getLogradouro());
             }

             if (funcionarioDTO.getBairro() != null) {
                 funcionarioExistente.setBairro(funcionarioDTO.getBairro());
             }

             if (funcionarioDTO.getLocalidade() != null) {
                 funcionarioExistente.setLocalidade(funcionarioDTO.getLocalidade());
             }

             if (funcionarioDTO.getUf() != null) {
                 funcionarioExistente.setUf(funcionarioDTO.getUf());
             }
         }

         Funcionario funcionarioAtualizado = repository.save(funcionarioExistente);
         log.info("Funcionário atualizado: ID {}", id);
         return mapper.toDTO(funcionarioAtualizado);
     }

    public void excluirFunc(Long id){
        if (repository.findById(id).isEmpty()){
            throw new EntidadeNaoEncontradaException("Funcionario Não Registrado");
        }else {
            repository.deleteById(id);
            log.info("Funcionário excluído: ID {}", id);
        }
    }

    public List<FuncionarioDTO> buscarPorCargo(br.com.joaomonteiro.restaurantBlue.auxiliar.Cargo cargo) {
        return mapper.toDTOList(repository.buscarPorCargo(cargo));
    }

}
