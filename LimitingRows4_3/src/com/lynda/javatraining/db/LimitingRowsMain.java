package com.lynda.javatraining.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.lynda.javatraining.db.tables.Tours;

public class LimitingRowsMain {

	public static void main(String[] args) throws SQLException {
		
//		ResultSet rs = null;

		try (Connection conn = DBUtil.getConnection(DBType.HSQLDB);
//				Statement stmt = conn.createStatement();
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery("select tourId, tourName, price from tours " + "limit 10, 5");
				) {			 
//			stmt.setMaxRows(5);  //setMaxRows(5);
			Tours.displayData(rs);					
			
		} catch (SQLException e) {
			DBUtil.processException(e);
		} 
	}

}
