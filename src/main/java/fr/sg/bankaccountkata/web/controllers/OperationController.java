package fr.sg.bankaccountkata.web.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.sg.bankaccountkata.configuration.SwaggerConfig;
import fr.sg.bankaccountkata.dtos.OperationDto;
import fr.sg.bankaccountkata.entities.Operation;
import fr.sg.bankaccountkata.services.IOperationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { SwaggerConfig.BANK_ACCOUNT })
@RestController
public class OperationController {

    @Autowired
    private IOperationService operationService;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @ApiOperation(value = "Deposit money in account")
    @GetMapping(value="/operations/diposit")
    public ResponseEntity<OperationDto> dipositMoney(@RequestParam String accountNumber, @RequestParam Double amount) {
    	Operation operation = operationService.deposit(accountNumber, amount);
    	return ResponseEntity.ok(modelMapper.map(operation, OperationDto.class));
    }
    
    @ApiOperation(value = "Withdraw money from the account")
    @GetMapping(value="/operations/withdraw")
    public ResponseEntity<OperationDto> withdrawMoney(@RequestParam String accountNumber, @RequestParam Double amount) {
    	Operation operation = operationService.withdraw(accountNumber, amount);
		return ResponseEntity.ok(modelMapper.map(operation, OperationDto.class));
    }

    @ApiOperation(value = "See your bank account operations history!")
    @GetMapping(value="/operations/allOperations")
    public List<OperationDto> getAllOperations(@RequestParam String accountNumber) {
    	
    	List<Operation> operations = operationService.allOperations(accountNumber);
    	
    	return operations
                .stream()
                .map(operation -> modelMapper.map(operation, OperationDto.class))
                .collect(Collectors.toList());
    }
}