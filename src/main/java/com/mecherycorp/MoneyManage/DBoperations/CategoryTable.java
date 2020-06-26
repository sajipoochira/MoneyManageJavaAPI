package com.mecherycorp.MoneyManage.DBoperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mecherycorp.MoneyManage.model.ExpenceCatogery;
import com.mecherycorp.MoneyManage.model.IncomeCatogery;

public class CategoryTable {
	Connection con = null;

	public CategoryTable() {
		// TODO Auto-generated constructor stub
	}

	public List<IncomeCatogery> getIncomeCategory()
	{
		
String query = "SELECT * FROM incomecatogery";
		
		ArrayList<IncomeCatogery> incomeCatogeryList = new ArrayList<>();
		Statement stmt=null;
		try {
		con = DBConnect.Connect();
		
				
			stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			 while(rs.next())
			 {
				 int Id = rs.getInt(1);
				 String CatogeryName = rs.getString(2);
				 IncomeCatogery incomeCatogery = new IncomeCatogery(Id,CatogeryName);
				 incomeCatogeryList.add(incomeCatogery);	
				 
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
		
		return incomeCatogeryList;
		
		
		
	}
	
	public List<ExpenceCatogery> getExpenceCategory()
	{
		
String query = "SELECT * FROM expencecatogery";
		
		ArrayList<ExpenceCatogery> expenceCatogeryList = new ArrayList<>();
		Statement stmt=null;
		try {
		con = DBConnect.Connect();
		
				
			stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			 while(rs.next())
			 {
				 int Id = rs.getInt(1);
				 String CatogeryName = rs.getString(2);
				 String CatType = rs.getString(3);
				 ExpenceCatogery expenceCatogery = new ExpenceCatogery(Id,CatogeryName,CatType);
				 expenceCatogeryList.add(expenceCatogery);	 
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
		
		return expenceCatogeryList;
		
		
		
	}

	public String addIncomeCatogery(IncomeCatogery incomeCatogery) {
		// TODO Auto-generated method stub
		
		con = DBConnect.Connect();
		String Sql = "INSERT INTO `incomecatogery` (`CatogaryName`) VALUES ('"+incomeCatogery.getCatogeryName()+"');";
		PreparedStatement stmt=null;
		try {
			stmt = con.prepareStatement(Sql);
			
			stmt.execute();
			
			
			
			
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

	public String addExpenceCatogery(ExpenceCatogery expenceCatogery) {
		
		con = DBConnect.Connect();
		String Sql = "INSERT INTO `expencecatogery` (`CatogaryName`) VALUES ('"+expenceCatogery.getCatogeryName()+"');";
		PreparedStatement stmt=null;
		try {
			stmt = con.prepareStatement(Sql);
			
			stmt.execute();
			
			
			
			
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
	
	
	
}
