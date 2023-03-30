package com.example.spring.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="credit")
public class Credit {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int transaction_id;
	private int from_account;
	private int to_account;
	private double amount;
	private String credit_status;
	private LocalTime transaction_timestamp;
	private LocalDate record_date_timestamp;
	
	public int getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}
	public int getFrom_account() {
		return from_account;
	}
	public void setFrom_account(int from_account) {
		this.from_account = from_account;
	}
	public int getTo_account() {
		return to_account;
	}
	public void setTo_account(int to_account) {
		this.to_account = to_account;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getCredit_status() {
		return credit_status;
	}
	public void setCredit_status(String credit_status) {
		this.credit_status = credit_status;
	}
	public LocalTime getTransaction_timestamp() {
		return transaction_timestamp;
	}
	public void setTransaction_timestamp(LocalTime transaction_timestamp) {
		this.transaction_timestamp = transaction_timestamp;
	}
	public LocalDate getRecord_date_timestamp() {
		return record_date_timestamp;
	}
	public void setRecord_date_timestamp(LocalDate record_date_timestamp) {
		this.record_date_timestamp = record_date_timestamp;
	}
	
	
}
