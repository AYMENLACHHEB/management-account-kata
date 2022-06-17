package fr.sg.bankaccountkata.repositories;

import fr.sg.bankaccountkata.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    BankAccount findById(int id);
    
    BankAccount findByAccountCode(String accountCode);
}
