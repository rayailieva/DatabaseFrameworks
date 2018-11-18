package softuni.softunigamestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.softunigamestore.domain.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByEmail(String email);
}
