package com.lynda.javatraining.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TryWithResourcesMain {

	public static void main(String[] args) throws SQLException {

		try (Connection conn = DBUtil.getConnection(DBType.MYSQLDB);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery("select stateId, stateName from states");
				) {			
			rs.last();
			System.out.println("Number of rows: " + rs.getRow());

		} catch (SQLException e) {
			DBUtil.processException(e);
		}		
	}

}
