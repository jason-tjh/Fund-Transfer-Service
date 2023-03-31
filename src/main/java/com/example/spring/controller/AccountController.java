package com.example.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.model.Account;
import com.example.spring.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@GetMapping("/account/")
	public List<Account> getAllAccounts() {
		return accountService.getAllAccounts();
	}
	
	@GetMapping("/account/{id}")
	public Account getAccountByID(@PathVariable("id") int id) {
		return accountService.getAccountByID(id);
	}
	
	@GetMapping("/accountBalance/{id}")
	public double checkBalanceByID(@PathVariable("id") int id) {
		return accountService.checkBalanceByID(id);
	}
	
	@GetMapping("/accountLimit/{id}")
	public double checkLimitByID(@PathVariable("id") int id) {
		return accountService.checkLimitByID(id);
	}
	
	@PutMapping("/updateAccount/{id}")
	public Account updateAccountBalanceByID(@RequestBody Account account, @PathVariable("id") int id) {
		Account existingAccount = accountService.getAccountByID(id);
		existingAccount.setBalance(account.getBalance());
		
		return accountService.updateAccount(existingAccount);
	}
	
}
