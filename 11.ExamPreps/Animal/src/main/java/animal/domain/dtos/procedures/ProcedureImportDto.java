package animal.domain.dtos.procedures;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "procedure")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcedureImportDto {

    @XmlElement(name = "vet")
    private String vet;
    @XmlElement(name = "animal")
    private String animal;
    @XmlElement(name = "animal-aids")
    private AnimalAidXmlImportRootDto animalAidXmlImportRootDto;

    public ProcedureImportDto(){}

    public String getVet() {
        return this.vet;
    }

    public void setVet(String vet) {
        this.vet = vet;
    }

    public String getAnimal() {
        return this.animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public AnimalAidXmlImportRootDto getAnimalAidXmlImportRootDto() {
        return this.animalAidXmlImportRootDto;
    }

    public void setAnimalAidXmlImportRootDto(AnimalAidXmlImportRootDto animalAidXmlImportRootDto) {
        this.animalAidXmlImportRootDto = animalAidXmlImportRootDto;
    }
}
