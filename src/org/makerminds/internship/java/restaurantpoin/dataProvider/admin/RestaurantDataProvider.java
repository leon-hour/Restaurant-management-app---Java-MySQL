package org.makerminds.internship.java.restaurantpoin.dataProvider.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.makerminds.internship.java.restaurantpoint.database.DBMSConnection;

/**
 * @author Leonora Latifaj
 *
 */
public class RestaurantDataProvider {

    public static List<String> getDatabaseList() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
	    List<String> dataBaseNameList = new ArrayList<String>();
	    DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/" , "root", "Leonora.MM21");
		Connection connection = dbmsConnection.getConnection();
	    try {
	        ResultSet resultSet = connection.getMetaData().getCatalogs();
	        if (resultSet == null) {
	            return null;
	        }
	        while (resultSet.next()) {
	            dataBaseNameList.add(resultSet.getString(1));
	        }
	        return dataBaseNameList;
	    } catch (SQLException e) {
	    }
	    dbmsConnection.closeConnection(connection, null);
	    return dataBaseNameList;
	}
	
}
