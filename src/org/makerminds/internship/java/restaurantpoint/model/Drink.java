package org.makerminds.internship.java.restaurantpoint.model;

/**
 * @author Leonora Latifaj
 *
 */
public class Drink extends Product{

	private boolean sugarFree;

	public Drink(int productId, String name, double price, String productType, boolean sugarFree) {
		super(productId, name, price);
	}

	public boolean isSugarFree() {
		return sugarFree;
	}

	public void setSugarFree(boolean sugarFree) {
		this.sugarFree = sugarFree;
	}	
	
}