package animal.repository;


import animal.domain.entities.Animal;
import animal.domain.entities.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    Optional<Animal> findOneByPassport(Passport passportNumber);


}
