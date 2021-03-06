package animal.domain.dtos.procedures;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "animal-aid")
@XmlAccessorType(XmlAccessType.FIELD)
public class AidImportDto {

    @XmlElement(name = "name")
    private String name;

    public AidImportDto() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
