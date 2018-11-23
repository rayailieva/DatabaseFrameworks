package productsshop.domain.dtos.view;

import com.google.gson.annotations.Expose;
import org.springframework.beans.MutablePropertyValues;

public class UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto {

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private Integer age;

    @Expose
    private SoldProductsDto soldProducts;

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

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public SoldProductsDto getSoldProducts() {
        return this.soldProducts;
    }

    public void setSoldProducts(SoldProductsDto soldProducts) {
        this.soldProducts = soldProducts;
    }
}
