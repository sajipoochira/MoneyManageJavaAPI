package com.mecherycorp.MoneyManage.model;

public class IncomeCatogery {
	private int id ;
	private String CatogeryName ;
	
		
	
	public IncomeCatogery() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public IncomeCatogery(int id, String catogeryName) {
		super();
		this.id = id;
		this.CatogeryName = catogeryName;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCatogeryName() {
		return CatogeryName;
	}
	public void setCatogeryName(String catogeryName) {
		CatogeryName = catogeryName;
	}
	
	
	
	

}
