package productsshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import productsshop.domain.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
