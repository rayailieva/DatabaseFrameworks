package fastfood.domain.dtos.orders;

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
    @XmlElement(name = "date")
    private String date;
    @XmlElement(name = "type")
    private String type;
    @XmlElement(name = "items")
    private ItemImportXmlRootDto items;

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
    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @NotNull
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ItemImportXmlRootDto getItems() {
        return this.items;
    }

    public void setItems(ItemImportXmlRootDto items) {
        this.items = items;
    }
}
