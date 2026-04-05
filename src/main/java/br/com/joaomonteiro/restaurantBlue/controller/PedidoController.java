package br.com.joaomonteiro.restaurantBlue.controller;

import br.com.joaomonteiro.restaurantBlue.model.Pedido;
import br.com.joaomonteiro.restaurantBlue.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor

public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public Pedido criarPedido(@RequestBody Pedido pedido){
        return pedidoService.criarPedido(pedido);
    }

    @GetMapping
    public List<Pedido> listarPedidos(){
        return pedidoService.listarPedidos();
    }

    @GetMapping("/{id}")
    public Pedido buscarPorId(@PathVariable Long id){
        return pedidoService.buscarPorID(id);
    }

    @PutMapping("/{id}")
    public Pedido atualizarPedido(@RequestBody Pedido pedido, @PathVariable Long id){
        return pedidoService.atualizarPedido(pedido, id);
    }

    @DeleteMapping("/{id}")
    public void excluirPedido(@PathVariable Long id){
        pedidoService.excluirPedido(id);
    }


}
