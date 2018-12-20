package hiberspring.domain.entities;

import hiberspring.domain.entities.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "towns")
public class Town extends BaseEntity {

    private String name;
    private Long population;

    public Town(){}

    @Column(name = "name", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "population", nullable = false)
    public Long getPopulation() {
        return this.population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }
}
