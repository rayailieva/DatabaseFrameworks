package productshopxml.domain.dtos.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategorySeedRootDto {

    @XmlElement(name = "category")
    private List<CategorySeedDto> categorySeedDtos;

    public CategorySeedRootDto(){
    }

    public List<CategorySeedDto> getCategorySeedDtos() {
        return categorySeedDtos;
    }

    public void setCategorySeedDtos(List<CategorySeedDto> categorySeedDtos) {
        this.categorySeedDtos = categorySeedDtos;
    }
}
