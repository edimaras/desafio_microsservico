package totalshakes.microsservico.pagamentos.dto;

import lombok.Data;
import totalshakes.microsservico.pagamentos.model.FormaDePagamento;
import totalshakes.microsservico.pagamentos.model.StatusPagamento;

import java.math.BigDecimal;

@Data
public class PagamentoDto {

    private Long id;
    private BigDecimal valor;
    private String nome;
    private String numero;
    private String expiracao;
    private String codigo;
    private StatusPagamento statusPagamento;
    private Long pedidoId;
    private FormaDePagamento formaDePagamento;
}
