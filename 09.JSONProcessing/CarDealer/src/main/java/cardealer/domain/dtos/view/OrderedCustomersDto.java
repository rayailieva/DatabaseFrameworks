package cardealer.domain.dtos.view;

import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

public class OrderedCustomersDto {

    public OrderedCustomersDto() {
        this.sales = new LinkedHashSet<>();
    }

    @Expose
    private Long id;

    @Expose
    private String name;

    @Expose
    private Date birthDate;

    @Expose
    private boolean isYoungDriver;

    @Expose
    private Set<SaleViewDto> sales;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Set<SaleViewDto> getSales() {
        return this.sales;
    }

    public void setSales(Set<SaleViewDto> sales) {
        this.sales = sales;
    }
}
