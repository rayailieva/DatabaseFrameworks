package ruk.domain.entities;

import ruk.domain.entities.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "branches")
public class Branch extends BaseEntity {

    private String name;

    public Branch(){}

    @Column(name = "name", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
