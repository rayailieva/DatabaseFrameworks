package hiberspring.domain.entities;

import hiberspring.domain.entities.base.BaseEntity;

import javax.persistence.*;

@Entity(name = "employees")
public class Employee extends BaseEntity {

    private String firstName;
    private String lastName;
    private String position;
    private EmployeeCard card;
    private Branch branch;

    public Employee(){}

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "position", nullable = false)
    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @OneToOne(targetEntity = EmployeeCard.class)
    @JoinColumn(name = "card", nullable = false, unique = true)
    public EmployeeCard getCard() {
        return this.card;
    }

    public void setCard(EmployeeCard card) {
        this.card = card;
    }

    @ManyToOne(targetEntity = Branch.class)
    @JoinColumn(name = "branch_id", nullable = false)
    public Branch getBranch() {
        return this.branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

}
