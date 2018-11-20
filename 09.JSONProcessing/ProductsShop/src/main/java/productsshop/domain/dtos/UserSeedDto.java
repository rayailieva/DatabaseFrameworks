package productsshop.domain.dtos;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

public class UserSeedDto {

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private Integer age;


    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    @Length(min = 3)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
