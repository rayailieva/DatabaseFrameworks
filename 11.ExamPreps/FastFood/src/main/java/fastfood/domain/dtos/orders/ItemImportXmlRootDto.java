package fastfood.domain.dtos.orders;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "items")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemImportXmlRootDto {

    @XmlElement(name = "item")
    private ItemImportXmlDto[] itemImportXmlDto;

    public ItemImportXmlRootDto() {
    }

    public ItemImportXmlDto[] getItemImportXmlDto() {
        return this.itemImportXmlDto;
    }

    public void setItemImportXmlDto(ItemImportXmlDto[] itemImportXmlDto) {
        this.itemImportXmlDto = itemImportXmlDto;
    }
}
