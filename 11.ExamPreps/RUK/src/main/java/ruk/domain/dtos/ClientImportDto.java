package ruk.domain.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.NotNull;

public class ClientImportDto {

    @Expose
    @SerializedName(value = "first_name")
    private String firstName;
    @Expose
    @SerializedName(value = "last_name")
    private String lastName;
    @Expose
    @SerializedName(value = "age")
    private Integer age;
    @Expose
    @SerializedName(value = "appointed_employee")
    private String employee;

    public ClientImportDto(){}

    @NotNull
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmployee() {
        return this.employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }
}
