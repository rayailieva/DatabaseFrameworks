package animal.domain.entities;

import animal.domain.entities.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity(name = "animal_aids")
public class AnimalAid extends BaseEntity {

    private String name;
    private BigDecimal price;

    public AnimalAid(){}

    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
