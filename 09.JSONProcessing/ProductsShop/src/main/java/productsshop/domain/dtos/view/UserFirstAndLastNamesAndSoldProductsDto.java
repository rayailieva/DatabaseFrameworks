package productsshop.domain.dtos.view;

import com.google.gson.annotations.Expose;

import java.util.LinkedHashSet;
import java.util.Set;

public class UserFirstAndLastNamesAndSoldProductsDto {

    @Expose
    private String firstName;

   @Expose
    private String lastName;

   @Expose
    private Set<ProductNamePriceBuyerFirstAndLastNamesDto> soldProducts;

    public UserFirstAndLastNamesAndSoldProductsDto() {
        this.soldProducts = new LinkedHashSet<>();
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<ProductNamePriceBuyerFirstAndLastNamesDto> getSoldProducts() {
        return this.soldProducts;
    }

    public void setSoldProducts(Set<ProductNamePriceBuyerFirstAndLastNamesDto> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
