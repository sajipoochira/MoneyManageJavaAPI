package com.mecherycorp.MoneyManage.model;

import java.sql.Date;
import java.time.LocalDate;

public class Expence {

	
	private int Id;
	private Date date ;
	private String Expence_Catogory;
	private String Expence_Description;
	private float Expence_Amount;
	private String Account ;
	
	
	
	
	
	public Expence() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Expence(int id, Date date, String expence_Catogory, String expence_Description, float expence_Amount,
			String account) {
		super();
		Id = id;
		this.date = date;
		this.Expence_Catogory = expence_Catogory;
		this.Expence_Description = expence_Description;
		this.Expence_Amount = expence_Amount;
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
	public String getExpence_Catogory() {
		return Expence_Catogory;
	}
	public void setExpence_Catogory(String expence_Catogory) {
		Expence_Catogory = expence_Catogory;
	}
	public String getExpence_Description() {
		return Expence_Description;
	}
	public void setExpence_Description(String expence_Description) {
		Expence_Description = expence_Description;
	}
	public float getExpence_Amount() {
		return Expence_Amount;
	}
	public void setExpence_Amount(float expence_Amount) {
		Expence_Amount = expence_Amount;
	}
	public String getAccount() {
		return Account;
	}
	public void setAccount(String account) {
		Account = account;
	}
	
	
	
	
	
}
