package br.com.joaomonteiro.restaurantBlue.service;


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

     public Cliente criarCliente(Cliente cliente){
         return repository.save(cliente);
     }

     public List<Cliente> listarClientes(){
         return repository.findAll();
     }

     public Cliente buscarPorID(Long id){
         if (repository.findById(id).isEmpty()){
             throw new RuntimeException("Cliente Não Registrado");
         } else {
             return repository.findById(id).get();
         }
     }

    public Cliente atualizarCliente(Cliente cliente, Long id) {
        Cliente clienteExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente Não Registrado"));

        if (cliente.getNome() != null) {
            clienteExistente.setNome(cliente.getNome());
        }

        if (cliente.getDatanasc() != null) {
            clienteExistente.setDatanasc(cliente.getDatanasc());
        }

        if (cliente.getCpf() != null) {
            clienteExistente.setCpf(cliente.getCpf());
        }

        if (cliente.getTelefone() != null) {
            clienteExistente.setTelefone(cliente.getTelefone());
        }

        if (cliente.getEmail() != null) {
            clienteExistente.setEmail(cliente.getEmail());
        }

        if (cliente.getEndereco() != null) {
            clienteExistente.setEndereco(cliente.getEndereco());
        }

        return repository.save(clienteExistente);
    }

     public void excluirCliente(Long id) {
         if (repository.findById(id).isEmpty()) {
             throw new RuntimeException("Cliente Não Registrado");
         } else {
             repository.deleteById(id);
         }
     }

}
