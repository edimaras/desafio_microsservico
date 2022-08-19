package totalshakes.microsservico.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import totalshakes.microsservico.pedidos.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
