package com.lynda.javatraining.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.lynda.javatraining.db.tables.States;

public class ScrollableResultSetsMain {

	public static void main(String[] args) throws SQLException {

		try (Connection conn = DBUtil.getConnection(DBType.HSQLDB);
//				Statement stmt = conn.createStatement();
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery("select stateId, stateName from states");
				) {			 
			States.displayData(rs);
			
			rs.last(); 
			System.out.println("Number of rows: " + rs.getRow());
			
			rs.first();
			System.out.println("First state : " + rs.getString("stateName"));
			
			rs.last();
			System.out.println("Last state: " + rs.getString("stateName"));
			
			rs.absolute(10);
			System.out.println("State name: " + rs.getString("stateName"));

		} catch (SQLException e) {
			DBUtil.processException(e);
		}		
	}

}
