package org.restaurant.people;

import org.restaurant.product.Food;
import org.restaurant.product.Menu;

public class Manager extends Employee {
    
    public Manager() {
        setHourlyWage(40);
        setType("Manager");
    }

    public Manager (String name, double hourlyWage, String username, String password) {
    	this.name = name;
    	this.hourlyWage = hourlyWage;
    	this.username = username;
    	this.password = password;
        setType("Manager");
    }
    
    // Hire a new Employee
    public void hireEmployee(Employee e) {
        this.restaurant.hireEmployee(e);
    }
    
    // Fire an Employee
    public void fireEmployee(Employee e) {
        this.restaurant.fireEmployee(e);
    }

    // Add new Food to the Menu
    public void addMenu(Menu menu, Food newItem){
    	if (menu.getFoodList().size() == 0) {
    		this.restaurant.addMenu(menu);
    	}
    	menu.addFood(newItem);
    }
    
    // Remove Food from the Menu
    public void removeMenuItem(Menu menu, Food item) {
    	menu.removeFood(item);
    	
    }

    // Adjust a chef's hourly wage
    public void adjustPay(Chef c, double p) {
        c.setHourlyWage(c.getHourlyWage() * (1 + p/100));
    }
}
