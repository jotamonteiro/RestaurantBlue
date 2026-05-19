package br.com.joaomonteiro.restaurantBlue.repository;

import br.com.joaomonteiro.restaurantBlue.auxiliar.Categoria;
import br.com.joaomonteiro.restaurantBlue.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository <Produto, Long> {

    @Query("SELECT p FROM Produto p WHERE p.categoria = :categoria")
    List<Produto> buscarPorCategoria(@Param("categoria") Categoria categoria);

    @Query("SELECT p FROM Produto p WHERE p.disponivel = true")
    List<Produto> buscarDisponibles();

    @Query("SELECT p FROM Produto p WHERE p.preco <= :preco")
    List<Produto> buscarPorPrecoMenorOuIgual(@Param("preco") Double preco);
}
