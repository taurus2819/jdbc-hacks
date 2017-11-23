package com.lynda.javatraining.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.lynda.javatraining.db.tables.Tours;
import com.lynda.javatraining.util.InputHelper;

public class PreparedStatementsMain {
	
	private static final String SQL = "SELECT tourId, tourName, price FROM tours where price <= ?";

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
				PreparedStatement stmt = conn.prepareStatement(SQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				) {			 
			stmt.setDouble(1, maxPrice);
			rs = stmt.executeQuery();
			Tours.displayData(rs);					
			
		} catch (SQLException e) {
			DBUtil.processException(e);
		} 
	}

}
