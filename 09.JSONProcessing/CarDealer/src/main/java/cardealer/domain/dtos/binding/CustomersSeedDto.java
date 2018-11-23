package cardealer.domain.dtos.binding;

import com.google.gson.annotations.Expose;

import java.time.LocalDate;
import java.util.Date;

public class CustomersSeedDto {

    @Expose
    private String name;

    @Expose
    private Date birthDate;

    @Expose
    private boolean isYoungDriver;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return this.isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }
}
