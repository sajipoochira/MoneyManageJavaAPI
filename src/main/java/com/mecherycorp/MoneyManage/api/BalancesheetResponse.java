package com.mecherycorp.MoneyManage.api;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mecherycorp.MoneyManage.businesslogic.Calculations;
import com.mecherycorp.MoneyManage.model.BalanceSheet;

@Path("balance")
public class BalancesheetResponse {
	
	Calculations calc = new Calculations();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public BalanceSheet getBalance()
	{
				
		return calc.getBalance();
		
	}
	
	@GET
	@Path("/{month}/{year}")
	@Produces(MediaType.APPLICATION_JSON)
	public BalanceSheet getBalance(@PathParam("month") int month, @PathParam("year") int year)
	{
				
		return calc.getBalance(month,year);
		
	}
	@GET
	@Path("/{month}/{year}/{excat}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getBalance(@PathParam("month") int month,@PathParam("excat") String excatname)
	{
		String mon =Integer.toString(month)	;	
		return mon + "  "+ excatname;
		
	}
	@GET
	@Path("/lastyear")
	@Produces(MediaType.APPLICATION_JSON)
	public List<BalanceSheet> getBalancelastyear()
	{
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int month = localDate.getMonthValue();
		int year = localDate.getYear();
		
		return calc.getBalancelastyear(month,year);
		
	}
}
