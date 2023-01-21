package org.makerminds.internship.java.restaurantpoint.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author Leonora Latifaj
 *
 */
public class ButtonsAction{
	Scanner input = new Scanner(System.in);

	public void insertRecord(String restaurantName, String menuName, String ID, String menuItemName, String price)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/"+restaurantName, "root", "");
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
		dbmsConnection.closeConnection(connection, preparedStatement);
	}
		else {
			System.out.println("Record found in the database");
		}
		dbmsConnection.closeConnection(connection, preparedStatement1);
	}
	public void updateRecord()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/menu", "root", "");
		Connection connection = dbmsConnection.getConnection();
		System.out.println("Name: ");
		String inputname = input.nextLine();
		System.out.println("New Department: ");
		String newDepartment = input.nextLine();
		String sql = "update Student set department = ? where name = ?;";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(2, inputname);
		preparedStatement.setString(1, newDepartment);
		int i = preparedStatement.executeUpdate();
		if (i > 0) {
			System.out.println("Record updated sucessfully");
		} else {
			System.out.println("No Such record in the Database");
		}
		dbmsConnection.closeConnection(connection, preparedStatement);
	}

	public void deleteRecord(String restaurantName, String menuName, String string, String menuItemName, String string2)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/"+restaurantName, "root", "");
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
		dbmsConnection.closeConnection(connection, preparedStatement);
	}
	
	public static  void insertTableRecord(String restaurantName, String ID, String nrOfChairs)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/"+restaurantName, "root", "");
		Connection connection = dbmsConnection.getConnection();
		String sql1 = "select * from table1 where id=?";
		PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
		preparedStatement1.setString(1, ID);
		ResultSet resultSet = preparedStatement1.executeQuery();
		int resultSetSize = 0;
		if(resultSet.last()) {
			resultSetSize = resultSet.getRow();
		}
		if(resultSetSize == 0) {
		String sql = "insert into table1 values (?,?);";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, ID);
		preparedStatement.setString(2, nrOfChairs);
		preparedStatement.executeUpdate();
		System.out.println("Record  inserted successfully");
		dbmsConnection.closeConnection(connection, preparedStatement);
	}
		else {
			System.out.println("Record found in the database");
		}
		dbmsConnection.closeConnection(connection, preparedStatement1);
	}
	public static void deleteTableRecord(String restaurantName, String id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
			DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/"+restaurantName, "root", "");
			Connection connection = dbmsConnection.getConnection();
			String sql = "delete from TABLE1 where id = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			int i = preparedStatement.executeUpdate();
			if (i > 0) {
				System.out.println("Record Deleted Successfully");
			} else {
				System.out.println("No Such Record in the Database");
			}
			dbmsConnection.closeConnection(connection, preparedStatement);
		}
	public static void insertRestaurantDataRecord(String restaurantName, String restaurantAddress)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/RestaurantAddress", "root", "");
		Connection connection = dbmsConnection.getConnection();
		String sql1 = "select * from restaurantaddressdata where restaurantName=?";
		PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
		preparedStatement1.setString(1, restaurantName);
		ResultSet resultSet = preparedStatement1.executeQuery();
		int resultSetSize = 0;
		if(resultSet.last()) {
			resultSetSize = resultSet.getRow();
		}
		if(resultSetSize == 0) {
		String sql = "insert into restaurantaddressdata values (?,?);";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, restaurantName);
		preparedStatement.setString(2, restaurantAddress);
		preparedStatement.executeUpdate();
		System.out.println("Record  inserted successfully");
		dbmsConnection.closeConnection(connection, preparedStatement);
	}
		else {
			System.out.println("Record found in the database");
		}
		dbmsConnection.closeConnection(connection, preparedStatement1);
	}
}