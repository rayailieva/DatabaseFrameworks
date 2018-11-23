package cardealer.domain.dtos.view;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class SaleDetailsViewDto {

    @Expose
    private CarViewShortDto car;

    @Expose
    private String customerName;

    @Expose
    private Double discount;

    @Expose
    private BigDecimal price;

    @Expose
    private BigDecimal priceWithDiscount;

    public SaleDetailsViewDto(){
    }

    public CarViewShortDto getCar() {
        return this.car;
    }

    public void setCar(CarViewShortDto car) {
        this.car = car;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getDiscount() {
        return this.discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceWithDiscount() {
        return this.priceWithDiscount;
    }

    public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }
}
