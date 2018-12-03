package animal.domain.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity(name = "animals")
public class Animal extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "age")
    private Integer age;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_serial_number", referencedColumnName = "serialNumber")
    private Passport passport;

    @OneToMany(mappedBy = "animal")
    private List<Procedure> procedures;

    public Animal(){
        this.procedures = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Passport getPassport() {
        return this.passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }
}
