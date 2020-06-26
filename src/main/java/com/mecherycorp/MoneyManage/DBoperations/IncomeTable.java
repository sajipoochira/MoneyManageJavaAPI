package com.mecherycorp.MoneyManage.DBoperations;

import java.sql.Statement;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

import com.mecherycorp.MoneyManage.businesslogic.Calculations;
import com.mecherycorp.MoneyManage.model.Accounts;
import com.mecherycorp.MoneyManage.model.Income;
import com.mecherycorp.MoneyManage.model.monthTodate;

public class IncomeTable {
	Connection con=null;
	AccountsTable acctbl = new AccountsTable();
	Statement stmt = null;
	public List<Income> getIncome()
	{
		String query = "SELECT * FROM income";
		
		ArrayList<Income> incomeList = new ArrayList<>();
		try {
		con = DBConnect.Connect();
		
				
			stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			 while(rs.next())
			 {
				 int Id = rs.getInt(1);
				 Date date = rs.getDate(2);
				 String IncCat = rs.getString(3);
				 String IncDec = rs.getString(4);
				 float amount = rs.getFloat(5);
				 String account = rs.getString(6);
				 Income income = new Income(Id,date,IncCat,IncDec,amount,account);
				 incomeList.add(income);	 
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
		
		
				
		return incomeList;
		
		
	}

	public List<Income> getIncome(int month, int year)
	{
		String query = "SELECT * FROM income";
		Statement stmt = null;
		ArrayList<Income> incomeList = new ArrayList<>();
		try {
		con = DBConnect.Connect();
		
		monthTodate mtd = Calculations.getFirstandLastDay(month,year);
		
				
			stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			 while(rs.next())
			 {
				 int Id = rs.getInt(1);
				 Date date = rs.getDate(2);
				 String IncCat = rs.getString(3);
				 String IncDec = rs.getString(4);
				 float amount = rs.getFloat(5);
				 String account = rs.getString(6);
				 LocalDate CovDate = date.toLocalDate();
				 if (CovDate.compareTo(mtd.getLastDay())<=0 && CovDate.compareTo(mtd.getFirstDay())>=0)
				 {          
				 Income income = new Income(Id,date,IncCat,IncDec,amount,account);
				 
				 incomeList.add(income);	
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
		
		
				
		return incomeList;
		
		
	}
	
	public String addIncome(Income income) {
				
		con = DBConnect.Connect();
		String Sql = "INSERT INTO `income` (`Date`, `IncomeCatogery`, `IncomeDescription`, `Amount`, `Account`) VALUES ('"+income.getDate()+"', '"+income.getIncome_Catogory()+"' ,'"+income.getIncome_Description()+"' ,"+income.getIncome_Amount()+" ,'"+income.getAccount()+"');";
		PreparedStatement stmt =null;
		try {
			stmt = con.prepareStatement(Sql);
			
			stmt.execute();
			
			List<Accounts> Acclist = acctbl.getAccounts();
			Boolean exists = false;
			for (Accounts acc : Acclist) {
				System.out.println(acc.getAccountName()+""+income.getAccount());
				if ((acc.getAccountName()).equals(income.getAccount())) {
					System.out.println("Maching");
					exists = true;
				}
			}
			
			if(!exists) {
				System.out.println("Not matching");
				Accounts acc = new Accounts(1,income.getAccount(),"QAR");
				
				acctbl.addAccount(acc);
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

	public List<Income> getIncome(String catName) {
		String query = "SELECT * FROM income";
		Statement stmt = null;
		ArrayList<Income> incomeList = new ArrayList<>();
		try {
		con = DBConnect.Connect();
		
		
		
				
			stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			 while(rs.next())
			 {
				 int Id = rs.getInt(1);
				 Date date = rs.getDate(2);
				 String IncCat = rs.getString(3);
				 String IncDec = rs.getString(4);
				 float amount = rs.getFloat(5);
				 String account = rs.getString(6);
				 if (IncCat.equals(catName))
				 {          
				 Income income = new Income(Id,date,IncCat,IncDec,amount,account);
				 
				 incomeList.add(income);	
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
		
		
				
		return incomeList;
		
		
	}
	
}
