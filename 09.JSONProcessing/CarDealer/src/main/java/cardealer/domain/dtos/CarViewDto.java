package cardealer.domain.dtos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CarViewDto implements Serializable {

    @SerializedName("Id")
    private Long id;

    @SerializedName("Make")
    private String make;

    @SerializedName("Model")
    private String model;

    @SerializedName("TravelledDistance")
    private Long travelledDistance;
}
