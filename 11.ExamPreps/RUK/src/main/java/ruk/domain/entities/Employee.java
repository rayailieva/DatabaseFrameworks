package ruk.domain.entities;

import org.hibernate.annotations.JoinColumnOrFormula;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "employees")
public class Employee extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "salary")
    private BigDecimal salary;

    @Column(name = "started_on")
    private Date startedOn;

    @ManyToOne(targetEntity = Branch.class)
    @JoinColumn(name = "branch", nullable = false)
    private Branch branch;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "employees_clients",
             joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
             inverseJoinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"))
    private List<Client> clients;

    public Employee(){
        this.clients = new ArrayList<>();
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSalary() {
        return this.salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Date getStartedOn() {
        return this.startedOn;
    }

    public void setStartedOn(Date startedOn) {
        this.startedOn = startedOn;
    }

    public Branch getBranch() {
        return this.branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public List<Client> getClients() {
        return this.clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}
