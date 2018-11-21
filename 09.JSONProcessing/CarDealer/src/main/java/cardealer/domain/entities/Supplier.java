package cardealer.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "suppliers")
public class Supplier extends BaseEntity {

    private String name;
    private boolean isImported;

    public Supplier(){
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "is_imported")
    public boolean getisImported() {
        return this.isImported;
    }

    public void setisImported(boolean isImported) {
        this.isImported = isImported;
    }
}
