package com.lynda.javatraining.db;

import com.lynda.javatraining.db.beans.Admin;
import com.lynda.javatraining.db.tables.AdminManager;
import com.lynda.javatraining.util.InputHelper;

public class UpdateSQLMain {

	private static Admin bean = null;
	private static boolean result = false;
	private static int adminId;

	public static void main(String[] args) throws Exception {
		
		ConnectionManager.getInstance().setDBType(DBType.MYSQLDB);		
		
//		System.out.println("Select the number for : 1 - Insert, 2 - Update, 3 - Delete");
		
		int selection = 0;		
		do {
			AdminManager.displayAllRows();
			selection = InputHelper.getIntegerInput("Select the number for : 1 - Insert, 2 - Update, 3 - Delete, 0 - Exit");
			
			switch (selection) {
			case 1:
				System.out.println("Insert into database");
				
				bean = new Admin();
				bean.setUserName(InputHelper.getInput("User Name: "));
				bean.setPassword(InputHelper.getInput("Password: "));
				
				boolean result = AdminManager.insert(bean);
				
				if(result) {
					System.out.println("New row with primary key " + bean.getAdminId() + " inserted");
				}				
				break;
			case 2:
//				System.out.println("Update the selected row");
				
				adminId = InputHelper.getIntegerInput("Select a row to update: ");
				bean = AdminManager.getRow(adminId);
				
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
//				bean.setUserName(InputHelper.getInput("User Name: "));
				bean.setPassword(InputHelper.getInput("Password: "));
				
				result = AdminManager.update(bean);
				
				if(result) {
					System.out.println("The row with primary key adminId " + bean.getAdminId() + " is updated");
				} else {
					System.out.println("woops!");
				}
				
				break;
			case 3:
//				System.out.println("Delete the selected row");
				
				int adminId = InputHelper.getIntegerInput("Select a row to delete: ");
				bean = AdminManager.getRow(adminId);
				
				System.out.println("Deleting the row .... ");
				if(bean == null) {
					System.err.println("No rows found");
					return;
				}else {			
					result = AdminManager.deleteRow(bean);
					
					if(result) {
//						System.out.println("The row with primary key adminId " + bean.getAdminId() + " is deleted");
						System.out.println("The row is delted");
					} else {
						System.out.println("woops!");
					}
				}
				
				break;		
			default:
				break;
			}			
		}while (selection != 0);
		
		
		
		ConnectionManager.getInstance().close();
	}
}

