package ruk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ruk.domain.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findByFirstNameAndLastName(String fistName, String lastName);
}
