package com.mecherycorp.MoneyManage.DBoperations;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	
	public static Connection Connect() {
	
		Connection conn = null;
		try {
			Class.forName(dbconStr.className);
			conn = DriverManager.getConnection(dbconStr.url, dbconStr.userName, dbconStr.Password);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return conn;
	}

	
	


	
}
