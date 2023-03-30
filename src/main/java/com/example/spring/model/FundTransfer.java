package com.example.spring.model;
public class FundTransfer {
	
	private int from_account;
	private int to_account;
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
