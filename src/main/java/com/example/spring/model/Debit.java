package com.example.spring.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="debit")
public class Debit {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int transaction_id;
	@NotNull
	private int from_account;
	@NotNull
	private int to_account;
	@NotNull
	@Min(value = 0, message = "The minimum debit amount should be more than 0.")
	private double amount;
	@NotNull
	@Size(max = 50)
	private String debit_status;
	@NotNull
	private LocalTime transaction_timestamp;
	@NotNull
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
	public String getDebit_status() {
		return debit_status;
	}
	public void setDebit_status(String debit_status) {
		this.debit_status = debit_status;
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
