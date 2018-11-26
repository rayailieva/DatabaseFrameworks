package productshopxml.domain.entities;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity(name = "users")
public class User extends BaseEntity{

    private String firstName;
    private String lastName;
    private Integer age;
    private List<User> friend;
    private List<Product> boughtProducts;
    private List<Product> soldProducts;

    public User() {

    }

    @Column(name = "first_name")
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

    @Column(name = "age")
    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @ManyToMany(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_friends",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id")
    )
    public List<User> getFriend() {
        return this.friend;
    }

    public void setFriend(List<User> friend) {
        this.friend = friend;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_buys",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    public List<Product> getBoughtProducts() {
        return this.boughtProducts;
    }

    public void setBoughtProducts(List<Product> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "user_sells",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    public List<Product> getSoldProducts() {
        return this.soldProducts;
    }

    public void setSoldProducts(List<Product> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
