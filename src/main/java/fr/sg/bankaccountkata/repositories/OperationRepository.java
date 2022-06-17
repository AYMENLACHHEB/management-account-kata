package fr.sg.bankaccountkata.repositories;

import fr.sg.bankaccountkata.entities.Operation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
	
	@Query("SELECT t FROM Operation t WHERE t.bankAccount.accountCode=:accountCode")
	List<Operation> allOperation(@Param("accountCode") String accountCode);
}
