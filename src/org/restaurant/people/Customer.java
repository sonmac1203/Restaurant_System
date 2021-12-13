package org.restaurant.people;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;

import org.restaurant.product.Food;
import org.restaurant.product.Menu;
import org.restaurant.product.Order;
import org.restaurant.product.Restaurant;
import org.restaurant.product.Review;

public class Customer implements Serializable {
	//characteristics
	private Restaurant restaurant;
	private String name;
	private ArrayList<Food> check;
	private Order order;
	private Review review;
	private String phoneNum;
	private Integer age;
    private double billAmount;
	private boolean billPaid;

	
	// default constructor
	public Customer() {
		setRestaurant(null);
        name = "unknown";
        order = new Order(this);
        review = new Review();
        check =  new ArrayList<Food>(); 
        phoneNum = "unknown";
        age = 0;
		billAmount = 0.0;
        billPaid = false;	 
	}

	// parameterized constructor
	public Customer(String name, String phoneNum, Integer age){
		setRestaurant(null);
		this.name = name;
        order = new Order(this);
        review = new Review();
        check =  new ArrayList<Food>(); 
		this.phoneNum = phoneNum;
		this.age = age;
		billAmount = 0.0;
		billPaid = false;
	}

	// Setters and Getters
	public String getName() {
		return this.name;
	}
	
	public void setName(String newName) {
		this.name =  newName;
	}
	
	public String getPhoneNum() {
		return this.phoneNum;
	}
	
	public void setPhoneNum(String newPhoneNumber) {
		this.phoneNum = newPhoneNumber;
	}
	
	public int getAge() {
		return this.age;
	}

	public void setAge(Integer newAge) {
		this.age = newAge;
	}

	public ArrayList<Food> getCheck(){
		return this.check;
	}
	
	public boolean getBillPaid(){
		return this.billPaid;
	}
	
	public Restaurant getRestaurant() {
		return restaurant;
	}
	
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	public Order getOrder() {
		return this.order;
	}
	

	// Order new food and add that food into the list
	public void orderFood(Food food){
		orderFoodGUI(food, System.out);
	}
	
	// GUI version
	public void orderFoodGUI(Food food, PrintStream s){
		if (!isOffered(food)) {
			s.println("Sorry, " + food.getName() + " is not offered in any of our menus. Please choose a different one.");
		}
		else if (isOrdered(food)) {
			s.println("Sorry, " + food.getName() + " has already been ordered by you. Please choose a different one.");
		}
		else {
			if(this.age < 21 && food.isRequire21()) {
				s.println("You can only order " + food.getName() + " if you are 21 or older. Please choose a different one.");
			}
			else {
				s.println("Success! An order of " + food.getName() + " has been added to check");
				this.order.addFoodToOrder(food);
			}
		}
	}
	
	// Calculate the total bill based on order made
    public double totalBill() {
		this.billAmount = this.order.getTotalPrice();
        return this.billAmount;
    }

    // Pay the bill so that the restaurant will receive the order
    public void payBill() {
    	this.billPaid = true;
    	System.out.println("The bill for customer " + this.getName() + " has been paid and your order has been received. Thank you!");
    	this.restaurant.receiveOrder(this.order);
    }

    // Check if customer is over 21 or not
	public boolean checkRequire21(){
		return this.age >= 21;
	}
	
	// Write a review for the restaurant
	public void writeReview(String feedback, int rating) {
		review = new Review(this, feedback, rating);
		this.restaurant.addReview(this.review);
	}
	
	// Print out the order made
	public void printOrderGUI(PrintStream s) {
		order.showOrderGUI(s);
	}

	// HELPER METHODS
	
	// Check if the order has been placed already
	public boolean isOrdered(Food f) {
		if (check.size() > 0) {
			for (Food orderedFood: check) {
				if (f.getName().equals(orderedFood.getName()))
					return true;
			}
		}
		return false;
	}
	
	// Check if the food is offered or not
	public boolean isOffered(Food f) {
		for (Menu m: this.getRestaurant().getMenuList()) {
			for (Food food: m.getFoodList()) {
				if (food.getName().equals(f.getName())) {
					return true;
				}
			}
		}
		return false;
	}
}

