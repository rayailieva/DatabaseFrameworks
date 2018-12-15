package fastfood.domain.dtos;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class EmployeeImportDto {

    @Expose
    private String name;
    @Expose
    private Integer age;
    @Expose
    private String position;

    public EmployeeImportDto(){}

    @Length(min = 3, max = 30)
    @NotNull
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Min(15)
    @Max(80)
    @NotNull
    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @NotNull
    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
