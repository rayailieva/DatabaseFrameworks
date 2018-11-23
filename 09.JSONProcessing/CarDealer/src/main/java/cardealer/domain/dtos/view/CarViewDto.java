package cardealer.domain.dtos.view;


import com.google.gson.annotations.Expose;

public class CarViewDto {

    @Expose
    private Long id;
    @Expose
    private String make;
    @Expose
    private String model;
    @Expose
    private Long travelledDistance;

    public CarViewDto() {

    }
}
