package cardealer.repository;

import cardealer.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomersRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "SELECT c FROM cardealer.domain.entities.Customer AS c ORDER BY c.birthDate")
    List<Customer> getOrderedCustomers();


}
