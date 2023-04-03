package com.example.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.exception.OverdraftException;
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
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FundTransferController.class);
	
	@PostMapping("/fundTransfer/")
	public ResponseEntity<String> requestFundTransfer(@RequestBody FundTransfer fundTransfer) {
		
		double transferAmount = fundTransfer.getTransfer_amount();
		int debitAccountID = fundTransfer.getFrom_account();
		int creditAccountID = fundTransfer.getTo_account();
		
		double debitAccountBalance = accountService.checkBalanceByID(debitAccountID);
		double accountLimit = accountService.checkLimitByID(debitAccountID);
		LOGGER.debug(String.format("Transfer amount: $%.2f | Balance: $%.2f | Limit: $%.2f.",
				transferAmount, debitAccountBalance, accountLimit));

		if (transferAmount > debitAccountBalance || transferAmount > accountLimit) {
			LOGGER.error(String.format("[400] Transfer amount of $%.2f exceeds the account balance/limit.", transferAmount));
			throw new OverdraftException("400", "Transaction cannot be completed, please try again.");
		}
		
		insertNewRecord("debit", debitAccountID, creditAccountID, transferAmount, "PENDING");
		LOGGER.debug(String.format("Insert new record into debit ('PENDING', '$%.2f')", transferAmount));
		double newDebitBalance = debitAccountBalance - transferAmount;
		updateBalanceAfterTransfer(debitAccountID, newDebitBalance);
		LOGGER.debug(String.format("Updated debit account [%d] balance: $%.2f", debitAccountID, newDebitBalance));
		
		double creditAccountBalance = accountService.checkBalanceByID(creditAccountID);
		double newCreditBalance = creditAccountBalance + transferAmount;
		updateBalanceAfterTransfer(creditAccountID, newCreditBalance);
		LOGGER.debug(String.format("Updated credit account [%d] balance: $%.2f", creditAccountID, newCreditBalance));

		insertNewRecord("credit", debitAccountID, creditAccountID, transferAmount, "RECEIVED");
		LOGGER.debug(String.format("Insert new record into credit ('RECEIVED', '$%.2f')", transferAmount));
		LOGGER.debug("Insert new record into debit (SUCCESS)");

		LOGGER.info("Fund transferred successfully.");
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
