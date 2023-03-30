package com.example.spring.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.model.Debit;
import com.example.spring.service.DebitService;

@RestController
public class DebitController {

	@Autowired
	private DebitService debitService;
	
	@GetMapping("/debit/")
	public List<Debit> getAllDebit() {
		return debitService.getAllDebit();
	}
	
	@GetMapping("/debit/{id}")
	public Debit getDebitByID(@PathVariable("id") int id) {
		return debitService.getDebitByID(id);
	}
	
//	@PostMapping("/insertDebit/")
//	public String insertNewDebitRecord(@RequestBody Debit debit) {
//		Debit newDebit = new Debit();
//		newDebit.setFrom_account(debit.getFrom_account());
//		newDebit.setTo_account(debit.getTo_account());
//		newDebit.setAmount(debit.getAmount());
//		newDebit.setDebit_status(debit.getDebit_status());
//		newDebit.setTransaction_timestamp(LocalTime.now());
//		newDebit.setRecord_date_timestamp(LocalDate.now());
//		
//		debitService.insertNewDebitRecord(newDebit);
//		return "New debit record created";
//	}
}
