package animal.domain.dtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "vets")
@XmlAccessorType(XmlAccessType.FIELD)
public class VetImportRootDto {

    @XmlElement(name = "vet")
    private VetImportDto[] vetImportDtos;

    public VetImportRootDto() {
    }

    public VetImportDto[] getVetImportDtos() {
        return this.vetImportDtos;
    }

    public void setVetImportDtos(VetImportDto[] vetImportDtos) {
        this.vetImportDtos = vetImportDtos;
    }
}
