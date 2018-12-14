package animal.domain.dtos;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AnimalImportDto {

    @Expose
    private String name;
    @Expose
    private String type;
    @Expose
    private Integer age;
    @Expose
    private PassportImportDto passport;

    public AnimalImportDto() {
    }

    @Length(min = 3, max = 20)
    @NotNull
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 3, max = 20)
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Min(1)
    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public PassportImportDto getPassport() {
        return this.passport;
    }

    public void setPassport(PassportImportDto passport) {
        this.passport = passport;
    }
}
