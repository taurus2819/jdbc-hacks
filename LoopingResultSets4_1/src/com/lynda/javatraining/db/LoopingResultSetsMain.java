package com.lynda.javatraining.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.lynda.javatraining.db.tables.Tours;

public class LoopingResultSetsMain {

	public static void main(String[] args) throws SQLException {

		try (Connection conn = DBUtil.getConnection(DBType.MYSQLDB);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery("select * from tours");
				) {			
			Tours.displayData(rs);

		} catch (SQLException e) {
			DBUtil.processException(e);
		}		
	}

}
