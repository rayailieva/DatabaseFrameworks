package softuni.mostwanted.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.mostwanted.domain.entities.Racer;

@Repository
public interface RacerRepository extends JpaRepository<Racer, Integer> {
}
