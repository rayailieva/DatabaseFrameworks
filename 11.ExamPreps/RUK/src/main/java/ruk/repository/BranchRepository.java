package ruk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ruk.domain.entities.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {

    Branch findOneByName(String name);
}
