package org.makerminds.internship.java.restaurantpoin.controller.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.makerminds.internship.java.restaurantpoint.database.DBMSConnection;

/**
 * @author Leonora Latifaj
 *
 */
public class MenuManagerController {

	public MenuManagerController() {
		// TODO Auto-generated constructor stub
	}

	public static void createTabel(String restaurantName, String menutName)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/" + restaurantName, "root", "Leonora.MM21");
		Connection connection = dbmsConnection.getConnection();

	     String sql = "CREATE TABLE  " + menutName + " (id VARCHAR(25) not NULL, name VARCHAR(25), price VARCHAR(25), PRIMARY KEY ( id ))"; 
		Statement statement = connection.createStatement();
		statement.executeUpdate(sql);
		System.out.println("Created table in given database...");
		dbmsConnection.closeConnection(connection, statement);
	}
	public static void updateTable(String restaurantName, String oldMenuName, String newMenuName)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/" + restaurantName, "root", "Leonora.MM21");
		Connection connection = dbmsConnection.getConnection();

	     String sql = "ALTER TABLE "+oldMenuName+ " RENAME TO "+ newMenuName;
		Statement statement = connection.createStatement();
		statement.executeUpdate(sql);
		System.out.println("The name of table has ben changed......!");
		dbmsConnection.closeConnection(connection, statement);
	}
	public static void deleteTable(String restaurantName, String menutName)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/" + restaurantName, "root", "Leonora.MM21");
		Connection connection = dbmsConnection.getConnection();
	     String sql = "DROP TABLE  " + menutName + ";"; 
		Statement statement = connection.createStatement();
		statement.executeUpdate(sql);
		System.out.println("Created table in given database...");
		dbmsConnection.closeConnection(connection, statement);
	}
}
