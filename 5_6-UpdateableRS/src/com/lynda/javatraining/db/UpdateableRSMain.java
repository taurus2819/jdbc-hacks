package com.lynda.javatraining.db;

import com.lynda.javatraining.db.beans.Admin;
import com.lynda.javatraining.db.tables.AdminManager;
import com.lynda.javatraining.util.InputHelper;

public class UpdateableRSMain {

	public static void main(String[] args) throws Exception {

		AdminManager.displayAllRows();
		
		int adminId = InputHelper.getIntegerInput("Select a row: ");
		Admin bean = AdminManager.getRow(adminId);
		
		System.out.println("Before updating the row ");
		if(bean == null) {
			System.err.println("No rows found");
			return;
		}else {
			System.out.println("Admin id: " + bean.getAdminId());
			System.out.println("Username: " + bean.getUserName());
			System.out.println("Password: " + bean.getPassword());
		}
		
		System.out.println("After updating the row ");
//		bean.setUserName(InputHelper.getInput("User Name: "));
		bean.setPassword(InputHelper.getInput("Set new Password: "));
		
		boolean result = AdminManager.updateAbleRS(bean);
		
		if(result) {
			System.out.println("The row with primary key adminId " + bean.getAdminId() + " is updated");
		} else {
			System.out.println("woops!");
		}
	}
}

