package productshopxml.service;

import productshopxml.domain.dtos.binding.ProductSeedRootDto;
import productshopxml.domain.dtos.view.ProductInRangeRootDto;

import java.math.BigDecimal;

public interface ProductService {

    void seedProducts(ProductSeedRootDto productSeedRootDto);

    ProductInRangeRootDto getAvailableProductsInPriceRange(BigDecimal low, BigDecimal high);
}
