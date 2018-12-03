package animal.domain.dtos.xml;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "vet")
@XmlAccessorType(XmlAccessType.FIELD)
public class VetImportDto {

    @NotNull
    @Length(min = 3, max = 40)
    private String name;

    @NotNull
    @Length(min = 3, max = 50)
    private String profession;

    @NotNull
    @Range(min = 22, max = 65)
    private Integer age;

    @NotNull
    @Pattern(regexp = "^(?:\\+359|0)\\d{9}$")
    @XmlElement(name = "phone-number")
    private String phoneNumber;

    public VetImportDto() {
    }

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

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
