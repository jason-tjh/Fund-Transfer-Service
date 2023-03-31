package com.example.spring.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="account")
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int account_id;
	@NotNull
	@Size(max = 100)
	private String first_name;
	@NotNull
	@Size(max = 100)
	private String last_name;
	@NotNull
	@Min(value = 0, message = "The minimum account balance should be 0.")
	private double balance;
	@NotNull
	@Min(value = 0, message = "The minimum account limit should not be negative.")
	private double account_limit;
	@NotNull
	private Date date_created;
	
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getAccount_limit() {
		return account_limit;
	}
	public void setAccount_limit(double account_limit) {
		this.account_limit = account_limit;
	}
	public Date getDate_created() {
		return date_created;
	}
	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}

}
