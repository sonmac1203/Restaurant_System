package org.restaurant.people;

import org.restaurant.product.Food;
import org.restaurant.product.Order;

public class Chef extends Employee {
    
    public Chef() {
    	this.hourlyWage = 0.0;
    	setType("Chef");
    }
    
    public Chef (String name, double hourlyWage, String username, String password) {
    	this.name = name;
    	this.hourlyWage = hourlyWage;
    	this.username = username;
    	this.password = password;
    	setType("Chef");
    }
    
    // Cook from a customer's order
    public void cookFood(Customer customer, Food f){
    	for (Order order: this.getRestaurant().getOrders()) {
    		if (order.getCustomer().getName().equals(customer.getName())) {
    			order.removeFoodFromOrder(f);
    		}
    	}
    }
}
