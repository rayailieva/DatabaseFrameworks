package cardealer.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "cars")
public class Car extends BaseEntity{

    private String make;
    private String model;
    private Long travelled_distance;
    private Set<Part> parts;

    public Car(){
    }

    @Column(name = "make")
    public String getMake() {
        return this.make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @Column(name = "model")
    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "travelled_distance")
    public Long getTravelled_distance() {
        return this.travelled_distance;
    }

    public void setTravelled_distance(Long travelled_distance) {
        this.travelled_distance = travelled_distance;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "parts_cars",
        joinColumns = {@JoinColumn(name = "car_id")},
        inverseJoinColumns = {@JoinColumn(name = "part_id")})
    public Set<Part> getParts() {
        return this.parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }
}
