package mostwanted.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "races")
public class Race extends BaseEntity{

    private Integer laps;
    private District district;
    private List<RaceEntry> entries;

    public Race(){
        this.entries = new ArrayList<>();
    }

    @Column(name = "laps", nullable = false)
    public Integer getLaps() {
        return this.laps;
    }

    public void setLaps(Integer laps) {
        this.laps = laps;
    }

    @ManyToOne
    public District getDistrict() {
        return this.district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    @OneToMany(mappedBy = "race", targetEntity = RaceEntry.class)
    public List<RaceEntry> getEntries() {
        return this.entries;
    }

    public void setEntries(List<RaceEntry> entries) {
        this.entries = entries;
    }
}
