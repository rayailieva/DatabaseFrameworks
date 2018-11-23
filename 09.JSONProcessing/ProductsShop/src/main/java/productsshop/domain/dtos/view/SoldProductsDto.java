package productsshop.domain.dtos.view;

import com.google.gson.annotations.Expose;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class SoldProductsDto {

    @Expose
    private Integer count;

    @Expose
    private Set<ProductNameAndPriceDto> soldProducts;

    public SoldProductsDto() {
        this.soldProducts = new LinkedHashSet<>();
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Set<ProductNameAndPriceDto> getSoldProducts() {
        return this.soldProducts;
    }

    public void setSoldProducts(Set<ProductNameAndPriceDto> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
