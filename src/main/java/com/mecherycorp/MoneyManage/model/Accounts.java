package com.mecherycorp.MoneyManage.model;



public class Accounts {
	private int id;
	private String AccountName;
	private String Currency;
	
	
	
	
	
	public Accounts() {
		super();
		// TODO Auto-generated constructor stub
	}





	public Accounts(int id, String accountName, String currency) {
		super();
		this.id = id;
		AccountName = accountName;
		Currency = currency;
	}





	public int getId() {
		return id;
	}





	public void setId(int id) {
		this.id = id;
	}





	public String getAccountName() {
		return AccountName;
	}





	public void setAccountName(String accountName) {
		AccountName = accountName;
	}





	public String getCurrency() {
		return Currency;
	}



	public void setCurrency(String currency) {
		Currency = currency;
	}



}
