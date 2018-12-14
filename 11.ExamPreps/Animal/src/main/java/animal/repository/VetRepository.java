package animal.repository;

import animal.domain.entities.Vet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VetRepository extends JpaRepository<Vet, Integer> {

    Optional<Vet> findByName(String name);
}
