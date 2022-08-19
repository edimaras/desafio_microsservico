package totalshakes.microsservico.pedidos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import totalshakes.microsservico.pedidos.dto.ItemDto;
import totalshakes.microsservico.pedidos.model.Item;
import totalshakes.microsservico.pedidos.model.Pedido;
import totalshakes.microsservico.pedidos.service.PedidoService;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/todos")
    public List<Pedido> obterTodos() {
        return pedidoService.obterTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obterPorId(@PathVariable @NotNull Long id) {
        Pedido pedido = pedidoService.obterPorId(id);

        return ResponseEntity.ok(pedido);
    }

    @PostMapping("/realizar-pedido")
    public ResponseEntity<Pedido> criarPedido() {
        Pedido pedido = pedidoService.criarPedido();
        return new ResponseEntity<>(pedido, HttpStatus.CREATED);
    }

    @PostMapping("/adicionar-item")
    public ResponseEntity<Item> adicionarItem(@RequestParam Long idPedido, @RequestBody ItemDto itemDto) {
        Item item = pedidoService.adicionarItemAoPedido(idPedido, itemDto);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/pago")
    public ResponseEntity<Void> aprovaPagamento(@PathVariable @NotNull Long id) {
        pedidoService.aprovaPagamentoPedido(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Pedido> deletarPedido(@PathVariable @NotNull Long id) {
        pedidoService.deletarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
