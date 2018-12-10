package alararestaurant.repository;

import alararestaurant.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query( "" +
            "SELECT o " +
            "FROM alararestaurant.domain.entities.Order o " +
            "JOIN o.employee AS e " +
            "JOIN e.position AS p " +
            "WHERE p.name = :positionName " +
            "ORDER BY e.name, o.id "
    )
    List<Order> findAllOrdersByBurgerFlippers(@Param(value = "positionName") String positionName);
}
