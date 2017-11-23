package com.lynda.javatraining.db.tables;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;

public class Tours {
	public static void displayData(ResultSet rs, int numRows) throws SQLException {

//		rs.last();
//		int numRows = rs.getRow();

		if(numRows == 0) {
			System.out.println("No more rows to fetch the data!");
		}else {
			System.out.println("There are " + numRows + " tours available");
			rs.beforeFirst();

			while(rs.next()) {
				StringBuffer buffer = new StringBuffer();
				int tourID 			= rs.getObject("tourId", Integer.class);
				String tourName 	= rs.getObject("tourName", String.class);
				BigDecimal price 	= rs.getObject("price", BigDecimal.class);
				
//				int tourID 		= rs.getInt("tourId");
//				String tourName = rs.getString("tourName");
//				double price 	= rs.getDouble("price");
				
				buffer.append("Tour " + tourID + ": ");				
				buffer.append(tourName);				
				NumberFormat nf = NumberFormat.getCurrencyInstance();
				String formattedPrice = nf.format(price);
				buffer.append(" ( " + formattedPrice + " )");

				System.out.println(buffer.toString());
			}
		}
	}

}
