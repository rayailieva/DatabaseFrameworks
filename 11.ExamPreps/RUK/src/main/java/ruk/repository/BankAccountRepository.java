package ruk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ruk.domain.entities.BankAccount;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

    Optional<BankAccount> findByAccountNumber(String number);
}
