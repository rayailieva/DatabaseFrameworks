package productshopxml.domain.dtos.view;

import javax.xml.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersProductsDto {

    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlElementWrapper(name = "sold-products")
    @XmlElement(name = "product")
    private List<ProductBuyerDto> productsSold;

    public UsersProductsDto(){
        this.productsSold = new LinkedList<>();
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public List<ProductBuyerDto> getProductsSold() {
        return this.productsSold;
    }

    public void setProductsSold(List<ProductBuyerDto> productsSold) {
        this.productsSold = productsSold;
    }
}
