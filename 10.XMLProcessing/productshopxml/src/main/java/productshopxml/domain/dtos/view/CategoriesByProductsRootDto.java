package productshopxml.domain.dtos.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesByProductsRootDto {

    @XmlElement(name = "categories")
    private List<CategoriesByProductsDto> categoriesByProductsDtos;

    public CategoriesByProductsRootDto(){
    }

    public List<CategoriesByProductsDto> getCategoriesByProductsDtos() {
        return this.categoriesByProductsDtos;
    }

    public void setCategoriesByProductsDtos(List<CategoriesByProductsDto> categoriesByProductsDtos) {
        this.categoriesByProductsDtos = categoriesByProductsDtos;
    }
}
