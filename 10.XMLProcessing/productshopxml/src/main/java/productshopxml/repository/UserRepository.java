package productshopxml.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import productshopxml.domain.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("" +
            "SELECT u FROM productshopxml.domain.entities.User AS u " +
            "JOIN productshopxml.domain.entities.Product AS p ON p.seller.id = u.id " +
            "WHERE p.buyer IS NOT NULL " +
            "GROUP BY u.id " +
            "ORDER BY u.lastName, u.firstName")
    List<User> getAllBySellContainsProductBuyer();

}
