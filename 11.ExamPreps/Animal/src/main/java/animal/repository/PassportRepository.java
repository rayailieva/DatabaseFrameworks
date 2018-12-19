package animal.repository;

import animal.domain.entities.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassportRepository extends JpaRepository<Passport, String> {

    Optional<Passport> findOneBySerialNumber(String number);
}
