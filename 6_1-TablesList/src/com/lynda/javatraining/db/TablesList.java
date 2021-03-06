package com.lynda.javatraining.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TablesList {

	public static void main(String[] args) throws SQLException {
		ConnectionManager.getInstance().setDBType(DBType.MYSQLDB);
		Connection conn = ConnectionManager.getInstance().getConnection();	
		ResultSet rsTables = null;
		try {
			DatabaseMetaData metadata = conn.getMetaData();
			String[] tableTypes = {"TABLE"};
			rsTables = metadata.getTables(null, "%", "%", tableTypes);
			while(rsTables.next()) {
				System.out.println(rsTables.getString("TABLE_NAME"));
			}
		} catch (SQLException e) {			
			System.err.println(e.getMessage());
		}finally {
			if(rsTables != null) {
				rsTables.close();
			}
		}
		
		
		ConnectionManager.getInstance().close();
	}
}

