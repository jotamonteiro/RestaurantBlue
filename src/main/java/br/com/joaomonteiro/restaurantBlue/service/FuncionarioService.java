package br.com.joaomonteiro.restaurantBlue.service;

import br.com.joaomonteiro.restaurantBlue.client.ViaCepClient;
import br.com.joaomonteiro.restaurantBlue.dto.FuncionarioDTO;
import br.com.joaomonteiro.restaurantBlue.dto.ViaCepResponseDTO;
import br.com.joaomonteiro.restaurantBlue.exception.EntidadeNaoEncontradaException;
import br.com.joaomonteiro.restaurantBlue.model.Funcionario;
import br.com.joaomonteiro.restaurantBlue.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository repository;
    private final ViaCepClient viaCepClient;

    public FuncionarioDTO criarFunc(FuncionarioDTO funcionarioDTO){
        Funcionario funcionario = dtoToEntity(funcionarioDTO);
        
        if (funcionario.getCep() != null && !funcionario.getCep().isBlank()) {
            String cepLimpo = funcionario.getCep().replaceAll("\\D", "");
            ViaCepResponseDTO endereco = viaCepClient.buscarPorCep(cepLimpo);

            funcionario.setCep(endereco.getCep());
            funcionario.setLogradouro(endereco.getLogradouro());
            funcionario.setBairro(endereco.getBairro());
            funcionario.setLocalidade(endereco.getLocalidade());
            funcionario.setUf(endereco.getUf());
            // Preserva o numero, pois não vem da API ViaCep
        }

        Funcionario funcionarioSalvo = repository.save(funcionario);
        return entityToDTO(funcionarioSalvo);
    }

    public List<FuncionarioDTO> listarFunc(){
        return repository.findAll().stream()
                .map(this::entityToDTO)
                .toList();
    }

    public FuncionarioDTO buscarPorID(Long id){
        Funcionario funcionario = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Funcionario Não Registrado"));
        return entityToDTO(funcionario);
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

        // Se o CEP foi alterado, busca as novas informações na API ViaCep
        if (funcionarioDTO.getCep() != null && !funcionarioDTO.getCep().isEmpty() && 
            !funcionarioDTO.getCep().equals(funcionarioExistente.getCep())) {
            String cepLimpo = funcionarioDTO.getCep().replaceAll("\\D", "");
            ViaCepResponseDTO endereco = viaCepClient.buscarPorCep(cepLimpo);

            funcionarioExistente.setCep(endereco.getCep());
            funcionarioExistente.setLogradouro(endereco.getLogradouro());
            funcionarioExistente.setBairro(endereco.getBairro());
            funcionarioExistente.setLocalidade(endereco.getLocalidade());
            funcionarioExistente.setUf(endereco.getUf());
        }

        // Atualiza número se informado
        if (funcionarioDTO.getNumero() != null) {
            funcionarioExistente.setNumero(funcionarioDTO.getNumero());
        }

        // Atualiza outros campos de endereço apenas se informados e CEP não foi alterado
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
        return entityToDTO(funcionarioAtualizado);
    }


    public void excluirFunc(Long id){
        if (repository.findById(id).isEmpty()){
            throw new EntidadeNaoEncontradaException("Funcionario Não Registrado");
        }else {
            repository.deleteById(id);
        }
    }

    public List<FuncionarioDTO> buscarPorCargo(br.com.joaomonteiro.restaurantBlue.auxiliar.Cargo cargo) {
        return repository.buscarPorCargo(cargo).stream()
                .map(this::entityToDTO)
                .toList();
    }

    private FuncionarioDTO entityToDTO(Funcionario funcionario) {
        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setId(funcionario.getId());
        dto.setNome(funcionario.getNome());
        dto.setDatanasc(funcionario.getDatanasc());
        dto.setCpf(funcionario.getCpf());
        dto.setCargo(funcionario.getCargo());
        dto.setDataAdm(funcionario.getDataAdm());
        dto.setCep(funcionario.getCep());
        dto.setNumero(funcionario.getNumero());
        dto.setLogradouro(funcionario.getLogradouro());
        dto.setBairro(funcionario.getBairro());
        dto.setLocalidade(funcionario.getLocalidade());
        dto.setUf(funcionario.getUf());
        return dto;
    }

    private Funcionario dtoToEntity(FuncionarioDTO dto) {
        Funcionario funcionario = new Funcionario();
        if (dto.getId() > 0) {
            funcionario.setId(dto.getId());
        }
        funcionario.setNome(dto.getNome());
        funcionario.setDatanasc(dto.getDatanasc());
        funcionario.setCpf(dto.getCpf());
        funcionario.setCargo(dto.getCargo());
        funcionario.setDataAdm(dto.getDataAdm());
        funcionario.setCep(dto.getCep());
        funcionario.setNumero(dto.getNumero());
        funcionario.setLogradouro(dto.getLogradouro());
        funcionario.setBairro(dto.getBairro());
        funcionario.setLocalidade(dto.getLocalidade());
        funcionario.setUf(dto.getUf());
        return funcionario;
    }

}
