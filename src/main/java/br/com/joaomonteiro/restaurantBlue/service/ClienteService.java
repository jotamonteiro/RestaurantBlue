package br.com.joaomonteiro.restaurantBlue.service;


import br.com.joaomonteiro.restaurantBlue.dto.ClienteDTO;
import br.com.joaomonteiro.restaurantBlue.exception.EntidadeNaoEncontradaException;
import br.com.joaomonteiro.restaurantBlue.model.Cliente;
import br.com.joaomonteiro.restaurantBlue.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteDTO criarCliente(ClienteDTO clienteDTO){
        Cliente cliente = dtoToEntity(clienteDTO);
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

        if (clienteDTO.getEndereco() != null) {
            clienteExistente.setEndereco(clienteDTO.getEndereco());
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
        dto.setEndereco(cliente.getEndereco());
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
        cliente.setEndereco(dto.getEndereco());
        return cliente;
    }

}
