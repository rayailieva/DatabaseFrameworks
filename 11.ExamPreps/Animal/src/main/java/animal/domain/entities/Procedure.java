package animal.domain.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "procedures")
public class Procedure extends BaseEntity {

    private List<AnimalAid> services;
    private Animal animal;
    private Vet vet;
    private LocalDate date;

    public Procedure(){}

    @ManyToMany(targetEntity = AnimalAid.class, cascade = CascadeType.ALL)
    @JoinTable(name = "animal_aids_procedures",
    joinColumns = @JoinColumn(name = "animal_aid_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "procedure_id", referencedColumnName = "id"))
    public List<AnimalAid> getServices() {
        return this.services;
    }

    public void setServices(List<AnimalAid> services) {
        this.services = services;
    }

    @OneToOne(targetEntity = Animal.class)
    public Animal getAnimal() {
        return this.animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    @ManyToOne(targetEntity = Vet.class)
    public Vet getVet() {
        return this.vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }

    @Column(name = "date_performed")
    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
