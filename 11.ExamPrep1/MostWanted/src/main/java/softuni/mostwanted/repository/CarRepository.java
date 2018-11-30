package softuni.mostwanted.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.mostwanted.domain.entities.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
}
