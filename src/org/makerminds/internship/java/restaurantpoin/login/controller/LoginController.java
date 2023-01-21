package org.makerminds.internship.java.restaurantpoin.login.controller;


import org.makerminds.internship.java.restaurantpoin.dataProvider.UserDataProvider;
import org.makerminds.internship.java.restaurantpoin.login.model.User;

/**
 * @author Leonora Latifaj
 *
 */
public class LoginController {
	
	private static final LoginController INSTANCE = new LoginController();

	private UserDataProvider userDataProvider = new UserDataProvider();
	private User loggedInUser = null;
	
	private LoginController() {
	}
	
	public User getLoggedInUser() {
		return loggedInUser;	
	}
	
	/**
	 * @param name
	 * @param password
	 */
	public void logInUser(String name, String password) {
		for(User user:userDataProvider.getUselList()) {
			if(user.getName().equals(name) && 
					user.getPassword().equals(password)) {
				loggedInUser = user;
			}
		}
	}
	
	public static LoginController getInstance() {
		return INSTANCE;
	}
}