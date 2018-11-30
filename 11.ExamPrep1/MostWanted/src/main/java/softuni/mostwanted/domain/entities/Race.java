package softuni.mostwanted.domain.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "races")
public class Race extends BaseEntity {

    private Integer laps;
    private District district;
    private Set<RaceEntry> entries;

    public Race(){
        this.entries = new LinkedHashSet<>();
    }

    @Column(name = "laps",nullable = false, columnDefinition = "INT NOT NULL DEFAULT 0")
    public Integer getLaps() {
        return this.laps;
    }

    public void setLaps(Integer laps) {
        this.laps = laps;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public District getDistrict() {
        return this.district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    @OneToMany(mappedBy = "race", cascade = CascadeType.MERGE)
    public Set<RaceEntry> getEntries() {
        return this.entries;
    }

    public void setEntries(Set<RaceEntry> entries) {
        this.entries = entries;
    }
}
