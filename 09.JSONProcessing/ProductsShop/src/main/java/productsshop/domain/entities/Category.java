package productsshop.domain.entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "categories")
public class Category extends BaseEntity{

    private String name;
    private Set<Product> products;

    public Category(){
        this.products = new LinkedHashSet<>();
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany
    @JoinTable(name = "category_products",
        joinColumns = {@JoinColumn(name = "category_id")},
        inverseJoinColumns = {@JoinColumn(name = "product_id")})
    public Set<Product> getProducts() {
        return this.products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
