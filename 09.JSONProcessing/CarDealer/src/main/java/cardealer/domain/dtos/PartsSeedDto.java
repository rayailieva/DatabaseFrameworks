package cardealer.domain.dtos;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class PartsSeedDto {

    @Expose
    private String name;

    @Expose
    private BigDecimal price;

    @Expose
    private Double quantity;

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

    public Double getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
