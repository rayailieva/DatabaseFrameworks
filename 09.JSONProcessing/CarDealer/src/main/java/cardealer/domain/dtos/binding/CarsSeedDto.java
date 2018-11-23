package cardealer.domain.dtos.binding;

import com.google.gson.annotations.Expose;

public class CarsSeedDto {

    @Expose
    private String make;

    @Expose
    private String model;

    @Expose
    private Double travelledDistance;

    public String getMake() {
        return this.make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getTravelledDistance() {
        return this.travelledDistance;
    }

    public void setTravelledDistance(Double travelledDistance) {
        this.travelledDistance = travelledDistance;
    }
}
