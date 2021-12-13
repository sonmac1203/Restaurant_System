package org.restaurant.people;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.*;

import org.restaurant.product.Restaurant;

public abstract class Employee implements Serializable {
    protected Restaurant restaurant;
    protected String name;
    protected String type;
    protected double hourlyWage;
    protected Integer hoursWorked;

    protected String username;
    protected String password;
    
    public Employee(){
    	restaurant = null;
        name = "unknown";
        type = "unknown";
        hourlyWage = 0.0;
        hoursWorked = 0;
        username = "";
        password = "";
    }

    // Getters and Setters
    public void setRestaurant(Restaurant r) {
    	this.restaurant = r;
    	r.hireEmployee(this);
    }
    
    public Restaurant getRestaurant() {
    	return this.restaurant;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setHourlyWage(double newHourlyWage){
        this.hourlyWage = newHourlyWage;
    }
    public double getHourlyWage(){
        return this.hourlyWage;
    }

    public void setHoursWorked(Integer newHoursWorked){
        this.hoursWorked = newHoursWorked;
    }
    public Integer getHoursWorked(){
        return this.hoursWorked;
    }
    
    // Return the amount of money earned an hour
    public double getAmountEarned(){
        return hoursWorked*hourlyWage;
    }    
    
    // View the employee's info
    public void viewInfo() {
    	viewInfoGUI(System.out);
    }
    
    // GUI version
    public void viewInfoGUI(PrintStream s) {
    	s.println(this.name + "'s information:");
    	s.println("- Position: " + this.type);
    	s.println("- Username: " + this.username);
    	s.println("- Hourly salary: $" + this.hourlyWage);
    }
    
}