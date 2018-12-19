package animal.domain.dtos;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

public class AnimalAidImportDto {

    @Expose
    private String name;
    @Expose
    private BigDecimal price;

    public AnimalAidImportDto() {
    }

    @Length(min = 3)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DecimalMin("1")
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
