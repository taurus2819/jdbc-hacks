package com.lynda.javatraining.db;

import com.lynda.javatraining.db.beans.Admin;
import com.lynda.javatraining.db.tables.AdminManager;
import com.lynda.javatraining.util.InputHelper;

public class DeleteSQLMain {

	public static void main(String[] args) throws Exception {

		AdminManager.displayAllRows();
		
		int adminId = InputHelper.getIntegerInput("Select a row: ");
		Admin bean = AdminManager.getRow(adminId);
		
		System.out.println("Deleting the row .... ");
		if(bean == null) {
			System.err.println("No rows found");
			return;
		}else {			
			boolean result = AdminManager.deleteRow(bean);
			
			if(result) {
//				System.out.println("The row with primary key adminId " + bean.getAdminId() + " is deleted");
				System.out.println("The row is delted");
			} else {
				System.out.println("woops!");
			}
		}
		
		
	}
}

