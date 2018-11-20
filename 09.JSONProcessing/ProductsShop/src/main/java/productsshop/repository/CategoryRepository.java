package productsshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import productsshop.domain.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    

}
