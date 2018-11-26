package productshopxml.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import productshopxml.domain.dtos.binding.ProductSeedDto;
import productshopxml.domain.dtos.binding.ProductSeedRootDto;
import productshopxml.domain.dtos.view.ProductInRangeDto;
import productshopxml.domain.dtos.view.ProductInRangeRootDto;
import productshopxml.domain.entities.Category;
import productshopxml.domain.entities.Product;
import productshopxml.domain.entities.User;
import productshopxml.repository.CategoryRepository;
import productshopxml.repository.ProductRepository;
import productshopxml.repository.UserRepository;
import productshopxml.util.ValidatorUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ProductServiceImpl implements ProductService {
    
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, UserRepository userRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedProducts(ProductSeedRootDto productSeedRootDto) {
        for(ProductSeedDto productSeedDto : productSeedRootDto.getProductSeedDtos()){
            if(!this.validatorUtil.isValid(productSeedDto)){
                System.out.println("Something is wrong.");
                continue;
            }

            Product entity = this.modelMapper.map(productSeedDto, Product.class);
            entity.setSeller(this.getRandomUser());

            Random random = new Random();

            if (random.nextInt() % 13 != 0) {
                entity.setBuyer(this.getRandomUser());
            }

            entity.setCategories(this.getRandomCategories());

            this.productRepository.saveAndFlush(entity);
        }
    }

    @Override
    public ProductInRangeRootDto getAvailableProductsInPriceRange(BigDecimal low, BigDecimal high) {
        List<Product> productEntities = this.productRepository.getAllByPriceBetween(low, high);

        List<ProductInRangeDto> productsInRange = new ArrayList<>();
        for(Product product : productEntities){
            ProductInRangeDto productInRangeDto = this.modelMapper.map(product, ProductInRangeDto.class);
            productsInRange.add(productInRangeDto);
        }
        ProductInRangeRootDto productInRangeRootDto = new ProductInRangeRootDto();
        productInRangeRootDto.setProductsView(productsInRange);
        return productInRangeRootDto;
    }

    private User getRandomUser() {
        Random random = new Random();

        return this.userRepository.getOne(random.nextInt((int)this.userRepository.count() - 1) + 1);
    }

    private List<Category> getRandomCategories() {
        Random random = new Random();

        List<Category> categories = new ArrayList<>();

        int categoriesCount = random.nextInt((int)this.categoryRepository.count() - 1) + 1;

        for (int i = 0; i < categoriesCount; i++) {
            categories.add(this.categoryRepository.getOne(random.nextInt((int)this.categoryRepository.count() - 1) + 1));
        }

        return categories;
    }
}
