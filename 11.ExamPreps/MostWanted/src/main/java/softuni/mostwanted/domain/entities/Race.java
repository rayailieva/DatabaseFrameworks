package softuni.mostwanted.domain.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "races")
public class Race extends BaseEntity {

    @Column(name = "laps")
    private Integer laps;

    @ManyToOne(cascade = CascadeType.ALL)
    private District district;

    @OneToMany(mappedBy = "race", cascade = CascadeType.MERGE)
    private List<RaceEntry> entries;

    public Race(){}

    public Integer getLaps() {
        return this.laps;
    }

    public void setLaps(Integer laps) {
        this.laps = laps;
    }

    public District getDistrict() {
        return this.district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public List<RaceEntry> getEntries() {
        return this.entries;
    }

    public void setEntries(List<RaceEntry> entries) {
        this.entries = entries;
    }
}
