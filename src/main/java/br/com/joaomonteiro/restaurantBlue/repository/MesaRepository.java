package br.com.joaomonteiro.restaurantBlue.repository;

import br.com.joaomonteiro.restaurantBlue.model.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {
}
