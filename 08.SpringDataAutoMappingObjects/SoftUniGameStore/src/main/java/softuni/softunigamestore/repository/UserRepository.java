package softuni.softunigamestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.softunigamestore.domain.dtos.view.GameTitleAndPriceViewDto;
import softuni.softunigamestore.domain.entities.User;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
