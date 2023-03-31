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
	
	@PostMapping("/insertDebit/")
	public Debit insertNewDebitRecord(@RequestBody Debit debit) {		
		return debitService.insertNewDebitRecord(debit.getFrom_account(), debit.getTo_account(),
						debit.getAmount(), debit.getDebit_status());
	}
}
