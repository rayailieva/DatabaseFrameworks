package ruk.domain.dtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class EmployeeImportDto {

    @Expose
    private String full_name;
    @Expose
    private Double salary;
    @Expose
    private Date started_on;
    @Expose
    private String branch_name;

    public EmployeeImportDto(){}

    @NotNull
    public String getFull_name() {
        return this.full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public Double getSalary() {
        return this.salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getStarted_on() {
        return this.started_on;
    }

    public void setStarted_on(Date started_on) {
        this.started_on = started_on;
    }

    @NotNull
    public String getBranch_name() {
        return this.branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }
}
