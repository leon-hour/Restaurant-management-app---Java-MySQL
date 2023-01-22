package org.makerminds.internship.java.restaurantpoin.controller.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.makerminds.internship.java.restaurantpoint.database.DBMSConnection;

public class EmployeeManagerController {
	public static void insertRecord(String EmployeeId, String Name, String LastName,String restaurantName, String userRole)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/employees", "root", "Leonora.MM21");
		Connection connection = dbmsConnection.getConnection();
		String sql1 = "select * from EMPLOYEES where ID=?";
		PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
		preparedStatement1.setString(1, EmployeeId);
		ResultSet resultSet = preparedStatement1.executeQuery();
		int resultSetSize = 0;
		if(resultSet.last()) {
			resultSetSize = resultSet.getRow();
		}
    
	if(resultSetSize == 0) {
		String sql = "insert into EMPLOYEES values (?,?,?,?,?,?);";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, EmployeeId);
		preparedStatement.setString(2, Name);
		preparedStatement.setString(3, LastName);
		preparedStatement.setString(4, restaurantName);
		preparedStatement.setString(5, userRole);
		preparedStatement.setString(6, userRole+EmployeeId);
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
	
	public static void updateRecord(String oldId, String newName, String newLastName, String newRestaurantName, String newUserRole)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/employees", "root", "Leonora.MM21");
		Connection connection = dbmsConnection.getConnection();
		String sql = "update employees set name = ?, lastName = ?, restaurant = ?, UserRole = ?, password = ? where id = ?;";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(6, oldId);
		preparedStatement.setString(1, newName);
		preparedStatement.setString(2, newLastName);
		preparedStatement.setString(3, newRestaurantName);
		preparedStatement.setString(4, newUserRole);
		preparedStatement.setString(5, newUserRole+oldId);
		int i = preparedStatement.executeUpdate();
		if (i > 0) {
			System.out.println("Record updated sucessfully");
		} else {
			System.out.println("No Such record in the Database");
		}
		//dbmsConnection.closeConnection(connection, preparedStatement);
	}

	public static void deleteRecord(String employeeId)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/employees", "root", "Leonora.MM21");
		Connection connection = dbmsConnection.getConnection();
		String sql = "delete from EMPLOYEES where id = ?;";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, employeeId);
		int i = preparedStatement.executeUpdate();
		if (i > 0) {
			System.out.println("Record Deleted Successfully");
		} else {
			System.out.println("No Such Record in the Database");
		}
		//dbmsConnection.closeConnection(connection, preparedStatement);
	}
}
