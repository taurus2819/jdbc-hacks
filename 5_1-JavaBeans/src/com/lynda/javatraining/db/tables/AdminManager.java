package com.lynda.javatraining.db.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.lynda.javatraining.db.DBType;
import com.lynda.javatraining.db.DBUtil;
import com.lynda.javatraining.db.beans.Admin;

public class AdminManager {

	public static void displayAllRows() throws SQLException {

		String sql = "select adminId, userName, password from admin";

		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQLDB);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				) {
			System.out.println("Admin Table");
			while(rs.next()) {
				StringBuffer bf = new StringBuffer();
				bf.append(rs.getObject("adminId", Integer.class));
				bf.append(rs.getObject("userName", String.class));
				bf.append(rs.getObject("password", String.class));	
				System.out.println(bf.toString());
			}		
		}
	}
	
	public static Admin getRow(int adminId) throws SQLException {
		String sql = "select * from admin where adminId = ?";
		ResultSet rs = null;
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQLDB);
				PreparedStatement stmt = conn.prepareStatement(sql);
				){
			stmt.setInt(1, adminId);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				Admin bean = new Admin();
				bean.setAdminId(adminId);
				bean.setUserName(rs.getString("userName"));
				bean.setPassword(rs.getString("password"));
				return bean;
			}else {
				return null;
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}finally {
			if(rs != null) {
				rs.close();
			}
		}	
		
	}
}
