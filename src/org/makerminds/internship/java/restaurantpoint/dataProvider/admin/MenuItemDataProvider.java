package org.makerminds.internship.java.restaurantpoint.dataProvider.admin;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.makerminds.internship.java.restaurantpoint.database.DBMSConnection;

/**
 * @author Leonora Latifaj
 *
 */
public class MenuItemDataProvider {

	public static String[][] getRecord(int i, String dataBaseName, String tableName) throws InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException, FileNotFoundException {
DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/" + dataBaseName, "root", "Leonora.MM21");
Connection connection = dbmsConnection.getConnection();
String sql1 = "select * from " + tableName + ";";
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
	
}
