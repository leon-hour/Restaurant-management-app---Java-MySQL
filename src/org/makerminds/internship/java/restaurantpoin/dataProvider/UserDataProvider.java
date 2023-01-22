package org.makerminds.internship.java.restaurantpoin.dataProvider;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.makerminds.internship.java.restaurantpoin.login.model.User;
import org.makerminds.internship.java.restaurantpoin.login.model.UserRole;
import org.makerminds.internship.java.restaurantpoint.database.DBMSConnection;

/**
 * @author Leonora Latifaj
 *
 */
public class UserDataProvider {
	private static List<User> userList = new ArrayList<>();

	public List<User> getUselList() {
		return userList;
	}

	public UserDataProvider() {
			userList.add(new User("1","1","1","route66",UserRole.MANAGER,"1"));
			userList.add(new User("2","2","2","route66",UserRole.WAITER,"2"));
			userList.add(new User("3","3","3","route66",UserRole.COOK,"3"));
			try {
				getRecord();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | FileNotFoundException
					| SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public static void getRecord() throws InstantiationException, IllegalAccessException, ClassNotFoundException,
			SQLException, FileNotFoundException {
		DBMSConnection dbmsConnection = new DBMSConnection("jdbc:mysql://localhost:3306/employees", "root", "Leonora.MM21");
		Connection connection = dbmsConnection.getConnection();
		String sql1 = "select * from employees;";
		PreparedStatement preparedStatement = connection.prepareStatement(sql1);
		;
		ResultSet resultSet = preparedStatement.executeQuery(sql1);
		while (resultSet.next()) {
			if (resultSet.getString(5).equals("WAITER"))
				userList.add(new User(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), UserRole.WAITER, resultSet.getString(6)));
			else if (resultSet.getString(5).equals("COOK"))
				userList.add(new User(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), UserRole.COOK, resultSet.getString(6)));
			else
				userList.add(new User(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4),UserRole.MANAGER, resultSet.getString(6)));
		}
	}
}
