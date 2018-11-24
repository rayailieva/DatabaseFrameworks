package cardealer.domain.dtos.view;

import com.google.gson.annotations.Expose;

import java.util.Set;

public class CarPartsViewDto {

    @Expose
    private CarViewDto car;

    @Expose
    private Set<PartsViewDto> parts;

    public CarPartsViewDto() {
    }

    public CarViewDto getCar() {
        return this.car;
    }

    public void setCar(CarViewDto car) {
        this.car = car;
    }

    public Set<PartsViewDto> getParts() {
        return this.parts;
    }

    public void setParts(Set<PartsViewDto> parts) {
        this.parts = parts;
    }
}
