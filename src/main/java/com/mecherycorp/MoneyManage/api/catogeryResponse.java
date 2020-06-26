package com.mecherycorp.MoneyManage.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mecherycorp.MoneyManage.DBoperations.CategoryTable;
import com.mecherycorp.MoneyManage.model.ExpenceCatogery;
import com.mecherycorp.MoneyManage.model.IncomeCatogery;

@Path("category")
public class catogeryResponse {
	
	CategoryTable ct = new CategoryTable();
	
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String defaultResponse() {
		
		return "Enter /income for Income Category and /expence for Expence Category";
	}


	@Path("income")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<IncomeCatogery> getIncomeCatogery()
	{
		
		return ct.getIncomeCategory();
			
	}
	
	
	@Path("expence")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ExpenceCatogery> getexpenceCatogery()
	{
		return ct.getExpenceCategory();
			
	}
	@Path("income")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addIncomeCatogery(IncomeCatogery incomeCatogery){
			
		return ct.addIncomeCatogery(incomeCatogery);
			
	}
	@Path("expence")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addExpenceCatogery(ExpenceCatogery expenceCatogery){
			
		return ct.addExpenceCatogery(expenceCatogery);
			
	}
}
