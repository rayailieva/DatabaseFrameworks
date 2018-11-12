package usersystemapp.domain.entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "towns")
public class Town {

    private Long id;
    private String name;
    private Country country;
    private Set<User> bornHere;
    private Set<User> livingHere;

    public Town() {
        this.bornHere = new LinkedHashSet<>();
        this.livingHere = new LinkedHashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(optional = false)
    public Country getCountry() {
        return this.country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @OneToMany(mappedBy = "bornTown")
    public Set<User> getBornHere() {
        return this.bornHere;
    }

    public void setBornHere(Set<User> bornHere) {
        this.bornHere = bornHere;
    }

    @OneToMany(mappedBy = "currentTown")
    public Set<User> getLivingHere() {
        return this.livingHere;
    }

    public void setLivingHere(Set<User> livingHere) {
        this.livingHere = livingHere;
    }
}
