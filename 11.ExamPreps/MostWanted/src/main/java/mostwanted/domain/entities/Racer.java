package mostwanted.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "racers")
public class Racer extends BaseEntity {

    private String name;
    private Integer age;
    private BigDecimal bounty;
    private Town homeTown;
    private List<Car> cars;

    public Racer(){

    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "age")
    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Column(name = "bounty")
    public BigDecimal getBounty() {
        return this.bounty;
    }

    public void setBounty(BigDecimal bounty) {
        this.bounty = bounty;
    }

    @ManyToOne(targetEntity = Town.class)
    public Town getHomeTown() {
        return this.homeTown;
    }

    public void setHomeTown(Town homeTown) {
        this.homeTown = homeTown;
    }

    @OneToMany(targetEntity = Car.class,mappedBy = "racer")
    public List<Car> getCars() {
        return this.cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
