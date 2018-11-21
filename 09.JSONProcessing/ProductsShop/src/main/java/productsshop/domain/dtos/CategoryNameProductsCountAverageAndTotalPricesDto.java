package productsshop.domain.dtos;

import com.google.gson.annotations.SerializedName;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CategoryNameProductsCountAverageAndTotalPricesDto {

    @NotNull
    @Length(min = 3, max = 15)
    @SerializedName("category")
    private String name;

    private Integer productsCount;

    private Double averagePrice;

    private BigDecimal totalRevenue;
}
