package productshopxml.domain.dtos.view;

import productshopxml.domain.entities.User;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductBuyerDto {

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "price")
    private BigDecimal price;

    @XmlElement(name = "buyer-first-name")
    private User buyerFirstName;

    @XmlElement(name = "buyer-last-name")
    private User buyerLastName;


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public User getBuyerFirstName() {
        return this.buyerFirstName;
    }

    public void setBuyerFirstName(User buyerFirstName) {
        this.buyerFirstName = buyerFirstName;
    }

    public User getBuyerLastName() {
        return this.buyerLastName;
    }

    public void setBuyerLastName(User buyerLastName) {
        this.buyerLastName = buyerLastName;
    }
}
