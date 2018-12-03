package softuni.mostwanted.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "towns")
public class Town extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    public Town() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
