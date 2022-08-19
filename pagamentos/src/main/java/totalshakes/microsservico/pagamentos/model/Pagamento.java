package totalshakes.microsservico.pagamentos.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Data
@Table(name= "pagamentos")
public class Pagamento {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull(message="Valor não deve ser nulo")
    @Positive(message = "O valor deve ser um número positivo")
    private BigDecimal valor;

    @NotBlank(message = "O nome não pode ser branco.")
    @Size(max = 100,message = "O nome precisa conter no máximo 100 caracteres")
    @Column(length = 100)
    private String nome;

    @NotBlank(message="Número não deve estar em branco")
    @Size(max = 100,message = "O número deve conter no máximo 100 caracteres")
    @Column(length = 100)
    private String numero;

    private String expiracao;

    @NotBlank(message = "O código não deve estar em branco.")
    @Size(min = 3,max = 3,message = "O código precisa conter no minímo 3 e no máximo 3 caracteres")
    @Column(length = 3)
    private String codigo;

    @NotNull(message="O Status não deve ser nulo")
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPagamento statusPagamento;

    @Column(name = "pedido_id")
    private Long pedidoId;

    @Column(name = "forma_de_pagamento_id")
    private FormaDePagamento formaDePagamento;

}
