package productshopxml.domain.dtos.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductBuyerRootDto{

    @XmlElement(name = "product")
    private List<ProductBuyerDto> productView;

    public ProductBuyerRootDto(){
    }

    public List<ProductBuyerDto> getProductView() {
        return this.productView;
    }

    public void setProductView(List<ProductBuyerDto> productView) {
        this.productView = productView;
    }
}
