package com.lynda.javatraining.db;

import java.sql.SQLException;

import com.lynda.javatraining.db.beans.Admin;
import com.lynda.javatraining.db.tables.AdminManager;
import com.lynda.javatraining.util.InputHelper;

public class InsertSQLMain {

	public static void main(String[] args) throws Exception {

		AdminManager.displayAllRows();
		
//		int adminId = InputHelper.getIntegerInput("Select a row: ");
//		Admin bean = AdminManager.getRow(adminId);
//		
//		if(bean == null) {
//			System.err.println("No rows found");
//		}else {
//			System.out.println("Admin id: " + bean.getAdminId());
//			System.out.println("Username: " + bean.getUserName());
//			System.out.println("Admin id: " + bean.getPassword());
//		}
		
		Admin bean = new Admin();
		bean.setUserName(InputHelper.getInput("User Name: "));
		bean.setPassword(InputHelper.getInput("Password: "));
		
		boolean result = AdminManager.insert(bean);
		
		if(result) {
			System.out.println("New row with primary ket " + bean.getAdminId() + " inserted");
		}
	}
}

