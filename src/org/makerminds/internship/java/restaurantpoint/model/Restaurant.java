package org.makerminds.internship.java.restaurantpoint.model;

/**
 * @author Leonora Latifaj
 *
 */
public class Restaurant {
private String name;
private String address;
	public Restaurant(String name, String address) {
		this.address = address;
		this.name = name;
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
