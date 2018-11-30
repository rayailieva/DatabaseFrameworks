package softuni.mostwanted.domain.dtos.binding.json;

import com.google.gson.annotations.Expose;
import softuni.mostwanted.domain.entities.Racer;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CarImportDto {

    @Expose
    @NotNull
    private String brand;

    @Expose
    @NotNull
    private String model;

    @Expose
    private BigDecimal price;

    @Expose
    @NotNull
    private Integer yearOfProduction;

    @Expose
    private Double maxSpeed;

    @Expose
    private Double zeroToSixty;

    @Expose
    private Racer racer;

    public CarImportDto(){}

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getYearOfProduction() {
        return this.yearOfProduction;
    }

    public void setYearOfProduction(Integer yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public Double getMaxSpeed() {
        return this.maxSpeed;
    }

    public void setMaxSpeed(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Double getZeroToSixty() {
        return this.zeroToSixty;
    }

    public void setZeroToSixty(Double zeroToSixty) {
        this.zeroToSixty = zeroToSixty;
    }

    public Racer getRacer() {
        return this.racer;
    }

    public void setRacer(Racer racer) {
        this.racer = racer;
    }
}
