package softuni.mostwanted.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.mostwanted.domain.entities.Town;

@Repository
public interface TownRepository extends JpaRepository<Town, Integer> {
}
