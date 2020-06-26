package com.mecherycorp.MoneyManage.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mecherycorp.MoneyManage.DBoperations.IncomeTable;
import com.mecherycorp.MoneyManage.model.Income;

@Path("income")
public class incomeResponse {
	IncomeTable inctble = new IncomeTable();	
@GET
@Produces(MediaType.APPLICATION_JSON)
public List<Income> getIncome(){
	
	return inctble.getIncome();
		
}

@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public String addIncome(Income income){
		
	return inctble.addIncome(income);
		
}

@GET
@Path("/{month}/{year}")
@Produces(MediaType.APPLICATION_JSON)
public List<Income> getIncome(@PathParam("month") int month,@PathParam("year") int year){
	
	return inctble.getIncome(month,year);
		
}


@GET
@Path("/category/{catName}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public List<Income> getIncome(@PathParam("catName") String catName){
	
	return inctble.getIncome(catName);
		
}

}
