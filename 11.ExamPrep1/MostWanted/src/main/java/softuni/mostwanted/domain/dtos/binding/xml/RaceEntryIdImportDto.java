package softuni.mostwanted.domain.dtos.binding.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "entry")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntryIdImportDto {

    @XmlAttribute(name = "id")
    private Long id;

    public RaceEntryIdImportDto() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
