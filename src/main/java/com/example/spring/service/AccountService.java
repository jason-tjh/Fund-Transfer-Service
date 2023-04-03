package com.example.spring.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.controller.FundTransferController;
import com.example.spring.exception.NegativeAmountException;
import com.example.spring.exception.NoRecordFoundException;
import com.example.spring.model.Account;
import com.example.spring.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FundTransferController.class);
		
	public List<Account> getAllAccounts() {
		return accountRepository.findAll();
	}
	
	public Account getAccountByID(int id) {
		return accountRepository.findById(id)
				.orElseThrow(() -> new NoRecordFoundException("400", 
								String.format("Account with id %d not found", id)));
	}
	
	public Account updateAccount(Account account) {
		if (account.getBalance() < 0) {
			throw new NegativeAmountException("400", "Account balance cannot be negative.");
		}
		return accountRepository.save(account);
	}
	
	public double checkBalanceByID(int id) {
		return getAccountByID(id).getBalance();
	}
	
	public double checkLimitByID(int id) {
		return getAccountByID(id).getAccount_limit();
	}
	
	public Account updateBalanceAfterTransfer(int id, double balanceAfterTransfer) {
		Account existingAccount = getAccountByID(id);
		existingAccount.setBalance(balanceAfterTransfer);
		LOGGER.info(String.format("New balance updated for Account %d: $%.2f", id, balanceAfterTransfer));
		
		return accountRepository.save(existingAccount);
	}
}
