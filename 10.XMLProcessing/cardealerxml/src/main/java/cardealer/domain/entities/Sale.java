package cardealer.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "sales")
public class Sale extends BaseEntity{

    private Double discount;
    private Car car;
    private Customer customer;

    public Sale(){
    }

    @Column(name = "discount")
    public Double getDiscount() {
        return this.discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }


    @ManyToOne(targetEntity = Car.class)
    @JoinColumn(
            name = "car_id", referencedColumnName = "id"
    )
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(
            name = "customer_id", referencedColumnName = "id"
    )
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
