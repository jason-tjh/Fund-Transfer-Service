package com.example.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return accountRepository.findById(id).get();
	}
	
	public Account updateAccount(Account account) {
		return accountRepository.save(account);
	}
	
	public double checkBalanceByID(int id) {
		return accountRepository.findById(id).get().getBalance();
	}
	
	public double checkLimitByID(int id) {
		return accountRepository.findById(id).get().getAccount_limit();
	}
	
	public Account updateBalanceAfterTransfer(int id, double balanceAfterTransfer) {
		Account existingAccount = getAccountByID(id);
		existingAccount.setBalance(balanceAfterTransfer);
		
		return accountRepository.save(existingAccount);
	}
}
