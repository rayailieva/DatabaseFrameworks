package cardealer.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarAndPartsExportRootDto {

    @XmlElement(name = "car")
    private List<CarAndPartsExportDto> carAndPartsExportDtos;

    public CarAndPartsExportRootDto() {
    }

    public List<CarAndPartsExportDto> getCarAndPartsExportDtos() {
        return this.carAndPartsExportDtos;
    }

    public void setCarAndPartsExportDtos(List<CarAndPartsExportDto> carAndPartsExportDtos) {
        this.carAndPartsExportDtos = carAndPartsExportDtos;
    }
}
