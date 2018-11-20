package productsshop.service;

import productsshop.domain.dtos.CategorySeedDto;

public interface CategoryService {

    void seedCategories(CategorySeedDto[] categorySeedDtos);
}
