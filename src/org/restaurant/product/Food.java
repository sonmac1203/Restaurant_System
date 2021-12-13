package org.restaurant.product;
import java.io.Serializable;
import java.util.ArrayList;

public class Food implements Serializable {
	//characteristics
	private String name;
	private double price;
	private boolean require21;
	private ArrayList<String> description;
	private Menu menu;
	
	// Default constructor
	public Food() {
		name = "unknown food";
		price = 0.0;
		description = new ArrayList<String>();
		menu = null;
		require21 = false;
	}
	
	// Parameterized constructor
	public Food(String name, double d) {
		this.name = name;
		this.price = d;
		description = new ArrayList<String>();
		menu = null;
		require21 = false;
	}

	// Getters and setters
	public String getName() {
		return this.name;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public void setPrice(double newPrice) {
		this.price = newPrice;
	}
	
	public Menu getMenu() {
		return menu;
	}
	
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	public boolean isRequire21() {
		return require21;
	}
	

	// Add a new description
	public void addDescription(Integer position, String newDescription){
		this.description.add(position, newDescription);
	}

	// Remove a description
	public void removeDescription(String d){
		this.description.remove(d);

	}

	// Set require21
	public void setRequire21(boolean b) {
		require21 = b;
	}
}



