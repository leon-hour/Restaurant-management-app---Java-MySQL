package org.makerminds.internship.java.restaurantpoint.dataProvider.waiter;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.makerminds.internship.java.restaurantpoint.database.DBMSConnection;
import org.makerminds.internship.java.restaurantpoint.login.controller.LoginController;

public class ShowOrdersDataProvider {

	public static String[][] getRecord(int i) throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException, FileNotFoundException {
		DBMSConnection dbmsConnection = new DBMSConnection(
				"jdbc:mysql://localhost:3306/" + LoginController.getInstance().getLoggedInUser().getRestaurant(),
				"root", "Leonora.MM21");
		Connection connection = dbmsConnection.getConnection();
		String sql = "SELECT DISTINCT tableId, orderStatur FROM orders;";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		int j = 0;
		while (resultSet.next()) {
			j++;
			System.out.println(j);
		}
		String sql1 = "SELECT DISTINCT tableId, orderStatur FROM orders; ";
		PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
		ResultSet resultSet1 = preparedStatement1.executeQuery();
		String[][] tableData = new String[j][2];
		while (resultSet1.next()) {
			tableData[i][0] = resultSet1.getString(1);
			tableData[i][1] = resultSet1.getString(2);
			i++;
		}
		return tableData;
	}
}
