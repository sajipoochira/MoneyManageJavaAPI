package com.mecherycorp.MoneyManage.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mecherycorp.MoneyManage.DBoperations.AccountsTable;
import com.mecherycorp.MoneyManage.model.Accounts;
@Path("accounts")
public class accountResponse {
	AccountsTable acctbl = new AccountsTable();
	
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Accounts> getAccounts()
		{
			
			return acctbl.getAccounts();
				
		}
				
		
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public String addAccount(Accounts account){
				
			return acctbl.addAccount(account);
				
		}
		
		
		
}
