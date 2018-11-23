package productsshop.service;

import productsshop.domain.dtos.CategoryNameProductsCountAverageAndTotalPricesDto;
import productsshop.domain.dtos.CategorySeedDto;

import java.util.List;

public interface CategoryService {

    void seedCategories(CategorySeedDto[] categorySeedDtos);

    List<CategoryNameProductsCountAverageAndTotalPricesDto> getCategoriesByProductsCount();
}
