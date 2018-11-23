package cardealer.domain.dtos.view;

import com.google.gson.annotations.Expose;

public class SaleViewDto {

    @Expose
    private Double discount;

    @Expose
    private CarViewDto car;

    public SaleViewDto(Double discount, CarViewDto car) {
        this.discount = discount;
        this.car = car;
    }

    public Double getDiscount() {
        return this.discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public CarViewDto getCar() {
        return this.car;
    }

    public void setCar(CarViewDto car) {
        this.car = car;
    }
}
