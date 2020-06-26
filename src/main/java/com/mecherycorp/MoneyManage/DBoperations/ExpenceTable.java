package com.mecherycorp.MoneyManage.DBoperations;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.mecherycorp.MoneyManage.businesslogic.Calculations;
import com.mecherycorp.MoneyManage.model.Accounts;
import com.mecherycorp.MoneyManage.model.Expence;
import com.mecherycorp.MoneyManage.model.ExpenceCatogery;
import com.mecherycorp.MoneyManage.model.monthTodate;


public class ExpenceTable {
	
Connection con=null;
AccountsTable acctbl = new AccountsTable();	
CategoryTable catTable = new CategoryTable();
Statement stmt = null;
	public List<Expence> getExpence()
	{
		String query = "SELECT * FROM expence";
		
		ArrayList<Expence> expenceList = new ArrayList<>();
		try {
		con = DBConnect.Connect();
		
				
			stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			 while(rs.next())
			 {
				 int Id = rs.getInt(1);
				 Date date = rs.getDate(2);
				 String expCat = rs.getString(3);
				 String expDec = rs.getString(4);
				 float amount = rs.getFloat(5);
				 String account = rs.getString(6);
				 Expence expence = new Expence(Id,date,expCat,expDec,amount,account);
				 expenceList.add(expence);	 
			 }
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		    //It's important to close the statement when you are done with it
		    try {
				stmt.close();
				con.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
				
		return expenceList;
		

}
	
	
	public List<Expence> getExpence(int month,int year)
	{
		String query = "SELECT * FROM expence";
		Statement stmt = null;
		
		ArrayList<Expence> expenceList = new ArrayList<>();
		try {
		con = DBConnect.Connect();
		
		monthTodate mtd = Calculations.getFirstandLastDay(month, year);	
			stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			 while(rs.next())
			 {
				 int Id = rs.getInt(1);
				 Date date = rs.getDate(2);
				 String expCat = rs.getString(3);
				 String expDec = rs.getString(4);
				 float amount = rs.getFloat(5);
				 String account = rs.getString(6);
				 LocalDate CovDate = date.toLocalDate();
				
				 if (CovDate.compareTo(mtd.getLastDay())<=0 && CovDate.compareTo(mtd.getFirstDay())>=0)
				 {     
				 Expence expence = new Expence(Id,date,expCat,expDec,amount,account);
				 expenceList.add(expence);	 
				 }
			 }
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		    //It's important to close the statement when you are done with it
		    try {
				stmt.close();
				con.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
				
		return expenceList;
		

}
	
	
public String addExpence(Expence  expence) {
		
		con = DBConnect.Connect();
		String Sql = "INSERT INTO `expence` (`Date`, `ExpenceCatogery`, `ExpenceDescription`, `Amount`, `Account`) VALUES ('"+expence.getDate()+"', '"+expence.getExpence_Catogory()+"' ,'"+expence.getExpence_Description()+"' ,"+expence.getExpence_Amount()+" ,'"+expence.getAccount()+"');";
		PreparedStatement stmt= null;
		try {
			
			stmt = con.prepareStatement(Sql);
			stmt.execute();
			
			List<Accounts> Acclist = acctbl.getAccounts();
			
			Boolean exists = false;
			for (Accounts acc : Acclist) {
				if ((acc.getAccountName()).equals(expence.getAccount()) ) {
					exists = true;
				}
			}
			
			if(!exists) {
				
				Accounts acc = new Accounts(1,expence.getAccount(),"QAR");
				
				acctbl.addAccount(acc);
			}
			List<ExpenceCatogery> excatlist = catTable.getExpenceCategory();
			
			Boolean Catexists = false;
			for (ExpenceCatogery cat : excatlist) {
				if ((cat.getCatogeryName()).equals(expence.getExpence_Catogory()) ) {
					Catexists = true;
				}
			}
			
			if(!Catexists) {
				
				ExpenceCatogery catentry = new ExpenceCatogery(1,expence.getExpence_Catogory(),"Normal");
				
				catTable.addExpenceCatogery(catentry);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		    //It's important to close the statement when you are done with it
		    try {
				stmt.close();
				con.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "Successfully Added";
		
	}


public List<Expence> getExpence(String catName) {
	
	String query = "SELECT * FROM expence";
	Statement stmt = null;
	
	ArrayList<Expence> expenceList = new ArrayList<>();
	try {
	con = DBConnect.Connect();
	
	
		stmt = con.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		 while(rs.next())
		 {
			 int Id = rs.getInt(1);
			 Date date = rs.getDate(2);
			 String expCat = rs.getString(3);
			 String expDec = rs.getString(4);
			 float amount = rs.getFloat(5);
			 String account = rs.getString(6);
			
			
			 if (expCat.equals(catName))
			 {     
			 Expence expence = new Expence(Id,date,expCat,expDec,amount,account);
			 expenceList.add(expence);	 
			 }
		 }
		
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
	    //It's important to close the statement when you are done with it
	    try {
			stmt.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
			
	return expenceList;
	
	}	

}
