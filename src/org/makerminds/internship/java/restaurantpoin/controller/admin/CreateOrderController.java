package org.makerminds.internship.java.restaurantpoin.controller.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.makerminds.internship.java.restaurantpoin.login.controller.LoginController;
import org.makerminds.internship.java.restaurantpoint.database.DBMSConnection;

public class CreateOrderController {

		public static void insertRecord(String tableId, String menuItemName, String menuItemPrice, String quantity)
				throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
			DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/"+LoginController.getInstance().getLoggedInUser().getRestaurant(), "root", "Leonora.MM21");
			Connection connection = dbmsConnection.getConnection();
			String sql = "insert into orders values (?,?,?,?,?);";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, tableId);
			preparedStatement.setString(2, menuItemName);
			preparedStatement.setString(3, menuItemPrice);
			preparedStatement.setString(4, quantity);
			preparedStatement.setString(5, "Orderd");
			preparedStatement.executeUpdate();
			System.out.println("Record  inserted successfully");
		}
		
		public static void updateRecord(String restaurantName, String tableId, String menuItem, String newOrderStatus)
				throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
			DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/"+restaurantName, "root", "Leonora.MM21");
			Connection connection = dbmsConnection.getConnection();
			String sql = "update Orders set orderStatur = ? where  tableId =? and  MenuItem = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(3, menuItem);
			preparedStatement.setString(2, tableId);
			preparedStatement.setString(1, newOrderStatus);
			int i = preparedStatement.executeUpdate();
			if (i > 0) {
				System.out.println("Record updated sucessfully");
			} else {
				System.out.println("No Such record in the Database");
			}
		}
		public static void updateRecordWaiter(String restaurantName, String tableId, String menuItem, String newOrderStatus)
				throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
			DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/"+restaurantName, "root", "Leonora.MM21");
			Connection connection = dbmsConnection.getConnection();
			String sql = "update Orders set orderStatur = ? where  tableId =? and  MenuItem = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(3, menuItem);
			preparedStatement.setString(2, tableId);
			preparedStatement.setString(1, newOrderStatus);
			int i = preparedStatement.executeUpdate();
			if (i > 0) {
				System.out.println("Record updated sucessfully");
			} else {
				System.out.println("No Such record in the Database");
			}
		}

		public static void deleteRecord(String restaurantName, String menuName, String string, String menuItemName, String string2)
				throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
			DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/"+restaurantName, "root", "Leonora.MM21");
			Connection connection = dbmsConnection.getConnection();
			String sql = "delete from "+menuName+" where id = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, string);
			int i = preparedStatement.executeUpdate();
			if (i > 0) {
				System.out.println("Record Deleted Successfully");
			} else {
				System.out.println("No Such Record in the Database");
			}
		}

}