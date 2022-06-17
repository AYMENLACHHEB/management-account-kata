package fr.sg.bankaccountkata.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import fr.sg.bankaccountkata.entities.BankAccount;
import fr.sg.bankaccountkata.entities.Operation;
import fr.sg.bankaccountkata.repositories.BankAccountRepository;
import fr.sg.bankaccountkata.repositories.OperationRepository;
import fr.sg.bankaccountkata.services.impl.OperationService;

@RunWith(SpringRunner.class)
@SpringBootTest
class OperationServiceTest {

	@Autowired
    OperationService operationService;

    @MockBean
    OperationRepository operationRepository;

    @MockBean
    BankAccountRepository bankAccountRepository;

    @BeforeEach
    void setUp() {

        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(1000d);
        bankAccount.setOverdraft(100d);
        bankAccount.setAccountCode("FR763000212115");

        Operation operation = new Operation();
        operation.setBankAccount(bankAccount);

        when(bankAccountRepository.findById(anyInt()))
                .thenReturn(bankAccount);
        
        when(bankAccountRepository.findByAccountCode(anyString()))
        		.thenReturn(bankAccount);

        when(bankAccountRepository.save(any(BankAccount.class)))
                .thenReturn(bankAccount);

        when(operationRepository.save(any(Operation.class)))
                .thenReturn(operation);

    }

    @Test
    void deposit() {
        Operation deposit = operationService.deposit("FR763000212115", 100d);
        assertNotNull(deposit);
    }
    
    @Test
    void deposit_with_compte_introuvable() {
    	RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
        	operationService.deposit(null, 100d);
    	});
    	assertEquals("Compte introuvable", thrown.getMessage());
    }

    @Test
    void withdraw() {
        Operation withdraw = operationService.withdraw("FR763000212115", 100d);
        assertNotNull(withdraw);
    }
    
    @Test
    void withdraw_with_compte_introuvable() {
    	RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            operationService.withdraw(null, 100d);
    	});
    	assertEquals("Compte introuvable", thrown.getMessage());
    }
    
    @Test
    void withdraw_with_solde_insuffisant() {
    	RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            operationService.withdraw("FR763000212115", 2000d);
    	});
    	assertEquals("Solde insuffisant", thrown.getMessage());
    }
}