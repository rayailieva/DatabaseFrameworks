package ruk.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity(name = "clients")
public class Client extends BaseEntity {


    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "age")
    private Integer age;

    @OneToOne(targetEntity = BankAccount.class)
    private BankAccount bankAccount;

    public Client(){
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BankAccount getBankAccount() {
        return this.bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}
