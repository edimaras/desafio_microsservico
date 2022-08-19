package totalshakes.microsservico.pedidos.dto;

import lombok.Data;
import totalshakes.microsservico.pedidos.model.Pedido;

@Data
public class ItemDto {

    private Pedido pedido;
    private Integer quantidade;
    private String descricao;
}
