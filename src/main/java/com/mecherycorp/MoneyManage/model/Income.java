package com.mecherycorp.MoneyManage.model;

import java.sql.Date;
import java.time.LocalDate;

public class Income {
	
	private int Id;
	private Date date ;
	private String Income_Catogory;
	private String Income_Description;
	private float Income_Amount;
	private String Account ;
	
		
	
	public Income() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Income(int id,Date date, String income_Catogory, String income_Description, float income_Amount,
			String account) {
		super();
		this.Id = id;
		this.date = date;
		this.Income_Catogory = income_Catogory;
		this.Income_Description = income_Description;
		this.Income_Amount = income_Amount;
		this.Account = account;
	}
	
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public LocalDate getDate() {
		return date.toLocalDate();
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getIncome_Catogory() {
		return Income_Catogory;
	}
	public void setIncome_Catogory(String income_Catogory) {
		Income_Catogory = income_Catogory;
	}
	public String getIncome_Description() {
		return Income_Description;
	}
	public void setIncome_Description(String income_Description) {
		Income_Description = income_Description;
	}
	public float getIncome_Amount() {
		return Income_Amount;
	}
	public void setIncome_Amount(float income_Amount) {
		Income_Amount = income_Amount;
	}
	public String getAccount() {
		return Account;
	}
	public void setAccount(String account) {
		Account = account;
	}
	
	
	

}
