package br.com.joaomonteiro.restaurantBlue.repository;

import br.com.joaomonteiro.restaurantBlue.auxiliar.StatusPagamento;
import br.com.joaomonteiro.restaurantBlue.auxiliar.StatusPedido;
import br.com.joaomonteiro.restaurantBlue.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository <Pedido, Long> {

    @Query("SELECT p FROM Pedido p WHERE p.status = :status")
    List<Pedido> buscarPorStatus(@Param("status") StatusPedido status);

    @Query("SELECT p FROM Pedido p WHERE p.statusPagamento = :statusPagamento")
    List<Pedido> buscarPorStatusPagamento(@Param("statusPagamento") StatusPagamento statusPagamento);
}
