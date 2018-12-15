package fastfood.domain.entities;

import fastfood.domain.entities.base.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "orders")
public class Order extends BaseEntity {

    private String customer;
    private LocalDateTime date;
    private String type;
    private BigDecimal totalPrice;
    private Employee employee;
    private List<OrderItem> orderItems;

    public Order(){}

    @Column(name = "customer", nullable = false)
    public String getCustomer() {
        return this.customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Column(name = "date", nullable = false)
    public LocalDateTime getDate() {
        return this.date;
    }

    @Column(name = "order_type", columnDefinition = "ENUM('ForHere', 'ToGo') default 'ForHere'")
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "total_price", nullable = false)
    public BigDecimal getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "employee_id", nullable = false)
    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @OneToMany(targetEntity = OrderItem.class, mappedBy = "order")
    public List<OrderItem> getOrderItems() {
        return this.orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
