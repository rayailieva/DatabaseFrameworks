package productshopxml.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productshopxml.domain.dtos.binding.CategorySeedDto;
import productshopxml.domain.dtos.binding.CategorySeedRootDto;
import productshopxml.domain.dtos.view.CategoriesByProductsDto;
import productshopxml.domain.entities.Category;
import productshopxml.repository.CategoryRepository;
import productshopxml.util.ValidatorUtil;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCategories(CategorySeedRootDto categorySeedRootDto) {
        for(CategorySeedDto categorySeedDto : categorySeedRootDto.getCategorySeedDtos()){
            if(!this.validatorUtil.isValid(categorySeedDto)){
                System.out.println("Something is wrong");
                continue;
            }

            Category categoryEntity = this.modelMapper.map(categorySeedDto, Category.class);
            this.categoryRepository.saveAndFlush(categoryEntity);
        }
    }


}
