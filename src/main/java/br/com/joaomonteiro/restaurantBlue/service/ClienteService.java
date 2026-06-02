package br.com.joaomonteiro.restaurantBlue.service;


import br.com.joaomonteiro.restaurantBlue.client.BrasilApiClient;
import br.com.joaomonteiro.restaurantBlue.dto.ClienteDTO;
import br.com.joaomonteiro.restaurantBlue.dto.BrasilApiCepResponseDTO;
import br.com.joaomonteiro.restaurantBlue.exception.EntidadeNaoEncontradaException;
import br.com.joaomonteiro.restaurantBlue.mapper.ClienteMapper;
import br.com.joaomonteiro.restaurantBlue.model.Cliente;
import br.com.joaomonteiro.restaurantBlue.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClienteService {

    private final ClienteRepository repository;
    private final BrasilApiClient brasilApiClient;
    private final ClienteMapper mapper;

    public ClienteDTO criarCliente(ClienteDTO clienteDTO){
         Cliente cliente = mapper.toEntity(clienteDTO);

         if (cliente.getCep() != null && !cliente.getCep().isBlank()) {
             String cepLimpo = cliente.getCep().replaceAll("\\D", "");
             BrasilApiCepResponseDTO endereco = brasilApiClient.buscarPorCep(cepLimpo);

             cliente.setCep(endereco.getCep());
             cliente.setLogradouro(endereco.getLogradouro());
             cliente.setBairro(endereco.getBairro());
             cliente.setLocalidade(endereco.getLocalidade());
             cliente.setUf(endereco.getUf());
             // Preserva o numero, pois não vem da API Brasil API
         }

         Cliente clienteSalvo = repository.save(cliente);
         log.info("Cliente criado: ID {}", clienteSalvo.getId());
         return mapper.toDTO(clienteSalvo);
      }

    public List<ClienteDTO> listarClientes(){
        return mapper.toDTOList(repository.findAll());
    }

    public ClienteDTO buscarPorID(Long id){
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente Não Registrado"));
        return mapper.toDTO(cliente);
    }

    public ClienteDTO atualizarCliente(ClienteDTO clienteDTO, Long id) {
         Cliente clienteExistente = repository.findById(id)
                 .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente Não Registrado"));

         if (clienteDTO.getNome() != null) {
             clienteExistente.setNome(clienteDTO.getNome());
         }

         if (clienteDTO.getDatanasc() != null) {
             clienteExistente.setDatanasc(clienteDTO.getDatanasc());
         }

         if (clienteDTO.getCpf() != null) {
             clienteExistente.setCpf(clienteDTO.getCpf());
         }

         if (clienteDTO.getTelefone() != null) {
             clienteExistente.setTelefone(clienteDTO.getTelefone());
         }

         if (clienteDTO.getEmail() != null) {
             clienteExistente.setEmail(clienteDTO.getEmail());
         }

          // Se o CEP foi alterado, busca as novas informações na API Brasil API
          if (clienteDTO.getCep() != null && !clienteDTO.getCep().isEmpty() &&
              !clienteDTO.getCep().equals(clienteExistente.getCep())) {
              String cepLimpo = clienteDTO.getCep().replaceAll("\\D", "");
              BrasilApiCepResponseDTO endereco = brasilApiClient.buscarPorCep(cepLimpo);

              clienteExistente.setCep(endereco.getCep());
              clienteExistente.setLogradouro(endereco.getLogradouro());
              clienteExistente.setBairro(endereco.getBairro());
              clienteExistente.setLocalidade(endereco.getLocalidade());
              clienteExistente.setUf(endereco.getUf());
          }

         // Atualiza número se informado
         if (clienteDTO.getNumero() != null) {
             clienteExistente.setNumero(clienteDTO.getNumero());
         }

         // Atualiza outros campos de endereço apenas se informados e CEP não foi alterado
         if (clienteDTO.getCep() == null || clienteDTO.getCep().isEmpty() ||
             clienteDTO.getCep().equals(clienteExistente.getCep())) {

             if (clienteDTO.getLogradouro() != null) {
                 clienteExistente.setLogradouro(clienteDTO.getLogradouro());
             }

             if (clienteDTO.getBairro() != null) {
                 clienteExistente.setBairro(clienteDTO.getBairro());
             }

             if (clienteDTO.getLocalidade() != null) {
                 clienteExistente.setLocalidade(clienteDTO.getLocalidade());
             }

             if (clienteDTO.getUf() != null) {
                 clienteExistente.setUf(clienteDTO.getUf());
             }
         }

         Cliente clienteAtualizado = repository.save(clienteExistente);
         log.info("Cliente atualizado: ID {}", id);
         return mapper.toDTO(clienteAtualizado);
     }

    public void excluirCliente(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new EntidadeNaoEncontradaException("Cliente Não Registrado");
        } else {
            repository.deleteById(id);
            log.info("Cliente excluído: ID {}", id);
        }
    }

    public List<ClienteDTO> buscarPorNome(String nome) {
        return mapper.toDTOList(repository.buscarPorNome(nome));
    }

    public List<ClienteDTO> buscarPorEmail(String email) {
        return mapper.toDTOList(repository.buscarPorEmail(email));
    }

}
