package animal.domain.dtos;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class PassportImportDto {

    @Expose
    private String serialNumber;
    @Expose
    private String ownerName;
    @Expose
    private String ownerPhoneNumber;
    @Expose
    private String registrationDate;

    public PassportImportDto(){}

    @NotNull
    @Pattern(regexp = "^\\S{7}\\d{3}$")
    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Length(min = 3, max = 30)
    public String getOwnerName() {
        return this.ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @NotNull
    @Pattern(regexp = "^(?:\\+359|0)\\d{9}$")
    public String getOwnerPhoneNumber() {
        return this.ownerPhoneNumber;
    }

    public void setOwnerPhoneNumber(String ownerPhoneNumber) {
        this.ownerPhoneNumber = ownerPhoneNumber;
    }

    public String getRegistrationDate() {
        return this.registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
}
