package alararestaurant.domain.dtos.orders;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
    private ItemXmlImportRootDto items;

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

    @NotNull
    public String getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @NotNull
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ItemXmlImportRootDto getItems() {
        return this.items;
    }

    public void setItems(ItemXmlImportRootDto items) {
        this.items = items;
    }
}
