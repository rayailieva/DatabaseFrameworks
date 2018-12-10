package ruk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ruk.domain.entities.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findOneByFirstNameAndLastName(String firstName, String lastName);

    @Query("" +
    "SELECT e "+
    "FROM ruk.domain.entities.Employee e " +
    "JOIN e.clients c " +
    "GROUP BY e " +
    "ORDER BY SIZE(e.clients) DESC, e.id ")
    List<Employee> getAllTopEmployees();
}
