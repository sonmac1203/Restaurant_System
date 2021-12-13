package org.restaurant.product;

import java.io.PrintStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.restaurant.people.Customer;

public class Order implements Serializable {
	private Customer customer;
	private ArrayList<Food> check;
	private double totalPrice;
	
	public Order(Customer customer) {
		setCustomer(customer);
		check = new ArrayList<Food>();
		totalPrice = 0.0;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ArrayList<Food> getCheck() {
		return check;
	}

	public void addFoodToOrder(Food f) {
		this.check.add(f);
	}
	
	public void removeFoodFromOrder(Food f) {
		this.check.remove(f);
	}

	// Calculate total price of the order
	public double getTotalPrice() {
		double price = 0;
		for (Food f: check) {
			price += f.getPrice();
		}
		this.totalPrice = price;
		return totalPrice;
	}
	
	// Print the order
	public void showOrder() {
		showOrderGUI(System.out);
	}
	
	// GUI version
	public void showOrderGUI(PrintStream s) {
		s.println("Order of " + customer.getName());
		for (Food f: check) {
			s.println(f.getName());
		}
		DecimalFormat df = new DecimalFormat("#.###");
		s.println("Total: " + df.format(getTotalPrice()));
	}
}
