package productshopxml.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import productshopxml.domain.dtos.binding.ProductSeedDto;
import productshopxml.domain.dtos.binding.ProductSeedRootDto;
import productshopxml.domain.dtos.view.ProductInRangeDto;
import productshopxml.domain.dtos.view.ProductInRangeRootDto;
import productshopxml.domain.entities.Product;
import productshopxml.domain.entities.User;
import productshopxml.repository.ProductRepository;
import productshopxml.repository.UserRepository;
import productshopxml.util.ValidatorUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.productRepository = productRepository;
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

            Product productEntity = this.modelMapper.map(productSeedDto, Product.class);

            productEntity.setSeller(this.getRandomSeller());
            productEntity.setBuyer(this.getRandomBuyer());

            this.productRepository.saveAndFlush(productEntity);
        }
    }

    @Override
    public ProductInRangeRootDto getAvailableProductsInPriceRange(BigDecimal low, BigDecimal high) {
        List<Product> productEntities = this.productRepository.getAllByPriceBetween(low, high);

        List<ProductInRangeDto> productsInRange = new ArrayList<>();
        for(Product product : productEntities){
            ProductInRangeDto productInRangeDto = this.modelMapper.map(product, ProductInRangeDto.class);
        }
        ProductInRangeRootDto productInRangeRootDto = new ProductInRangeRootDto();
        productInRangeRootDto.setProductsView(productsInRange);
        return productInRangeRootDto;
    }

    private User getRandomBuyer() {
        Random random = new Random();
        int randomIndex = random.nextInt((int) (this.userRepository.count() - 1)) + 1;

        return this.userRepository.findAll().get(randomIndex);
    }

    private User getRandomSeller() {
        Random random = new Random();
        int randomIndex = random.nextInt((int) (this.userRepository.count() - 1)) + 1;

        return this.userRepository.findAll().get(randomIndex);
    }
}
