package org.restaurant.product;

import java.io.Serializable;
import java.util.ArrayList;

public class Menu implements Serializable {
	private String name;
	private ArrayList<Food> foodList;
	
	public Menu() {
		setName("unknown");
		setFoodList(new ArrayList<Food>());
	}
	
	public Menu(String name) {
		setName(name);
		setFoodList(new ArrayList<Food>());
	}

	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Food> getFoodList() {
		return foodList;
	}

	public void setFoodList(ArrayList<Food> foodList) {
		this.foodList = foodList;
	}
	
	// Methods
	// Add new food to menu
	public void addFood(Food food) {
		this.foodList.add(food);
		food.setMenu(this);
	}
	
	// Remove food from menu
	public void removeFood(Food food) {
		this.foodList.remove(food);
	}
}
