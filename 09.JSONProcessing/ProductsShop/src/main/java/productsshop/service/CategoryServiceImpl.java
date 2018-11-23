package productsshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import productsshop.domain.dtos.view.CategoriesByProductsDto;
import productsshop.domain.dtos.seedDatabase.CategorySeedDto;
import productsshop.domain.entities.Category;
import productsshop.repository.CategoryRepository;
import productsshop.util.ValidatorUtil;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCategories(CategorySeedDto[] categorySeedDtos) {
        for(CategorySeedDto categorySeedDto : categorySeedDtos){
            if (!this.validatorUtil.isValid(categorySeedDto)) {
                this.validatorUtil.violations(categorySeedDto)
                        .forEach(violation -> System.out.println(violation.getMessage()));

                continue;
            }

            Category entity = this.modelMapper.map(categorySeedDto, Category.class);

            this.categoryRepository.saveAndFlush(entity);
        }
    }

    @Override
    public List<CategoriesByProductsDto> getCategoriesByProductsCount() {
       return this.categoryRepository.getCategoriesByProductsCount();

    }

}
