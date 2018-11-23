package cardealer.domain.dtos.view;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class CustomerPurchasesViewDto {

    @Expose
    private String name;

    @Expose
    private Integer boughtCars;

    @Expose
    private BigDecimal spentMoney;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBoughtCars() {
        return this.boughtCars;
    }

    public void setBoughtCars(Integer boughtCars) {
        this.boughtCars = boughtCars;
    }

    public BigDecimal getSpentMoney() {
        return this.spentMoney;
    }

    public void setSpentMoney(BigDecimal spentMoney) {
        this.spentMoney = spentMoney;
    }
}
