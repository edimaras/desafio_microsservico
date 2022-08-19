package totalshakes.microsservico.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import totalshakes.microsservico.pedidos.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
