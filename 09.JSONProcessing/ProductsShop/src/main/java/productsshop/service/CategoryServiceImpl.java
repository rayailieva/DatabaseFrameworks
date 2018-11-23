package productsshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import productsshop.domain.dtos.CategoryNameProductsCountAverageAndTotalPricesDto;
import productsshop.domain.dtos.CategorySeedDto;
import productsshop.domain.entities.Category;
import productsshop.domain.entities.Product;
import productsshop.repository.CategoryRepository;
import productsshop.util.ValidatorUtil;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<CategoryNameProductsCountAverageAndTotalPricesDto> getCategoriesByProductsCount() {
        return this.categoryRepository.getCategoriesByProductsCount();
    }

}
