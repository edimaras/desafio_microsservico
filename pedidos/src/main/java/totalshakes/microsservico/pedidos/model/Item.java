package totalshakes.microsservico.pedidos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity(name="item")
@Data
@Table(name = "item_do_pedido")
public class Item {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Positive
    private Integer quantidade;

    private String descricao;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @Column(name="pedido_id", updatable = false, insertable = false)
    private Long pedidoId;

}
