package totalshakes.microsservico.pagamentos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import totalshakes.microsservico.pagamentos.model.Pagamento;
import totalshakes.microsservico.pagamentos.model.StatusPagamento;
import totalshakes.microsservico.pagamentos.repository.PagamentoRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private PedidoClient pedido;

    public Page<Pagamento> obterTodos(Pageable paginacao) {
        return  pagamentoRepository.findAll(paginacao);
    }

    public Pagamento obterPorId(Long id) {
        return pagamentoRepository.findById(id).get();
    }

    public void criarPagamento(Pagamento pagamento) {
        pagamento.setStatusPagamento(StatusPagamento.CRIADO);
        pagamentoRepository.save(pagamento);
    }

    public void atualizarPagamento(Long id, Pagamento pagamento) {
        pagamento.setId(id);
        pagamentoRepository.save(pagamento);
    }

    public void confirmarPagamento(Long id){
        Optional<Pagamento> pagamento = pagamentoRepository.findById(id);

        if (!pagamento.isPresent()) {
            throw new EntityNotFoundException();
        }

        pagamento.get().setStatusPagamento(StatusPagamento.CONFIRMADO);
        pagamentoRepository.save(pagamento.get());
        pedido.atualizaPagamento(pagamento.get().getPedidoId());
    }

    public void deletarPagamento(Long id) {
        pagamentoRepository.deleteById(id);
    }
}
