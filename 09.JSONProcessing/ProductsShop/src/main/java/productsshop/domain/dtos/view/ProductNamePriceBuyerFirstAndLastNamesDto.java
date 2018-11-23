package productsshop.domain.dtos.view;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class ProductNamePriceBuyerFirstAndLastNamesDto {

    @Expose
    private String name;

    @Expose
    private BigDecimal price;

    @Expose
    private String buyerFirstName;

    @Expose
    private String buyerLastName;
}
