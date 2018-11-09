package entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<BillingDetail> billingDetails;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name="first_name")
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name="last_name")
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name="email")
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name="password")
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(mappedBy = "owner")
    public List<BillingDetail> getBillingDetails() {
        return this.billingDetails;
    }

    public void setBillingDetails(List<BillingDetail> billingDetails) {
        this.billingDetails = billingDetails;
    }
}
