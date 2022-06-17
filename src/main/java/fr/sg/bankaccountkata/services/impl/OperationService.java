package fr.sg.bankaccountkata.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.sg.bankaccountkata.entities.BankAccount;
import fr.sg.bankaccountkata.entities.Operation;
import fr.sg.bankaccountkata.enums.OperationType;
import fr.sg.bankaccountkata.repositories.BankAccountRepository;
import fr.sg.bankaccountkata.repositories.OperationRepository;
import fr.sg.bankaccountkata.services.IOperationService;

@Service
public class OperationService implements IOperationService {

    @Autowired
    OperationRepository operationRepository;

    @Autowired
    BankAccountRepository bankAccountRepository;

    
	@Override
	public Operation deposit(String accountCode, Double amount) {
		BankAccount bankAccount = bankAccountRepository.findByAccountCode(accountCode);
		if(bankAccount == null) 
			throw new RuntimeException("Compte introuvable");
		Operation operation = new Operation(OperationType.DEPOSIT, amount);
		operation.setDate(LocalDateTime.now());
		operation.setBankAccount(bankAccount);
		operationRepository.save(operation);
		bankAccount.setBalance(bankAccount.getBalance() + amount);
		bankAccountRepository.save(bankAccount); 
		return operation;
	}

	@Override
	public Operation withdraw(String accountCode, Double amount) {
		BankAccount bankAccount = bankAccountRepository.findByAccountCode(accountCode);
		if(bankAccount == null) 
				throw new RuntimeException("Compte introuvable");
		Double overdraft = bankAccount.getOverdraft();
		if(bankAccount.getBalance() + overdraft < amount)
			throw new RuntimeException("Solde insuffisant");
		Operation operation = new Operation(OperationType.WITHDRAWAL, amount);
		operation.setBankAccount(bankAccount);
		operationRepository.save(operation);
		bankAccount.setBalance(bankAccount.getBalance() - amount);
		bankAccountRepository.save(bankAccount);
		return operation;
	}
	
	@Override
	public List<Operation> allOperations(String accountCode) {
		
		return operationRepository.allOperation(accountCode);
	}
	
}
