package org.makerminds.internship.java.restaurantpoint.dataProvider.waiter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.makerminds.internship.java.restaurantpoint.database.DBMSConnection;
import org.makerminds.internship.java.restaurantpoint.login.controller.LoginController;

public class PrintInvoiceDataProvider {

	public static String[][] getRecords(int i, String tableId)
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
