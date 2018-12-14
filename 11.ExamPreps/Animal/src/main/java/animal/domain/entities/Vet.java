package animal.domain.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "vets")
public class Vet extends BaseEntity {

    private String name;
    private String profession;
    private Integer age;
    private String phoneNumber;
    private List<Procedure> procedures;

    public Vet(){}

    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "profession")
    public String getProfession() {
        return this.profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Column(name = "age")
    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @OneToMany(targetEntity = Procedure.class, mappedBy = "vet", fetch = FetchType.EAGER)
    public List<Procedure> getProcedures() {
        return this.procedures;
    }

    public void setProcedures(List<Procedure> procedures) {
        this.procedures = procedures;
    }
}
