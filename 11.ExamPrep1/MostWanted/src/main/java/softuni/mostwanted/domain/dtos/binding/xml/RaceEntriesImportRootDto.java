package softuni.mostwanted.domain.dtos.binding.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "race-entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntriesImportRootDto {

    @XmlElement(name = "race-entry")
    private RaceEntriesImportDto[] raceEntriesImportDtos;

    public RaceEntriesImportRootDto() {
    }

    public RaceEntriesImportDto[] getRaceEntriesImportDtos() {
        return this.raceEntriesImportDtos;
    }

    public void setRaceEntriesImportDtos(RaceEntriesImportDto[] raceEntriesImportDtos) {
        this.raceEntriesImportDtos = raceEntriesImportDtos;
    }
}
