package com.example.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.exception.NegativeAmountException;
import com.example.spring.exception.NoRecordFoundException;
import com.example.spring.model.Account;
import com.example.spring.repository.AccountRepository;

@Service
public class AccountService {

	
	@Autowired
	AccountRepository accountRepository;
	
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
		
		return accountRepository.save(existingAccount);
	}
}
