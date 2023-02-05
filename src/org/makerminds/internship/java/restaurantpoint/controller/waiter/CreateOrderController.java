package org.makerminds.internship.java.restaurantpoint.controller.waiter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.makerminds.internship.java.restaurantpoint.database.DBMSConnection;
import org.makerminds.internship.java.restaurantpoint.enums.OrderStatus;
import org.makerminds.internship.java.restaurantpoint.login.controller.LoginController;

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
			preparedStatement.setString(5, OrderStatus.QUEUE.toString());
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
			switch(newOrderStatus) {
			case "QUEUE":
				newOrderStatus = OrderStatus.IN_PROGRESS.toString();
				preparedStatement.setString(1, newOrderStatus);
				break;
			case "IN_PROGRESS":
				newOrderStatus = OrderStatus.READY.toString();
				preparedStatement.setString(1, newOrderStatus);
				break;
			}
			int i = preparedStatement.executeUpdate();
			if (i > 0) {
				System.out.println("Record updated sucessfully");
			} else {
				System.out.println("No Such record in the Database");
			}
		}
		public static void updateRecordWaiter(String restaurantName, String tableId, String newOrderStatus)
				throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
			DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/"+restaurantName, "root", "Leonora.MM21");
			Connection connection = dbmsConnection.getConnection();
			String sql = "update Orders set orderStatur = ? where  tableId =?;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(2, tableId);
			switch(newOrderStatus) {
			case "READY":
				newOrderStatus = OrderStatus.DELIVERED.toString();
				preparedStatement.setString(1, newOrderStatus);
				break;
			case "DELIVERED":
				newOrderStatus = OrderStatus.PAID.toString();
				preparedStatement.setString(1, newOrderStatus);
				break;
			}
			int i = preparedStatement.executeUpdate();
			if (i > 0) {
				System.out.println("Record updated sucessfully");
			} else {
				System.out.println("No Such record in the Database");
			}
		}

		public static void deleteRecord(String restaurantName)
				throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
			DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/"+restaurantName, "root", "Leonora.MM21");
			Connection connection = dbmsConnection.getConnection();
			String sql = "select * from Orders where orderStatur = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "PAID");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String sql1 = "delete from Orders where orderStatur = ?;";
				PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
				preparedStatement1.setString(1, "PAID");
				preparedStatement1.executeUpdate();
			}
		}

}