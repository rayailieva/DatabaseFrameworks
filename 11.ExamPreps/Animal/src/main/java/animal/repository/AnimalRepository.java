package animal.repository;


import animal.domain.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    Optional<Animal> findByPassport(String passport);


    @Query( "" +
            "SELECT a " +
            "FROM animal.domain.entities.Animal a " +
            "JOIN a.passport p " +
            "ORDER BY a.age, p.serialNumber "
    )
    List<Animal> exportAnimals();
}
