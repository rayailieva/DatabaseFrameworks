package softuni.mostwanted.domain.dtos.binding.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "races")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceImportRootDto {

    @XmlElement(name = "race")
    private RaceImportDto[] raceImportDtos;

    private RaceImportRootDto(){}

    public RaceImportDto[] getRaceImportDtos() {
        return this.raceImportDtos;
    }

    public void setRaceImportDtos(RaceImportDto[] raceImportDtos) {
        this.raceImportDtos = raceImportDtos;
    }
}
