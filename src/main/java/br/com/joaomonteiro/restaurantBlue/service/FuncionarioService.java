package br.com.joaomonteiro.restaurantBlue.service;

import br.com.joaomonteiro.restaurantBlue.client.ViaCepClient;
import br.com.joaomonteiro.restaurantBlue.dto.ViaCepResponseDTO;
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

    public Funcionario criarFunc(Funcionario funcionario){
        if (funcionario.getCep() != null && !funcionario.getCep().isBlank()) {
            String cepLimpo = funcionario.getCep().replaceAll("\\D", "");
            ViaCepResponseDTO endereco = viaCepClient.buscarPorCep(cepLimpo);

            funcionario.setCep(endereco.getCep());
            funcionario.setLogradouro(endereco.getLogradouro());
            funcionario.setBairro(endereco.getBairro());
            funcionario.setLocalidade(endereco.getLocalidade());
            funcionario.setUf(endereco.getUf());
        }

        return repository.save(funcionario);
    }

    public List<Funcionario> listarFunc(){
        return repository.findAll();
    }

    public Funcionario buscarPorID(Long id){
        if (repository.findById(id).isEmpty()){
            throw new RuntimeException("Funcionario Não Registrado");
        } else {
            return repository.findById(id).get();
        }
    }

    public Funcionario atualizarFunc(Funcionario funcionario, Long id) {
        Funcionario funcionarioExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionario Não Registrado"));

        if (funcionario.getNome() != null) {
            funcionarioExistente.setNome(funcionario.getNome());
        }

        if (funcionario.getDatanasc() != null) {
            funcionarioExistente.setDatanasc(funcionario.getDatanasc());
        }

        if (funcionario.getCpf() != null) {
            funcionarioExistente.setCpf(funcionario.getCpf());
        }

        if (funcionario.getCargo() != null) {
            funcionarioExistente.setCargo(funcionario.getCargo());
        }

        if (funcionario.getDataAdm() != null) {
            funcionarioExistente.setDataAdm(funcionario.getDataAdm());
        }

        return repository.save(funcionarioExistente);
    }


    public void excluirFunc(Long id){
        if (repository.findById(id).isEmpty()){
            throw new RuntimeException("Funcionario Não Registrado");
        }else {
            repository.deleteById(id);
        }
    }

}
