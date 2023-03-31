package com.example.spring.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.model.Credit;
import com.example.spring.repository.CreditRepository;

@Service
public class CreditService {

	@Autowired
	CreditRepository creditRepository;
	
	public List<Credit> getAllCredit() {
		return creditRepository.findAll();
	}
	
	public Credit getCreditByID(int id) {
		return creditRepository.findById(id).get();
	}

	public Credit insertNewCreditRecord(int accountFrom, int accountTo, double amount, String status) {
		Credit newCredit = new Credit();
		newCredit.setFrom_account(accountFrom);
		newCredit.setTo_account(accountTo);
		newCredit.setAmount(amount);
		newCredit.setCredit_status(status);
		newCredit.setTransaction_timestamp(LocalTime.now());
		newCredit.setRecord_date_timestamp(LocalDate.now());
		
		return creditRepository.save(newCredit);
	}

}
