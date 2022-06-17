package fr.sg.bankaccountkata.services;

import java.util.List;

import fr.sg.bankaccountkata.entities.BankAccount;
import fr.sg.bankaccountkata.entities.Operation;


public interface IOperationService {

	Operation deposit(String accountNumber, Double amount);
	
	List<Operation> allOperations(String accountCode);

	Operation withdraw(String accountNumber, Double amount);
}
