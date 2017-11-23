package com.lynda.javatraining.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.lynda.javatraining.db.tables.Tours;
import com.lynda.javatraining.util.InputHelper;

public class StoredProcsMain {
	
	private static final String SQL = "{call GetToursByPrice(?)}";

	public static void main(String[] args) throws SQLException {
		
		double maxPrice;
		try {
			maxPrice = InputHelper.getDoubleInput("Enter the price: ");
		} catch (NumberFormatException e) {
			System.out.println("Invalid number: " + e.getMessage());
			return;
		}
				
		ResultSet rs = null;
		try (Connection conn = DBUtil.getConnection(DBType.MYSQLDB);
				CallableStatement stmt = conn.prepareCall(SQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				) {			 
			stmt.setDouble(1, maxPrice);
			rs = stmt.executeQuery();
			Tours.displayData(rs);					
			
		} catch (SQLException e) {
			DBUtil.processException(e);
		} 
	}

}
