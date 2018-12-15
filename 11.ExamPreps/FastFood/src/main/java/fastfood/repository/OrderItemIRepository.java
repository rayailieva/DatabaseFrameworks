package fastfood.repository;

import fastfood.domain.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemIRepository extends JpaRepository<OrderItem, Integer> {
}
