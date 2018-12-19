package animal.domain.dtos.vets;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "vet")
@XmlAccessorType(XmlAccessType.FIELD)
public class VetImportDto {

    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "profession")
    private String profession;
    @XmlElement(name = "age")
    private Integer age;
    @XmlElement(name = "phone-number")
    private String phoneNumber;

    public VetImportDto(){}

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return this.profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @NotNull
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
