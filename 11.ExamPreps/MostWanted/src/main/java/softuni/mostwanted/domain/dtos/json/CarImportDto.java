package softuni.mostwanted.domain.dtos.json;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class CarImportDto {

    @Expose
    private String brand;

    @Expose
    private String model;

    @Expose
    private BigDecimal price;

    @Expose
    private Integer yearOfProduction;

    @Expose
    private Double maxSpeed;

    @Expose
    private Double zeroToSixty;

    @Expose
    private String racerName;

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

    public String getRacerName() {
        return this.racerName;
    }

    public void setRacerName(String racerName) {
        this.racerName = racerName;
    }
}
