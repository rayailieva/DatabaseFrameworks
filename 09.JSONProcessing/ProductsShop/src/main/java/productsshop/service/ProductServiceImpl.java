package productsshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productsshop.domain.dtos.ProductSeedDto;
import productsshop.domain.entities.Product;
import productsshop.repository.ProductRepository;
import productsshop.util.ValidatorUtil;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedProducts(ProductSeedDto[] productSeedDtos) {
        for (ProductSeedDto productSeedDto : productSeedDtos) {
            if (!this.validatorUtil.isValid(productSeedDto)) {
                this.validatorUtil.violations(productSeedDto)
                        .forEach(violation -> System.out.println(violation.getMessage()));

                continue;
            }

            Product entity = this.modelMapper.map(productSeedDto, Product.class);

            this.productRepository.saveAndFlush(entity);
        }
    }
}
