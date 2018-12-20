package hiberspring.repository;

import hiberspring.domain.entities.Employee;
import hiberspring.domain.entities.EmployeeCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByCard(EmployeeCard card);

    @Query( "" +
            "SELECT e " +
            "FROM hiberspring.domain.entities.Employee e " +
            "ORDER BY concat(e.firstName, ' ' , e.lastName) ASC , LENGTH(e.position) DESC "
    )
    List<Employee> exportEmployees();
}
