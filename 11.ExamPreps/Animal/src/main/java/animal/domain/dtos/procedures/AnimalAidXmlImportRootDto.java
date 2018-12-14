package animal.domain.dtos.procedures;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "animal-aids")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnimalAidXmlImportRootDto {

    @XmlElement(name = "animal-aid")
    private AnimalAidXmlImportDto[] animalAidXmlImportDtos;

    public AnimalAidXmlImportRootDto() {
    }

    public AnimalAidXmlImportDto[] getAnimalAidXmlImportDtos() {
        return this.animalAidXmlImportDtos;
    }

    public void setAnimalAidXmlImportDtos(AnimalAidXmlImportDto[] animalAidXmlImportDtos) {
        this.animalAidXmlImportDtos = animalAidXmlImportDtos;
    }
}
