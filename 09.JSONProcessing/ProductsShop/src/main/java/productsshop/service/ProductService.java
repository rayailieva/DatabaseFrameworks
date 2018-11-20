package productsshop.service;

import productsshop.domain.dtos.ProductSeedDto;

public interface ProductService {

    void seedProducts(ProductSeedDto[] productSeedDtos);
}
