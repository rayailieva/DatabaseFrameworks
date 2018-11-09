package entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="patients")
public class Patient {

    private long id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private Date birthDate;
    private byte[] picture;
    private boolean hasMedicalInsurance;
    private List<Visitation> visitations;
    private List<Medicament> prescriptions;
    private List<Diagnose> diagnoses;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "email")
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "birth_date")
    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Column(name = "picture")
    public byte[] getPicture() {
        return this.picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @Column(name = "has_medical_insurance")
    public boolean isHasMedicalInsurance() {
        return this.hasMedicalInsurance;
    }

    public void setHasMedicalInsurance(boolean hasMedicalInsurance) {
        this.hasMedicalInsurance = hasMedicalInsurance;
    }


    @OneToMany(mappedBy = "patient")
    public List<Visitation> getVisitations() {
        return this.visitations;
    }

    public void setVisitations(List<Visitation> visitations) {
        this.visitations = visitations;
    }

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "id"))
    public List<Medicament> getPrescriptions() {
        return this.prescriptions;
    }

    public void setPrescriptions(List<Medicament> prescriptions) {
        this.prescriptions = prescriptions;
    }

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "id"))
    public List<Diagnose> getDiagnoses() {
        return this.diagnoses;
    }

    public void setDiagnoses(List<Diagnose> diagnoses) {
        this.diagnoses = diagnoses;
    }

    @Column(name = "address")
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
