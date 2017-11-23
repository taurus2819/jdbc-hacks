package com.lynda.javatraining.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static final String USERNAME = "dbuser";
	private static final String PASSWORD = "dbpassword";
	private static final String H_CONN_STRING =
			"jdbc:hsqldb:data/explorecalifornia";
	private static final String M_CONN_STRING =
			"jdbc:mysql://localhost/explorecalifornia";
	
	public static Connection getConnection(DBType dbtype) throws SQLException {
		switch (dbtype) {
		case HSQLDB:
			return DriverManager.getConnection(H_CONN_STRING, USERNAME, PASSWORD);
		case MYSQLDB:
			return DriverManager.getConnection(M_CONN_STRING, USERNAME, PASSWORD);			
		default:
		return null;
		}		
	}
	
	public static void processException(SQLException e) {
		System.err.println("Error message: " + e.getMessage());
		System.err.println("Error message: " + e.getErrorCode());
		System.err.println("Error message: " + e.getSQLState());
	}

}
