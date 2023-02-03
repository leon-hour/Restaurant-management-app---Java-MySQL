package org.makerminds.internship.java.restaurantpoin.dataProvider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.makerminds.internship.java.restaurantpoint.database.DBMSConnection;

/**
 * @author Leonora Latifaj
 *
 */
public class MenuDataProvider {

	public static List<String> getTabelList(String dataBaseName)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/" + dataBaseName, "root", "Leonora.MM21");
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
