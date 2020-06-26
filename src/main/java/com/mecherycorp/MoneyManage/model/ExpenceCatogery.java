package com.mecherycorp.MoneyManage.model;

public class ExpenceCatogery {
	
	private int id ;
	private String CatogeryName ;
	private String CatType;
	
		
	public String getCatType() {
		return CatType;
	}


	public void setCatType(String catType) {
		CatType = catType;
	}


	public ExpenceCatogery() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public ExpenceCatogery(int id, String catogeryName, String CatType) {
		super();
		this.id = id;
		this.CatogeryName = catogeryName;
		this.CatType = CatType;
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
