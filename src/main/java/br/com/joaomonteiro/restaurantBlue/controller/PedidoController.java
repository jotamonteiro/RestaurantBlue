package br.com.joaomonteiro.restaurantBlue.controller;

import br.com.joaomonteiro.restaurantBlue.auxiliar.StatusPagamento;
import br.com.joaomonteiro.restaurantBlue.auxiliar.StatusPedido;
import br.com.joaomonteiro.restaurantBlue.dto.PedidoDTO;
import br.com.joaomonteiro.restaurantBlue.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
@CrossOrigin

public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoDTO> criarPedido(@Valid @RequestBody PedidoDTO pedidoDTO){
        PedidoDTO resultado = pedidoService.criarPedido(pedidoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    @GetMapping
    public List<PedidoDTO> listarPedidos(){
        return pedidoService.listarPedidos();
    }

    @GetMapping("/{id}")
    public PedidoDTO buscarPorId(@PathVariable Long id){
        return pedidoService.buscarPorID(id);
    }

    @PutMapping("/{id}")
    public PedidoDTO atualizarPedido(@Valid @RequestBody PedidoDTO pedidoDTO, @PathVariable Long id){
        return pedidoService.atualizarPedido(pedidoDTO, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPedido(@PathVariable Long id){
        pedidoService.excluirPedido(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/pedido")
    public List<PedidoDTO> buscarPorStatus(@RequestParam StatusPedido status) {
        return pedidoService.buscarPorStatus(status);
    }

    @GetMapping("/status/pagamento")
    public List<PedidoDTO> buscarPorStatusPagamento(@RequestParam StatusPagamento statusPagamento) {
        return pedidoService.buscarPorStatusPagamento(statusPagamento);
    }


}
