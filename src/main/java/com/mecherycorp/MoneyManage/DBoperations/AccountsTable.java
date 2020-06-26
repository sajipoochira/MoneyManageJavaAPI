package com.mecherycorp.MoneyManage.DBoperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mecherycorp.MoneyManage.model.Accounts;

public class AccountsTable {
	Connection con=null;

	
	public List<Accounts> getAccounts()
	{
		String query = "SELECT * FROM accounts";
		
		ArrayList<Accounts> AccountsList = new ArrayList<>();
		Statement stmt = null;
		try {
		con = DBConnect.Connect();
		
				
			stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			 while(rs.next())
			 {
				 int Id = rs.getInt(1);
				 String accountname = rs.getString(2);
				 String currency = rs.getString(3);
				 Accounts acc = new Accounts(Id,accountname,currency);
				 
				 AccountsList.add(acc);	 
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
		
		
				
		return AccountsList;
		
		
	}
public String addAccount(Accounts Account) {
		
		con = DBConnect.Connect();
		String Sql = "INSERT INTO `accounts` (`AccountName`, `Currency`) VALUES ('"+Account.getAccountName()+"', '"+Account.getCurrency()+"');";
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
public Accounts getAccounts(String accountName) {
	
	String query = "SELECT * FROM accounts WHERE AccountName = "+ "'"+accountName+"'";
	Accounts acc = null;
	
	Statement stmt = null;
	try {
	con = DBConnect.Connect();
	
			
		stmt = con.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		rs.next();
			 int Id = rs.getInt(1);
			 String accountname = rs.getString(2);
			 
			 String currency = rs.getString(3);
			 acc = new Accounts(Id,accountname,currency);
			 
		
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
	
	return acc;
	
	
	
	
}

}
