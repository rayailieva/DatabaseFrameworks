package productshopxml.service;

import productshopxml.domain.dtos.binding.CategorySeedRootDto;
import productshopxml.domain.dtos.view.CategoriesByProductsDto;

import java.util.List;

public interface CategoryService {

    void seedCategories(CategorySeedRootDto categorySeedRootDto);


}
