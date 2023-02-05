package org.makerminds.internship.java.restaurantpoint.login.model;

import org.makerminds.internship.java.restaurantpoint.enums.UserRole;

/**
 * @author Leonora Latifaj
 *
 */
public class User {
	private String name;
	private String lastName;
	private String password;
	private UserRole userRole;
	private String restaurant;
	
	public User(String id, String name,String lastName, String restaurant, UserRole userRole , String password) {
		this.name = name;
		this.password = password;
		this.userRole = userRole;
		this.restaurant = restaurant;
		this.lastName= lastName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserRole getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

}
