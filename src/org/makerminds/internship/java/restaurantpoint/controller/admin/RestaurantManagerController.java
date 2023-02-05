package org.makerminds.internship.java.restaurantpoint.controller.admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.makerminds.internship.java.restaurantpoint.database.DBMSConnection;

/**
 * @author Leonora Latifaj
 *
 */
public class RestaurantManagerController {

	public RestaurantManagerController() {
		// TODO Auto-generated constructor stub
	}
	
	public static void createDatabase(String dataBaseName) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		// Open a connection
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "Leonora.MM21");
				Statement statement = connection.createStatement();) {
			String sql = "CREATE DATABASE " + dataBaseName;
			statement.executeUpdate(sql);
			System.out.println("Database created successfully...");
			createTabel(dataBaseName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateDatabase(String databaseName, String newDatabaseName) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "Leonora.MM21");
			String sql = "ALTER DATABASE "+ databaseName + " MODIFY NAME = "+newDatabaseName ;
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			System.out.println("Database updated successfully...");
	}

	public static void deleteDatabase(String dataBaseName) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		// Open a connection
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "Leonora.MM21");
				Statement stmt = conn.createStatement();) {
			String sql = "DROP DATABASE " + dataBaseName;
			stmt.executeUpdate(sql);
			System.out.println("Database DELETED successfully...");
			deleteRestaurantDataRecord(dataBaseName);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void insertRestaurantDataRecord(String restaurantName, String restaurantAddress)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/RestaurantAddress", "root", "Leonora.MM21");
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

		dbmsConnection.closeConnection(connection, preparedStatement1);
		dbmsConnection.closeConnection(connection, preparedStatement);
	}
		else {
			System.out.println("Record found in the database");
		}
		dbmsConnection.closeConnection(connection, preparedStatement1);
	}
	
	public static void deleteRestaurantDataRecord(String restaurantName)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/RestaurantAddress", "root", "Leonora.MM21");
		Connection connection = dbmsConnection.getConnection();
		String sql = "delete from restaurantaddressdata where RestaurantName = ?;";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, restaurantName);
		int i = preparedStatement.executeUpdate();
		if (i > 0) {
			System.out.println("Record Deleted Successfully");
		} else {
			System.out.println("No Such Record in the Database");
		}
		dbmsConnection.closeConnection(connection, preparedStatement);;
	}

	public static void createTabel(String restaurantName)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/" + restaurantName, "root", "Leonora.MM21");
		Connection connection = dbmsConnection.getConnection();

	    String sql = "CREATE TABLE Table1 (id VARCHAR(25) not NULL, seats VARCHAR(25),status VARCHAR(15), PRIMARY KEY ( id ))"; 
		Statement statement = connection.createStatement();
		statement.executeUpdate(sql);
		System.out.println("Created table in given database...");
	    String sql1 = "CREATE TABLE orders (tableId VARCHAR(25) not NULL, MenuItem VARCHAR(20), itemPrice VARCHAR(10),quantity VARCHAR(2),orderStatur VARCHAR(20))"; 
		Statement statement1 = connection.createStatement();
		statement1.executeUpdate(sql1);
		System.out.println("Created table in given database...");
		dbmsConnection.closeConnection(connection, statement);;
		dbmsConnection.closeConnection(connection, statement1);;
	}
}
