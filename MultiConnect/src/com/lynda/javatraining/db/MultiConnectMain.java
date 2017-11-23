package com.lynda.javatraining.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MultiConnectMain {
	
	public static void main(String[] args) throws SQLException {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection(DBType.MYSQLDB);
			System.out.println("Connected! to MySQLDB");
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("select * from states");
			
			rs.last();
			System.out.println("Number of rows: " + rs.getRow());
			
		} catch (SQLException e) {
			System.err.println(e);
		}finally {
			if(rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
		
	}

}
