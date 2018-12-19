package ruk.domain.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

public class EmployeeImportDto {

    @Expose
    @SerializedName(value = "full_name")
    private String fullName;
    @Expose
    @SerializedName(value = "salary")
    private BigDecimal salary;
    @Expose
    @SerializedName(value = "started_on")
    private Date startedOn;
    @Expose
    @SerializedName(value = "branch_name")
    private String branchName;

    public EmployeeImportDto(){}

    @NotNull
    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BigDecimal getSalary() {
        return this.salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Date getStartedOn() {
        return this.startedOn;
    }

    public void setStartedOn(Date startedOn) {
        this.startedOn = startedOn;
    }

    @NotNull
    public String getBranchName() {
        return this.branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}
