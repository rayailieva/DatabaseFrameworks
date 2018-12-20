package hiberspring.domain.entities;

import hiberspring.domain.entities.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "branches")
public class Branch extends BaseEntity {

    private String name;
    private Town town;

    public Branch(){}

    @Column(name = "name", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(targetEntity = Town.class)
    @JoinColumn(name = "town_id", nullable = false)
    public Town getTown() {
        return this.town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
