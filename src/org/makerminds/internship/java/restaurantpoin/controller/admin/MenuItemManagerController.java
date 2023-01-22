package org.makerminds.internship.java.restaurantpoin.controller.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.makerminds.internship.java.restaurantpoint.database.DBMSConnection;

/**
 * @author Leonora Latifaj
 *
 */
public class MenuItemManagerController {

	public static void insertRecord(String restaurantName, String menuName, String ID, String menuItemName, String price)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/"+restaurantName, "root", "Leonora.MM21");
		Connection connection = dbmsConnection.getConnection();
		String sql1 = "select * from "+menuName+" where id=?";
		PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
		preparedStatement1.setString(1, ID);
		ResultSet resultSet = preparedStatement1.executeQuery();
		int resultSetSize = 0;
		if(resultSet.last()) {
			resultSetSize = resultSet.getRow();
		}
		if(resultSetSize == 0) {
		String sql = "insert into "+menuName+" values (?,?,?);";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, ID);
		preparedStatement.setString(2, menuItemName);
		preparedStatement.setString(3, price);
		preparedStatement.executeUpdate();
		System.out.println("Record  inserted successfully");

		//dbmsConnection.closeConnection(connection, preparedStatement1);
		//dbmsConnection.closeConnection(connection, preparedStatement);
	}
		else {
			System.out.println("Record found in the database");
		}
		dbmsConnection.closeConnection(connection, preparedStatement1);
	}
	
	public static void updateRecord(String restaurantName, String menuName, String oldId, String newId, String newName, String newPrice)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/"+restaurantName, "root", "Leonora.MM21");
		Connection connection = dbmsConnection.getConnection();
		String sql = "update "+menuName+" set id = ?, name = ?, price = ? where id = ?;";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(4, oldId);
		preparedStatement.setString(1, newId);
		preparedStatement.setString(2, newName);
		preparedStatement.setString(3, newPrice);
		int i = preparedStatement.executeUpdate();
		if (i > 0) {
			System.out.println("Record updated sucessfully");
		} else {
			System.out.println("No Such record in the Database");
		}
	//	dbmsConnection.closeConnection(connection, preparedStatement);
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
	//	dbmsConnection.closeConnection(connection, preparedStatement);
	}
	
}
