package animal.domain.dtos.procedures;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "animal-aids")
@XmlAccessorType(XmlAccessType.FIELD)
public class AidImportRootDto {

    @XmlElement(name = "animal-aid")
    private AidImportDto[] aidImportDtos;

    public AidImportRootDto() {
    }

    public AidImportDto[] getAidImportDtos() {
        return this.aidImportDtos;
    }

    public void setAidImportDtos(AidImportDto[] aidImportDtos) {
        this.aidImportDtos = aidImportDtos;
    }
}
