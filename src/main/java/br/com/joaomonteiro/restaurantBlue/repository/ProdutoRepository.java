package br.com.joaomonteiro.restaurantBlue.repository;

import br.com.joaomonteiro.restaurantBlue.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository <Produto, Long> {
}
