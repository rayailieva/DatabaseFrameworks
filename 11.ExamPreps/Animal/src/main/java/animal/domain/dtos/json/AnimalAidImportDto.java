package animal.domain.dtos.json;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class AnimalAidImportDto {

    @Expose
    private String name;

    @Expose
    private BigDecimal price;

    public AnimalAidImportDto(){}

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
