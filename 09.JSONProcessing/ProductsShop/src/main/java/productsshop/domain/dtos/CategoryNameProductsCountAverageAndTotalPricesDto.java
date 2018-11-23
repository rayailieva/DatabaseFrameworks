package productsshop.domain.dtos;

import com.google.gson.annotations.SerializedName;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class CategoryNameProductsCountAverageAndTotalPricesDto implements Serializable {

    @NotNull
    @Length(min = 3, max = 15)
    @SerializedName("category")
    private String name;

    private Integer productsCount;

    private Double averagePrice;

    private BigDecimal totalRevenue;

    public CategoryNameProductsCountAverageAndTotalPricesDto() {
    }

    public CategoryNameProductsCountAverageAndTotalPricesDto(@NotNull String name, Integer productsCount, Double averagePrice, BigDecimal totalRevenue) {
        this.name = name;
        this.productsCount = productsCount;
        this.averagePrice = averagePrice;
        this.totalRevenue = totalRevenue;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProductsCount() {
        return this.productsCount;
    }

    public void setProductsCount(Integer productsCount) {
        this.productsCount = productsCount;
    }

    public Double getAveragePrice() {
        return this.averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public BigDecimal getTotalRevenue() {
        return this.totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
