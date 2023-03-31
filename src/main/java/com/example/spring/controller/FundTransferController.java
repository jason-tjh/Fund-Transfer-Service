package com.example.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.exception.OverdraftException;
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

		if (transferAmount > debitAccountBalance || transferAmount > accountLimit) {
			throw new OverdraftException("400", "Transaction cannot be completed, please try again.");
		}
		
		insertNewRecord("debit", debitAccountID, creditAccountID, transferAmount, "PENDING");
		double newDebitBalance = debitAccountBalance - transferAmount;
		updateBalanceAfterTransfer(debitAccountID, newDebitBalance);
		
		double creditAccountBalance = accountService.checkBalanceByID(creditAccountID);
		double newCreditBalance = creditAccountBalance + transferAmount;
		updateBalanceAfterTransfer(creditAccountID, newCreditBalance);

		insertNewRecord("credit", debitAccountID, creditAccountID, transferAmount, "RECEIVED");
		insertNewRecord("debit", debitAccountID, creditAccountID, transferAmount, "SUCCESS");
		
		return new ResponseEntity<>(
				String.format("The amount $%.2f has been transferred successfully.", transferAmount),
				HttpStatus.OK);
	}
	
	
	private void updateBalanceAfterTransfer(int targetAccount, double newBalance) {
		accountService.updateBalanceAfterTransfer(targetAccount, newBalance);
	}
	
	private void insertNewRecord(String type, int debitID,
			int creditID,double transferAmount, String status) {
		if (type.equalsIgnoreCase("debit")) {
			debitService.insertNewDebitRecord(debitID, creditID, transferAmount, status);
		}
		else if (type.equalsIgnoreCase("credit")) {
			creditService.insertNewCreditRecord(debitID, creditID, transferAmount, status);
		}
		else {
			return;
		}
	}
}
