package softuni.mostwanted.domain.entities;

import javax.persistence.*;

@Entity(name = "race_entries")
public class RaceEntry extends BaseEntity {

    @Column(name = "has_finished")
    private Boolean hasFinished;

    @Column(name = "finish_time")
    private Integer finishTime;

    @ManyToOne
    private Car car;

    @ManyToOne(cascade = CascadeType.ALL)
    private Racer racer;

    @ManyToOne(cascade = CascadeType.ALL)
    private Race race;

    public RaceEntry(){}

    public Boolean getHasFinished() {
        return this.hasFinished;
    }

    public void setHasFinished(Boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    public Integer getFinishTime() {
        return this.finishTime;
    }

    public void setFinishTime(Integer finishTime) {
        this.finishTime = finishTime;
    }

    public Car getCar() {
        return this.car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Racer getRacer() {
        return this.racer;
    }

    public void setRacer(Racer racer) {
        this.racer = racer;
    }

    public Race getRace() {
        return this.race;
    }

    public void setRace(Race race) {
        this.race = race;
    }
}
