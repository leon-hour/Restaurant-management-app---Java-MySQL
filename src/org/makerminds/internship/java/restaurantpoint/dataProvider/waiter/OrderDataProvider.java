package org.makerminds.internship.java.restaurantpoint.dataProvider.waiter;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.makerminds.internship.java.restaurantpoint.database.DBMSConnection;
import org.makerminds.internship.java.restaurantpoint.login.controller.LoginController;

public class OrderDataProvider {

	public OrderDataProvider() {
		// TODO Auto-generated constructor stub
	}

	public static String[][] getRecord(int i, String dataBaseName, String tableName) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException, FileNotFoundException {
		DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/orders", "root", "");
		Connection connection = dbmsConnection.getConnection();
		String sql1 = "select * from arrivedorders;";
		PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
		;
		ResultSet resultSet1 = preparedStatement1.executeQuery(sql1);
		int j = 0;
		while (resultSet1.next()) {
			j++;
			System.out.println(j);
		}
		String sql = "select * from " + tableName + ";";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		;
		ResultSet resultSet = preparedStatement.executeQuery(sql);
		String[][] tableData = new String[j][3];
		while (resultSet.next()) {
			tableData[i][0] = resultSet.getString(1);
			tableData[i][1] = resultSet.getString(2);
			tableData[i][2] = resultSet.getString(3);
			i++;
		}
		//dbmsConnection.closeConnection(connection, preparedStatement);
		return tableData;
	}
	
	public static String[][] getOrders(int i, String tableId)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
	DBMSConnection dbmsConnection = new DBMSConnection(
			"jdbc:mysql://localhost:3306/" + LoginController.getInstance().getLoggedInUser().getRestaurant(),
			"root", "Leonora.MM21");
	Connection connection = dbmsConnection.getConnection();
	String sql = "select * from orders where tableId = ?";
	PreparedStatement preparedStatement = connection.prepareStatement(sql);
	preparedStatement.setString(1, tableId);
	ResultSet resultSet = preparedStatement.executeQuery();
	int j = 0;
	while (resultSet.next()) {
		j++;
		System.out.println(j);
	}
	String sql1 = "select * from orders where tableId = ? ";
	PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
	preparedStatement1.setString(1, tableId);
	ResultSet resultSet1 = preparedStatement1.executeQuery();
	String[][] tableData = new String[j][3];
	while (resultSet1.next()) {
		tableData[i][0] = resultSet1.getString(2);
		tableData[i][1] = resultSet1.getString(3);
		tableData[i][2] = resultSet1.getString(4);
		i++;
	}
	return tableData;
}
}
