package com.mecherycorp.MoneyManage.api;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.mecherycorp.MoneyManage.businesslogic.uploadData;;

@Path("upload")
public class UploadResponse {

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String uploadData(String data) {
		
		uploadData ud = new uploadData();
	ud.insertData(data);
		
		return "Success";
		
		
	}

	
	
}
