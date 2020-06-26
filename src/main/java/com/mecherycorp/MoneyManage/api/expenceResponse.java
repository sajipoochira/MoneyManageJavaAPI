package com.mecherycorp.MoneyManage.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mecherycorp.MoneyManage.DBoperations.ExpenceTable;
import com.mecherycorp.MoneyManage.model.Expence;
@Path("expence")
public class expenceResponse {
	ExpenceTable exptble = new ExpenceTable();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Expence> getExpence(){
		
				
		return exptble.getExpence();
		
	}
	
	@GET
	@Path("/{month}/{year}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Expence> getExpence(@PathParam("month") int month,@PathParam("year") int year){
		
		return exptble.getExpence(month,year);
			
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addExpence(Expence expence){
			
		return exptble.addExpence(expence);
			
	}
	
	
	@GET
	@Path("/category/{catName}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Expence> getExpence(@PathParam("catName") String CatName){
		
		
		return exptble.getExpence(CatName);
		
		
		
	}
	
	
}
