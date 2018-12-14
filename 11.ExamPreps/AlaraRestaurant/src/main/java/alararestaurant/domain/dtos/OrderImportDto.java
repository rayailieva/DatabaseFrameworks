package alararestaurant.domain.dtos;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderImportDto {

    @XmlElement(name = "customer")
    private String customer;
    @XmlElement(name = "employee")
    private String employee;
    @XmlElement(name = "date-time")
    private String dateTime;
    @XmlElement(name = "type")
    private String type;
    @XmlElement(name = "items")
    private ItemXmlImportRootDto itemXmlImportRootDto;

    public OrderImportDto() {
    }

    @NotNull
    public String getCustomer() {
        return this.customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @NotNull
    public String getEmployee() {
        return this.employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ItemXmlImportRootDto getItemXmlImportRootDto() {
        return this.itemXmlImportRootDto;
    }

    public void setItemXmlImportRootDto(ItemXmlImportRootDto itemXmlImportRootDto) {
        this.itemXmlImportRootDto = itemXmlImportRootDto;
    }
}
