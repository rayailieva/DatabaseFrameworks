package productsshop.service;

import productsshop.domain.dtos.view.ProductInRangeDto;
import productsshop.domain.dtos.seedDatabase.ProductSeedDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    void seedProducts(ProductSeedDto[] productSeedDtos);

    List<ProductInRangeDto> productsInRange(BigDecimal more, BigDecimal less);
}
