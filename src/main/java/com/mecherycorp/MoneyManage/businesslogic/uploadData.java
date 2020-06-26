package com.mecherycorp.MoneyManage.businesslogic;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.mecherycorp.MoneyManage.DBoperations.AccountsTable;
import com.mecherycorp.MoneyManage.DBoperations.IncomeTable;
import com.mecherycorp.MoneyManage.model.Accounts;
import com.mecherycorp.MoneyManage.model.Income;
public class uploadData {

	public uploadData() {
		// TODO Auto-generated constructor stub
	}

	public String insertData(String data){
		IncomeTable intble = new IncomeTable();
		List<Income> entry = texttoIncome(data);
		
		for(int counter=0; counter < entry.size();counter++ )
		{
		intble.addIncome(entry.get(counter));
		}
			
		
		return data;
		
			}
	
	public List<Income> texttoIncome(String data){

		String[] lines=data.split("\n");
		AccountsTable acctbl = new AccountsTable();
		
		ArrayList<Income> result = new ArrayList<Income>();
		try {
		
		for(int i=1;i<lines.length;i++){
			
			

		    
		    String[] currentline=lines[i].split(",");
		    
		    //Income(Date date, String income_Catogory, String income_Description, float income_Amount,	String account)
		    
		   
			
			Date dt = changeDateFm(currentline[0]);
			
		    Float amount = Float.parseFloat(currentline[3]);
		   // System.out.println(dt.toString());
		    String income_Catogory = currentline[2];
		    String income_Description = currentline[7];
		    String account = currentline[1];
if(amount<0) {
		Income inc = new Income(1,dt,income_Catogory,income_Description,amount,account);
		
		

		    result.add(inc);
}

		}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return result; 
		}
	
	public Date changeDateFm(String date) {
		
		final String OLD_FORMAT = "dd/MM/yyyy";
		final String NEW_FORMAT = "yyyy-MM-dd";
		String oldDateString = date;
		String newDateString;
		LocalDate Newlocaldate;
		Date Newdate = null;
		try {
		

		SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
		Date d = (Date) sdf.parse(oldDateString);
		sdf.applyPattern(NEW_FORMAT);
		newDateString = sdf.format(d);
		ZoneId defaultZoneId = ZoneId.systemDefault();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
		Newlocaldate = LocalDate.parse(newDateString, formatter);
		
		Newdate = (Date) Date.from(Newlocaldate.atStartOfDay(defaultZoneId).toInstant());
		}catch(Exception e) {
			
		}
		return Newdate;
		
						
	}
}
