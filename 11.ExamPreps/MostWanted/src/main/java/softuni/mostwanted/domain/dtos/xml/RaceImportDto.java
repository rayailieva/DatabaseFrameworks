package softuni.mostwanted.domain.dtos.xml;

import softuni.mostwanted.domain.entities.District;
import softuni.mostwanted.domain.entities.RaceEntry;


import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "race")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceImportDto {

    @XmlElement(name = "laps")
    private Integer laps;

    @XmlElement(name = "district-name")
    private String district;

    @XmlElementWrapper(name = "entries")
    @XmlElement(name = "entry")
    private List<RaceEntryIdImportDto> entries;

    public RaceImportDto() {
        this.entries = new ArrayList<>();
    }

    public Integer getLaps() {
        return this.laps;
    }

    public void setLaps(Integer laps) {
        this.laps = laps;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public List<RaceEntryIdImportDto> getEntries() {
        return this.entries;
    }

    public void setEntries(List<RaceEntryIdImportDto> entries) {
        this.entries = entries;
    }
}
