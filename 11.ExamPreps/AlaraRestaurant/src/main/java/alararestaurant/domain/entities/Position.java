package alararestaurant.domain.entities;

import alararestaurant.domain.entities.Base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "positions")
public class Position extends BaseEntity {

    private String name;
    private List<Employee> employees;

    public Position(){}

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(targetEntity = Employee.class, mappedBy = "position")
    public List<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
