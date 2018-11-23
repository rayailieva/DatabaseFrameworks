package productsshop.domain.dtos.view;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class ProductNameAndPriceDto {

    @Expose
    private String name;

    @Expose
    private BigDecimal price;
}
