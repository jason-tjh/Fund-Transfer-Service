package com.example.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.model.Account;
import com.example.spring.model.FundTransfer;
import com.example.spring.service.AccountService;
import com.example.spring.service.CreditService;
import com.example.spring.service.DebitService;

@RestController
public class FundTransferController {

	@Autowired
	AccountService accountService;
	
	@Autowired
	DebitService debitService;
	
	@Autowired
	CreditService creditService;
	
	@PostMapping("/fundTransfer/")
	public ResponseEntity<String> requestFundTransfer(@RequestBody FundTransfer fundTransfer) {
		
		double transferAmount = fundTransfer.getTransfer_amount();
		int debitAccountID = fundTransfer.getFrom_account();
		int creditAccountID = fundTransfer.getTo_account();
		
		double debitAccountBalance = accountService.checkBalanceByID(debitAccountID);
		double accountLimit = accountService.checkLimitByID(debitAccountID);

		// TODO: Add validation
		if (transferAmount < 0 ||
				transferAmount > debitAccountBalance ||
				transferAmount > accountLimit) {
			System.out.println("Error: " + transferAmount + " | " + debitAccountBalance + " | " + accountLimit);
		}
		
		debitService.insertNewDebitRecord(debitAccountID, creditAccountID, transferAmount, "PENDING");
		
		// TODO: Add delay?
		
		creditService.insertNewCreditRecord(debitAccountID, creditAccountID, transferAmount, "RECEIVED");
		double creditAccountBalance = accountService.checkBalanceByID(creditAccountID);
		double newCreditBalance = creditAccountBalance + transferAmount;
		accountService.updateBalanceAfterTransfer(creditAccountID, newCreditBalance);

		double newDebitBalance = debitAccountBalance - transferAmount;
		accountService.updateBalanceAfterTransfer(debitAccountID, newDebitBalance);
		
		return new ResponseEntity<>("ACCEPTED", HttpStatus.ACCEPTED);
	}
}
