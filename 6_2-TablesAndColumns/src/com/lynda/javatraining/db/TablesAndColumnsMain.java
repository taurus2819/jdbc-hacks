package com.lynda.javatraining.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.lynda.javatraining.db.beans.Admin;
import com.lynda.javatraining.db.tables.AdminManager;
import com.lynda.javatraining.util.InputHelper;

public class TablesAndColumnsMain {

	private static Admin bean = null;
	private static boolean result = false;
	private static int adminId;

	public static void main(String[] args) throws Exception {
		
		ConnectionManager.getInstance().setDBType(DBType.MYSQLDB);	
		Connection conn = ConnectionManager.getInstance().getConnection();
		ResultSet rsTables = null;
		ArrayList<String> tableList = new ArrayList<>();
		ResultSet rsColumns = null;
		try {
			DatabaseMetaData metadata = conn.getMetaData();
			String[] tableTypes = {"TABLE"};
			rsTables = metadata.getTables(null, "%", "%", tableTypes);
			while(rsTables.next()) {
				tableList.add(rsTables.getString("TABLE_NAME"));
			}
			
			for (String tableName : tableList) {
				System.out.println("\nTable: " + tableName);
				System.out.println("------------------");
				rsColumns = metadata.getColumns(null, "%", tableName, "%");
				while(rsColumns.next()) {
					StringBuffer sb = new StringBuffer();
					sb.append(rsColumns.getString("COLUMN_NAME") + " : " + rsColumns.getString("TYPE_NAME"));
					System.out.println(sb.toString());
				}
			}
			
		} catch (Exception e) {
			e.getMessage();
		} finally {
			if(rsTables != null) {
				rsTables.close();
				rsColumns.close();
			}
		}
		
		
		ConnectionManager.getInstance().close();
	}
}

