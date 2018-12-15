package fastfood.domain.entities;

import fastfood.domain.entities.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "order_items")
public class OrderItem extends BaseEntity {

    private Order order;
    private Item item;
    private Integer quantity;

    public OrderItem(){}

    @ManyToOne(targetEntity = Order.class)
    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @ManyToOne(targetEntity = Item.class)
    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Column(name = "quantity", nullable = false)
    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
