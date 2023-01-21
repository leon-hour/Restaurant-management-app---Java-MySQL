package org.makerminds.internship.java.restaurantpoint.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Leonora Latifaj
 *
 */
public class DatabaseActions {

	public static void insertRecord(String restaurantName, String menuName, String ID, String menuItemName, String price)
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
		String sql = "update Student set department = ? where name = ?;";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		//preparedStatement.setString(2, inputname);
		//preparedStatement.setString(1, newDepartment);
		int i = preparedStatement.executeUpdate();
		if (i > 0) {
			System.out.println("Record updated sucessfully");
		} else {
			System.out.println("No Such record in the Database");
		}
		dbmsConnection.closeConnection(connection, preparedStatement);
	}

	public static void deleteRecord(String restaurantName, String menuName, String string, String menuItemName, String string2)
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
	
	public static void createTabel(String restaurantName)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/" + restaurantName, "root", "");
		Connection connection = dbmsConnection.getConnection();

	     String sql = "CREATE TABLE  Table1 (id VARCHAR(25) not NULL, chairs VARCHAR(25), PRIMARY KEY ( id ))"; 
		Statement statement = connection.createStatement();
		statement.executeUpdate(sql);
		System.out.println("Created table in given database...");
	}
	public static void createDatabase(String dataBaseName) {
		// Open a connection
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");
				Statement stmt = conn.createStatement();) {
			String sql = "CREATE DATABASE " + dataBaseName;
			stmt.executeUpdate(sql);
			System.out.println("Database created successfully...");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteDatabase(String dataBaseName) {
		// Open a connection
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");
				Statement stmt = conn.createStatement();) {
			String sql = "DROP DATABASE " + dataBaseName;
			stmt.executeUpdate(sql);
			System.out.println("Database DELETED successfully...");
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	public static List<String> getDatabaseList() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/", "root", "");
		Connection connection = dbmsConnection.getConnection();
	        DatabaseMetaData metadata = connection.getMetaData();
	        ResultSet result = metadata.getCatalogs();
	        List<String>  databaseList = new ArrayList<String>();
	        while (result.next()) {
	            String aDBName = result.getString(1);
	            System.out.println(aDBName);
	            databaseList.add(aDBName);
	        }      
	         
	        connection.close();
		return databaseList;
	}
	public static List<String> getTabelList(String dataBaseName)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/" + dataBaseName, "root", "");
		Connection connection = dbmsConnection.getConnection();
		System.out.println("Connection established......");
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery("Show tables");
		System.out.println("Tables in the current database: ");
		ArrayList<String> tableList = new ArrayList<String>();
		while (rs.next()) {
			tableList.add(rs.getString(1));
			System.out.println(rs.getString(1));
		}
		return tableList;
	}
}
