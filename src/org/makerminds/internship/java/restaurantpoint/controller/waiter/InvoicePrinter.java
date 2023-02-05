package org.makerminds.internship.java.restaurantpoint.controller.waiter;

import org.makerminds.internship.java.restaurantpoint.login.controller.LoginController;

public class InvoicePrinter {
	String tableID;
	static double vATRate = 0.18;
	static double totalOrderAmount = 0.0;
	static StringBuilder str = new StringBuilder();

		public static String printOrderInfo( String[][]  orderItemData, String tableID) {
			str = new StringBuilder();
			totalOrderAmount = 0.0;
			header(tableID);
			printOrderItemInfo(orderItemData);
			footer();
			return str.toString();
		}
		public static void header(String tableID) {
			str.append("<html><br>--------------------------------------");
			str.append("<br>Order from tabel with id: " + tableID );
			//str.append("<br>----------------------------------");
		}
		public static void printOrderItemInfo(String[][] orderItemData){
			for(int i = 0; i< orderItemData.length; i++) {
				System.out.println("<br>"+orderItemData[i][2] + "X " + orderItemData[i][0]+ " | "
					+ orderItemData[i][1]+ " | "
					+Integer.parseInt(orderItemData[i][2])*Double.parseDouble(orderItemData[i][1]));
			totalOrderAmount = totalOrderAmount+ Integer.parseInt(orderItemData[i][2])*Double.parseDouble(orderItemData[i][1]);
			}
		}
		public static void footer() {
			str.append("<br>----------------------------------");
			str.append("<br>Total order amount: " + totalOrderAmount + " Euro");
			str.append("<br>VAT amount: " +totalOrderAmount * vATRate+ " Euro");
			str.append("<br>VAT : " + vATRate *100+ " %");
			str.append("<br>Total order amount with VAT: " + (totalOrderAmount + (totalOrderAmount*vATRate)) + " Euro");
			str.append("<br>-------------------------------------");
			str.append("<br>" + LoginController.getInstance().getLoggedInUser().getRestaurant() + ".");
		}
	}