package productsshop.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "products")
public class Product extends BaseEntity{

    private String name;
    private BigDecimal price;
    private User seller;
    private User buyer;
    private Set<Category> categories;

    public Product(){
        this.categories = new LinkedHashSet<>();
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "price", nullable = false)
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    public User getSeller() {
        return this.seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    @ManyToOne
    @JoinColumn(name = "buyer_id", referencedColumnName = "id")
    public User getBuyer() {
        return this.buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    @ManyToMany(mappedBy = "products", cascade = CascadeType.ALL)
    public Set<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
