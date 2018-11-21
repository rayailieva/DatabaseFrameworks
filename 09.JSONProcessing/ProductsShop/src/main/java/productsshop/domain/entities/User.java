package productsshop.domain.entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "users")
public class User extends BaseEntity{

    private String firstName;
    private String lastName;
    private Integer age;
    private Set<User> friends;
    private Set<Product> sell;
    private Set<Product> buy;

    public User(){
        this.friends = new LinkedHashSet<>();
        this.sell = new LinkedHashSet<>();
        this.buy = new LinkedHashSet<>();
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

    @Column(name = "age", nullable = false)
    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @ManyToMany
    @JoinTable(name = "users_friends",
        joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "friend_id", referencedColumnName = "id")})

    public Set<User> getFriends() {
        return this.friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "seller", cascade = CascadeType.ALL)
    public Set<Product> getSell() {
        return this.sell;
    }

    public void setSell(Set<Product> sell) {
        this.sell = sell;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "buyer", cascade = CascadeType.ALL)
    public Set<Product> getBuy() {
        return this.buy;
    }

    public void setBuy(Set<Product> buy) {
        this.buy = buy;
    }
}
