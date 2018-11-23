package cardealer.domain.dtos;

import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class OrderedCustomersDto {

    public OrderedCustomersDto() {
        this.sales = new LinkedHashSet<>();
    }

    private Long id;

    private String name;

    private Date birthDate;

    private boolean isYoungDriver;

    private Set<SaleViewDto> sales;

}
