package productsshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import productsshop.domain.dtos.CategoryNameProductsCountAverageAndTotalPricesDto;
import productsshop.domain.entities.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("SELECT new productsshop.domain.dtos.CategoryNameProductsCountAverageAndTotalPricesDto(" +
            "c.name, c.products.size, AVG(p.price), SUM(p.price)) " +
            "FROM productsshop.domain.entities.Category AS c " +
            "JOIN c.products AS p " +
            "GROUP BY c.id " +
            "ORDER BY c.products.size DESC")
    List<CategoryNameProductsCountAverageAndTotalPricesDto> getCategoriesByProductsCount();
}
