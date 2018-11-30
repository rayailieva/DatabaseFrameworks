package softuni.mostwanted.domain.dtos.binding.xml;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "race-entry")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntriesImportDto {

    @XmlAttribute(name = "has-finished")
    private Boolean hasFinished;

    @XmlAttribute(name = "finish-time")
    private Double finishTime;

    @XmlAttribute(name = "car-id")
    private Integer car;

    @XmlElement(name = "racer")
    private String racerName;

    public RaceEntriesImportDto() {
    }

    public Boolean getHasFinished() {
        return this.hasFinished;
    }

    public void setHasFinished(Boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    public Double getFinishTime() {
        return this.finishTime;
    }

    public void setFinishTime(Double finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getCar() {
        return this.car;
    }

    public void setCar(Integer car) {
        this.car = car;
    }

    public String getRacerName() {
        return this.racerName;
    }

    public void setRacerName(String racerName) {
        this.racerName = racerName;
    }
}
