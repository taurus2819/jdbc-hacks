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
	
	public static boolean insert(Admin bean) throws Exception {

		String sql = "INSERT into admin (userName, password) " +
				"VALUES (?, ?)";
		ResultSet keys = null;
		try (
				Connection conn = DBUtil.getConnection(DBType.MYSQLDB);
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				) {
			
			stmt.setString(1, bean.getUserName());
			stmt.setString(2, bean.getPassword());
			int affected = stmt.executeUpdate();
			
			if(affected == 1) {
				keys = stmt.getGeneratedKeys();
				keys.next();
				int newKey = keys.getInt(1);
				bean.setAdminId(newKey);
			} else {
				System.err.println("No rows affected");
				return false;
			}
			
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		} finally{
			if(keys != null) {
				keys.close();
			}
		}
		return true;
	}
	
	public static boolean updateAbleRS(Admin bean) throws Exception{
		String sql = "select * from admin where adminId = ?";
		ResultSet rs = null;
		try (
				Connection conn = DBUtil.getConnection(DBType.MYSQLDB);
				PreparedStatement stmt = conn.prepareStatement(
						sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				){
//			stmt.setString(1, bean.getUserName());
//			stmt.setString(2, bean.getPassword());
			stmt.setInt(1, bean.getAdminId());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				rs.updateString("username", bean.getUserName());
				rs.updateString("password", bean.getPassword());
				rs.updateRow();
				return true;
			} else {
				return false;
			}
		}catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}finally {
			if (rs != null) {
				rs.close();
			}
		}
	}
}

