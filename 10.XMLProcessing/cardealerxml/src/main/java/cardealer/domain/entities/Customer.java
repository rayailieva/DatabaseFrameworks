package cardealer.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity(name = "customers")
public class Customer extends BaseEntity{

    private String name;
    private Date birthDate;
    private Boolean isYoungDriver;
    private List<Sale> sales;
    private List<Sale> purchases;

    public Customer() {
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "birth_date")
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Column(name = "is_young_driver")
    public Boolean getYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(Boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    @OneToMany(targetEntity = Sale.class, mappedBy = "customer")
    public List<Sale> getSales() {
        return this.sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    public List<Sale> getPurchases() {
        return this.purchases;
    }

    public void setPurchases(List<Sale> purchases) {
        this.purchases = purchases;
    }
}
