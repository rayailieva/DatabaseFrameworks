package productshopxml.domain.dtos.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductInRangeRootDto {

    private List<ProductInRangeDto> productsView;

    public ProductInRangeRootDto(){
    }

    public List<ProductInRangeDto> getProductsView() {
        return this.productsView;
    }

    public void setProductsView(List<ProductInRangeDto> productsView) {
        this.productsView = productsView;
    }
}
