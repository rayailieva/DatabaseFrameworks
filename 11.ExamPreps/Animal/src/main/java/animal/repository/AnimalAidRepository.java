package animal.repository;

import animal.domain.entities.AnimalAid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalAidRepository extends JpaRepository<AnimalAid, Integer> {
}
