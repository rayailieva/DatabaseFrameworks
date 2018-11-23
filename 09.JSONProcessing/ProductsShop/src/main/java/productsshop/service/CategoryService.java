package productsshop.service;

import productsshop.domain.dtos.view.CategoriesByProductsDto;
import productsshop.domain.dtos.seedDatabase.CategorySeedDto;

import java.util.List;

public interface CategoryService {

    void seedCategories(CategorySeedDto[] categorySeedDtos);

    List<CategoriesByProductsDto> getCategoriesByProductsCount();
}
