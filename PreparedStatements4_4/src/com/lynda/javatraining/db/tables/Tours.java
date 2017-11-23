package com.lynda.javatraining.db.tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;

public class Tours {
	public static void displayData(ResultSet rs) throws SQLException {

		rs.last();
		int numRows = rs.getRow();

		if(numRows == 0) {
			System.out.println("No more rows to fetch the data!");
		}else {
			System.out.println("There are " + numRows + " tours available");
			rs.beforeFirst();

			while(rs.next()) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("Tour " + rs.getInt("tourId") + ": ");
				buffer.append(rs.getString("tourName"));

				double price = rs.getDouble("price");
				NumberFormat nf = NumberFormat.getCurrencyInstance();
				String formattedPrice = nf.format(price);
				buffer.append(" ( " + formattedPrice + " )");

				System.out.println(buffer.toString());
			}
		}
	}

}
