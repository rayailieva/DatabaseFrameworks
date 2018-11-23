package productsshop.domain.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserFirstAndLastNamesAndSoldProductsDto implements Serializable {

    private String firstName;

    @NotNull
    @Length(min = 3)
    private String lastName;


    private List<ProductNamePriceBuyerFirstAndLastNamesDto> soldProducts;

    public UserFirstAndLastNamesAndSoldProductsDto(){
        this.soldProducts = new ArrayList<>();
    }

    public List<ProductNamePriceBuyerFirstAndLastNamesDto> getSoldProducts() {
        return this.soldProducts;
    }

    public void setSoldProducts(List<ProductNamePriceBuyerFirstAndLastNamesDto> soldProducts) {
        this.soldProducts = soldProducts;
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
}
