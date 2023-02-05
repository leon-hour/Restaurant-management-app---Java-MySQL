package org.makerminds.internship.java.restaurantpoint.model;

/**
 * @author Leonora Latifaj
 *
 */
public class Product {

	private int productId;
	private String name;
	private double price;

	public Product(int productId, String name, double price) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
	}
	public int getProductId() {
		return productId;
	}
	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}
}
