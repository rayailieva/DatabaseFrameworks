package cardealer.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerPurchaseExportDto {

    @XmlAttribute(name = "full-name")
    private String name;

    @XmlAttribute(name = "bought-cars")
    private Integer boughtCars;

    @XmlAttribute(name = "spent-money")
    private BigDecimal spentMoney;

    public CustomerPurchaseExportDto(){}

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBoughtCars() {
        return this.boughtCars;
    }

    public void setBoughtCars(Integer boughtCars) {
        this.boughtCars = boughtCars;
    }

    public BigDecimal getSpentMoney() {
        return this.spentMoney;
    }

    public void setSpentMoney(BigDecimal spentMoney) {
        this.spentMoney = spentMoney;
    }
}
