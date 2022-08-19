package totalshakes.microsservico.pedidos.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import totalshakes.microsservico.pedidos.dto.ItemDto;
import totalshakes.microsservico.pedidos.model.Item;
import totalshakes.microsservico.pedidos.model.Pedido;
import totalshakes.microsservico.pedidos.model.StatusPedido;
import totalshakes.microsservico.pedidos.repository.ItemRepository;
import totalshakes.microsservico.pedidos.repository.PedidoRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public List<Pedido> obterTodos() {
        List<Pedido> pedidos = new ArrayList<Pedido>();
        pedidoRepository.findAll().forEach(pedido -> pedidos.add(pedido));
        return pedidos;
    }

    public Pedido obterPorId(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Não encontrado"));
    }

    public Pedido criarPedido() {
        Pedido pedido = new Pedido();
        pedido.setDataHora(LocalDateTime.now());
        pedido.setStatusPedido(StatusPedido.REALIZADO);
        pedidoRepository.save(pedido);
        return pedido;
    }

    public Item adicionarItemAoPedido(Long id, ItemDto itemDto){
        Pedido pedido = obterPorId(id);
        Item item = objectMapper.convertValue(itemDto, Item.class);
        item.setPedido(pedido);
        item.setPedidoId(pedido.getId());
        return itemRepository.save(item);
    }

    public void aprovaPagamentoPedido(Long id) {
        Pedido pedido = obterPorId(id);
        pedido.setStatusPedido(StatusPedido.PAGO);
        pedidoRepository.save(pedido);
    }

    public void deletarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }

}
