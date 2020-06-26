package com.mecherycorp.MoneyManage.model;

public class BalanceSheet {
	private float Total_income;
	private float Total_expence;
	private float Balance;
	
	
	
	public BalanceSheet() {
		super();
		// TODO Auto-generated constructor stub
	}



	public BalanceSheet(float total_income, float total_expence, float balance) {
		super();
		Total_income = total_income;
		Total_expence = total_expence;
		Balance = balance;
	}



	public float getTotal_income() {
		return Total_income;
	}



	public void setTotal_income(float total_income) {
		Total_income = total_income;
	}



	public float getTotal_expence() {
		return Total_expence;
	}



	public void setTotal_expence(float total_expence) {
		Total_expence = total_expence;
	}



	public float getBalance() {
		return Balance;
	}



	public void setBalance(float balance) {
		Balance = balance;
	}


	
	

}
