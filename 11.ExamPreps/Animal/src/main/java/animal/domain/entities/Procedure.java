package animal.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "procedures")
public class Procedure extends BaseEntity {

    @ManyToOne
    private Animal animal;

    @ManyToOne
    private Vet vet;

    @Column(name = "date_performed")
    private Date date;

    @ManyToMany
    @JoinTable(name = "animal_aids_procedures",
    joinColumns = {@JoinColumn(name = "animal_aid_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "procedure_id", referencedColumnName = "id")})
    private List<AnimalAid> services;

    public Procedure(){
        this.services = new ArrayList<>();
    }

    public List<AnimalAid> getServices() {
        return this.services;
    }

    public void setServices(List<AnimalAid> services) {
        this.services = services;
    }

    public Animal getAnimal() {
        return this.animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Vet getVet() {
        return this.vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getCost() {
        return this.services
                .stream()
                .map(AnimalAid::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
