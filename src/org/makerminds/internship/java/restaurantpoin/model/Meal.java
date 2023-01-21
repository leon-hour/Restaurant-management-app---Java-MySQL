package org.makerminds.internship.java.restaurantpoin.model;

/**
 * @author Leonora Latifaj
 *
 */
public class Meal extends Product{
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Meal(int productId, String name, double price, String description) {
		super(productId, name, price);
		this.description = description;
	}
	
}