package org.makerminds.internship.java.restaurantpoin.model;

/**
 * @author Leonora Latifaj
 *
 */
public class Table {
private int id;
private int nrOfSeats;
	public Table(int id, int nrOfSeats) {
		this.nrOfSeats = nrOfSeats;
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNrOfSeats() {
		return nrOfSeats;
	}
	public void setNrOfSeats(int seats) {
		this.nrOfSeats = seats;
	}

}
