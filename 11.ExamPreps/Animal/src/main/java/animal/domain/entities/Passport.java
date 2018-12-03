package animal.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity(name = "passports")
public class Passport {

    @Id
    @Column(length = 10, nullable = false)
    private String serialNumber;

    @OneToOne
    private Animal animal;

    @Column(name = "owner_phone_number", nullable = false)
    private String ownerPhoneNumber;

    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "registered_on")
    private Date registrationDate;

    public Passport() {
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Animal getAnimal() {
        return this.animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public String getOwnerPhoneNumber() {
        return this.ownerPhoneNumber;
    }

    public void setOwnerPhoneNumber(String ownerPhoneNumber) {
        this.ownerPhoneNumber = ownerPhoneNumber;
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Date getRegistrationDate() {
        return this.registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
