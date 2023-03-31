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

import com.example.spring.model.Credit;
import com.example.spring.service.CreditService;

@RestController
public class CreditController {

	@Autowired
	private CreditService creditService;

	@GetMapping("/credit/")
	public List<Credit> getAllCredit() {
		return creditService.getAllCredit();
	}
	
	@GetMapping("/credit/{id}")
	public Credit getCreditByID(@PathVariable("id") int id) {
		return creditService.getCreditByID(id);
	}
	
	@PostMapping("/insertCredit/")
	public Credit insertNewCreditRecord(@RequestBody Credit credit) {	
		return creditService.insertNewCreditRecord(credit.getFrom_account(), credit.getTo_account(),
				credit.getAmount(), credit.getCredit_status());
	}
}
