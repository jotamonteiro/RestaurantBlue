package br.com.joaomonteiro.restaurantBlue.repository;

import br.com.joaomonteiro.restaurantBlue.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository <Cliente, Long> {

    @Query("select c from Cliente c ")
    public List<Cliente> listarClienteOrdenado();

    @Query("SELECT c FROM Cliente c WHERE UPPER(c.nome) LIKE UPPER(CONCAT('%', :nome, '%'))")
    List<Cliente> buscarPorNome(@Param("nome") String nome);

    @Query("SELECT c FROM Cliente c WHERE UPPER(c.email) LIKE UPPER(CONCAT('%', :email, '%'))")
    List<Cliente> buscarPorEmail(@Param("email") String email);
}
