package util;

import java.sql.DriverManager;

import java.sql.Connection;

public class DatabaseUtil {
	public static Connection getConnection() {
		
	
	try {
		String dbURL = "jdbc:mysql://localhost:3306/Masil";
		String dbID = "root";
		String dbPassword = "1234";
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(dbURL , dbID , dbPassword);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
	}
}
