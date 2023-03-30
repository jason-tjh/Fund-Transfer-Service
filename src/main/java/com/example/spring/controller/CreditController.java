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
	
//	@PostMapping("/insertCredit/")
//	public String insertNewCreditRecord(@RequestBody Credit credit) {
//		Credit newCredit = new Credit();
//		newCredit.setFrom_account(credit.getFrom_account());
//		newCredit.setTo_account(credit.getTo_account());
//		newCredit.setAmount(credit.getAmount());
//		newCredit.setCredit_status(credit.getCredit_status());
//		newCredit.setTransaction_timestamp(LocalTime.now());
//		newCredit.setRecord_date_timestamp(LocalDate.now());
//		
//		creditService.insertNewCreditRecord(newCredit);
//		return "New credit record created";
//	}
}
