package br.com.joaomonteiro.restaurantBlue.repository;

import br.com.joaomonteiro.restaurantBlue.auxiliar.Cargo;
import br.com.joaomonteiro.restaurantBlue.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    @Query("SELECT f FROM Funcionario f WHERE f.cargo = :cargo")
    List<Funcionario> buscarPorCargo(@Param("cargo") Cargo cargo);
}
