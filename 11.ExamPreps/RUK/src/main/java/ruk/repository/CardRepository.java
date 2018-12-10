package ruk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ruk.domain.entities.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
}
