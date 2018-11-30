package softuni.mostwanted.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.mostwanted.domain.entities.RaceEntry;

@Repository
public interface RaceEntriesRepository extends JpaRepository<RaceEntry, Integer> {
}
