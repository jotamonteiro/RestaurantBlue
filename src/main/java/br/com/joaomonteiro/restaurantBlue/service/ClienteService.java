package br.com.joaomonteiro.restaurantBlue.service;


import br.com.joaomonteiro.restaurantBlue.client.BrasilApiClient;
import br.com.joaomonteiro.restaurantBlue.dto.ClienteDTO;
import br.com.joaomonteiro.restaurantBlue.dto.BrasilApiCepResponseDTO;
import br.com.joaomonteiro.restaurantBlue.exception.EntidadeNaoEncontradaException;
import br.com.joaomonteiro.restaurantBlue.model.Cliente;
import br.com.joaomonteiro.restaurantBlue.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final BrasilApiClient brasilApiClient;

    public ClienteDTO criarCliente(ClienteDTO clienteDTO){
         Cliente cliente = dtoToEntity(clienteDTO);

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
         return entityToDTO(clienteSalvo);
     }

    public List<ClienteDTO> listarClientes(){
        return repository.findAll().stream()
                .map(this::entityToDTO)
                .toList();
    }

    public ClienteDTO buscarPorID(Long id){
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente Não Registrado"));
        return entityToDTO(cliente);
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
        return entityToDTO(clienteAtualizado);
    }

    public void excluirCliente(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new EntidadeNaoEncontradaException("Cliente Não Registrado");
        } else {
            repository.deleteById(id);
        }
    }

    public List<ClienteDTO> buscarPorNome(String nome) {
        return repository.buscarPorNome(nome).stream()
                .map(this::entityToDTO)
                .toList();
    }

    public List<ClienteDTO> buscarPorEmail(String email) {
        return repository.buscarPorEmail(email).stream()
                .map(this::entityToDTO)
                .toList();
    }

    private ClienteDTO entityToDTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setDatanasc(cliente.getDatanasc());
        dto.setCpf(cliente.getCpf());
        dto.setTelefone(cliente.getTelefone());
        dto.setEmail(cliente.getEmail());
        dto.setCep(cliente.getCep());
        dto.setNumero(cliente.getNumero());
        dto.setLogradouro(cliente.getLogradouro());
        dto.setBairro(cliente.getBairro());
        dto.setLocalidade(cliente.getLocalidade());
        dto.setUf(cliente.getUf());
        return dto;
    }

    private Cliente dtoToEntity(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        if (dto.getId() != null) {
            cliente.setId(dto.getId());
        }
        cliente.setNome(dto.getNome());
        cliente.setDatanasc(dto.getDatanasc());
        cliente.setCpf(dto.getCpf());
        cliente.setTelefone(dto.getTelefone());
        cliente.setEmail(dto.getEmail());
        cliente.setCep(dto.getCep());
        cliente.setNumero(dto.getNumero());
        cliente.setLogradouro(dto.getLogradouro());
        cliente.setBairro(dto.getBairro());
        cliente.setLocalidade(dto.getLocalidade());
        cliente.setUf(dto.getUf());
        return cliente;
    }

}
