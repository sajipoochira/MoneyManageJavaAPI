package com.mecherycorp.MoneyManage.businesslogic;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

import com.mecherycorp.MoneyManage.DBoperations.AccountsTable;
import com.mecherycorp.MoneyManage.DBoperations.CategoryTable;
import com.mecherycorp.MoneyManage.DBoperations.ExpenceTable;
import com.mecherycorp.MoneyManage.DBoperations.IncomeTable;
import com.mecherycorp.MoneyManage.model.Accounts;
import com.mecherycorp.MoneyManage.model.BalanceSheet;
import com.mecherycorp.MoneyManage.model.Expence;
import com.mecherycorp.MoneyManage.model.ExpenceCatogery;
import com.mecherycorp.MoneyManage.model.Income;
import com.mecherycorp.MoneyManage.model.IncomeCatogery;
import com.mecherycorp.MoneyManage.model.monthTodate;

public class Calculations {
	Income income;
	Expence expence;
	AccountsTable acctbl = new AccountsTable();
	IncomeTable inctble;
	public List<Income> IncomeList;
	ExpenceTable exctble;
	List<Expence> ExpenceList;
	
	
	public Calculations() {
		exctble = new ExpenceTable();
		inctble = new IncomeTable();
		IncomeList = inctble.getIncome();
		ExpenceList = exctble.getExpence();
		
	}


	public float totalIncome() {
				
		float sum = 0;
		
		
		 for (int counter = 0; counter < IncomeList.size(); counter++) { 	
			 
			 income = IncomeList.get(counter); 	
			 Accounts account = acctbl.getAccounts(income.getAccount()); 
			 
			 if((account.getCurrency()).equals("QAR") && CatergoryMatch(income.getIncome_Catogory(), "income") )
			 {
				 
	          sum+=income.getIncome_Amount();
			 }
	      }   		
		
		return sum;
		
				
	}
	
	
public float totalIncome(int month, int year) {
		
	//	IncomeTable inctble = new IncomeTable();
		
	//	List<Income> IncomeList = inctble.getIncome();
		float sum = 0;
		
		monthTodate mtd = getFirstandLastDay(month,year);
		
		 for (int counter = 0; counter < IncomeList.size(); counter++) { 	
			 
			 income = IncomeList.get(counter); 	
			 
			 LocalDate IncomeDate = income.getDate();
			 Accounts account = acctbl.getAccounts(income.getAccount()); 
			 
			 if (IncomeDate.compareTo(mtd.getLastDay())<=0 && IncomeDate.compareTo(mtd.getFirstDay())>=0 && (account.getCurrency()).equals("QAR") && CatergoryMatch(income.getIncome_Catogory(), "income"))
			 {
	         	          
	          sum+=income.getIncome_Amount();
			 }
	      }   		
		
		return sum;
		
				
	}
	
public float totalExpence() {
		
	
		float sum = 0;
		
		
		 for (int counter = 0; counter < ExpenceList.size(); counter++) { 	
			 
			 expence = ExpenceList.get(counter); 	
			// Accounts account = acctbl.getAccounts(expence.getAccount()); 
			 if(CatergoryMatch(expence.getExpence_Catogory(), "expence"))
			 {
	          sum+=expence.getExpence_Amount();	 
			 }
	          }   		
		
		return sum;
		
				
	}



public float totalExpence(int month, int year) {
	
//	ExpenceTable exctble = new ExpenceTable();
		
		List<Expence> ExpenceList = exctble.getExpence();
		
		monthTodate mtd = getFirstandLastDay(month,year);
		
		float sum = 0;
		
		
		 for (int counter = 0; counter < ExpenceList.size(); counter++) { 	
			 
			 expence = ExpenceList.get(counter); 
			 
			 LocalDate ExpenceDate = expence.getDate();
			 
			//Accounts account = acctbl.getAccounts(expence.getAccount());  
			 if (ExpenceDate.compareTo(mtd.getLastDay())<=0 && ExpenceDate.compareTo(mtd.getFirstDay())>=0 && CatergoryMatch(expence.getExpence_Catogory(), "expence"))
			 {          
	          sum+=expence.getExpence_Amount();	 
			 }
	          }   		
		
		return sum;
		
				
	}


public BalanceSheet getBalance() {
	
	float TotalInc = totalIncome();
	float Totalexp = totalExpence();
	
	float Bal = TotalInc - Totalexp;
	
	BalanceSheet bs = new BalanceSheet(TotalInc,Totalexp,Bal);
	
	return bs;
	
	
}
public BalanceSheet getBalance(int month, int year) {
	
			
	float TotalInc = totalIncome(month,year);
	float Totalexp = totalExpence(month,year);
	
	float Bal = TotalInc - Totalexp;
	
	BalanceSheet bs = new BalanceSheet(TotalInc,Totalexp,Bal);
	
	return bs;
	
	
}

public static monthTodate getFirstandLastDay(int month, int year) {
	
	
	
	YearMonth yearMonth = YearMonth.of( year, month );
	LocalDate first = yearMonth.atDay( 1 );
	LocalDate last = yearMonth.atEndOfMonth();
	
	monthTodate mtd = new monthTodate(first,last);
	return mtd;
	
	
}

public boolean CatergoryMatch(String catname, String CatType) {
	CategoryTable ct = new CategoryTable();
	boolean exists = false;
	if(CatType.equals("income")) {
		
		List<IncomeCatogery> IncomecatList = ct.getIncomeCategory();
		for(IncomeCatogery inccat : IncomecatList ) {
			if ((inccat.getCatogeryName()).equals(catname)) {
				exists = true;
			}
			
		}
		
	}else if(CatType.equals("expence")){
		List<ExpenceCatogery> expencecatList = ct.getExpenceCategory();
		for(ExpenceCatogery exccat : expencecatList ) {
			if ((exccat.getCatogeryName()).equals(catname) && (exccat.getCatType() ).equals("Normal")) {
				exists = true;
			}
			
		}
		
	}else {
		System.out.println("enter valid type");
	}
	
	return exists;
	
	
}


public List<BalanceSheet> getBalancelastyear(int month, int year) {
	
	
	ArrayList<BalanceSheet> balancelist = new ArrayList<BalanceSheet>();
	
	for(int i=0;i<12;i++) {
		
		month = month-1;
		if (month == 0)
		{
			month = 12;
			year = year-1;
		}
		balancelist.add(getBalance(month,year));
			
	}
	
	
	return balancelist;
}


}
