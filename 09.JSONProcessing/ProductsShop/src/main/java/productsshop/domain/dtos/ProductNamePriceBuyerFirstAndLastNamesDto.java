package productsshop.domain.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductNamePriceBuyerFirstAndLastNamesDto {

    @NotNull
    @Length(min = 3)
    private String name;

    @NotNull
    private BigDecimal price;

    private String buyerFirstName;

    @NotNull
    @Length(min = 3)
    private String buyerLastName;
}
