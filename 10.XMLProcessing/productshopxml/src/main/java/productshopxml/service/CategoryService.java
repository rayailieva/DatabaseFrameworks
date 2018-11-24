package productshopxml.service;

import productshopxml.domain.dtos.binding.CategorySeedRootDto;

public interface CategoryService {

    void seedCategories(CategorySeedRootDto categorySeedRootDto);
}
