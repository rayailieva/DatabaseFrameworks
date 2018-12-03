package softuni.mostwanted.domain.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "districts")
public class District extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Town town;

    public District(){}

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Town getTown() {
        return this.town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
