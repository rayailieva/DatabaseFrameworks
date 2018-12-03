package softuni.mostwanted.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.mostwanted.domain.entities.Race;

@Repository
public interface RaceRepository extends JpaRepository<Race, Integer> {
}
