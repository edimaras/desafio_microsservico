package totalshakes.microsservico.pagamentos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import totalshakes.microsservico.pagamentos.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    Page<Pagamento> findAll(Pageable paginacao);
}
