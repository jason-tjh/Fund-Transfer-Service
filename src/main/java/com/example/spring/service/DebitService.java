package com.example.spring.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.exception.NegativeAmountException;
import com.example.spring.model.Debit;
import com.example.spring.repository.DebitRepository;

@Service
public class DebitService {

	@Autowired
	DebitRepository debitRepository;
	
	public List<Debit> getAllDebit() {
		return debitRepository.findAll();
	}
	
	public Debit getDebitByID(int id) {
		return debitRepository.findById(id).get();
	}
	
	public Debit insertNewDebitRecord(int accountFrom, int accountTo, double amount, String status) {
		
		if (amount < 0) {
			throw new NegativeAmountException("400", "Amount cannot be negative.");
		}
		Debit newDebit = new Debit();
		newDebit.setFrom_account(accountFrom);
		newDebit.setTo_account(accountTo);
		newDebit.setAmount(amount);
		newDebit.setDebit_status(status);
		newDebit.setTransaction_timestamp(LocalTime.now());
		newDebit.setRecord_date_timestamp(LocalDate.now());
		
		return debitRepository.save(newDebit);
	}
}
