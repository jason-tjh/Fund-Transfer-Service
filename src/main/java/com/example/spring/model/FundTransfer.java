package com.example.spring.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class FundTransfer {

	@NotNull
	private int from_account;
	@NotNull
	private int to_account;
	@NotNull
	@Min(value = 0, message = "The minimum transfer amount should be more than 0.")
	private double transfer_amount;
	
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
	public double getTransfer_amount() {
		return transfer_amount;
	}
	public void setTransfer_amount(double transfer_amount) {
		this.transfer_amount = transfer_amount;
	}

	
}
