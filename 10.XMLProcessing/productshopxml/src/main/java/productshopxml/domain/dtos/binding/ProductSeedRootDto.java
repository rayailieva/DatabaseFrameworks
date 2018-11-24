package productshopxml.domain.dtos.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductSeedRootDto {

    @XmlElement(name = "product")
    private List<ProductSeedDto> productSeedDtos;

    public ProductSeedRootDto(){
    }

    public List<ProductSeedDto> getProductSeedDtos() {
        return this.productSeedDtos;
    }

    public void setProductSeedDtos(List<ProductSeedDto> productSeedDtos) {
        this.productSeedDtos = productSeedDtos;
    }
}
