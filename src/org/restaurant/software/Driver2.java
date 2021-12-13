package org.restaurant.software;

import org.restaurant.GUI.RestaurantGUI;
import org.restaurant.people.Chef;
import org.restaurant.people.Manager;
import org.restaurant.product.Food;
import org.restaurant.product.Menu;
import org.restaurant.product.Restaurant;

public class Driver2 {

	public static void main(String[] args) {
		
		// Create the restaurant and a manager
		Restaurant r = new Restaurant();
		Manager m = new Manager();
		
		//Set and Get Name
		r.setName("Krusty Krab"); 
		
		//Set and Get Address
		r.setAddress("831 Bottom Feeder Lane, Bikini Bottom");
		r.setPhoneNum("(555) 555-5555");

		
		m.setName("Wilson");
		m.setUsername("wilson");
		m.setPassword("password");
		m.setRestaurant(r);
		
		
		Food f1 = new Food();
		Food f2 = new Food();
		Food f3 = new Food();
		Food f4 = new Food();
		Food f5 = new Food();
		Food f6 = new Food();
		Food f7 = new Food();
		
		Menu menu1 = new Menu();
		Menu menu2 = new Menu();
				
		f1.setName("Krabby Patty"); 
		f2.setName("Sailors Surprise");
		f3.setName("Salty Sea Dog"); 
		
		menu1.setName("Signature"); 
		menu2.setName("Drinks"); 
		
		f4.setName("Kelp Shake");
		f5.setName("Seafoam Soda");
		f6.setName("Pineapple Under the Sea"); 
		f7.setName("Tropical Mix");
		f7.setRequire21(true);
		
		f1.setPrice(12.99);
		f2.setPrice(15.99);
		f3.setPrice(13.99);
		f4.setPrice(7);
		f5.setPrice(8.5);
		f6.setPrice(9);
		f7.setPrice(10);
		
		Chef c1 = new Chef();
		Chef c2 = new Chef();
		Chef c3 = new Chef();
		
		c1.setName("Spongebob");
		c2.setName("Squidward");
		c3.setName("Mr.Krabs"); 
		
		c1.setUsername("chef1");
		c1.setPassword("chef1password");
		c2.setUsername("chef2");
		c2.setPassword("chef2password");
		c3.setUsername("chef3");
		c3.setPassword("chef3password");
		
		c1.setRestaurant(r);
		c2.setRestaurant(r);
		c3.setRestaurant(r);
		
		c1.setHourlyWage(41);
		c2.setHourlyWage(50);
		c3.setHourlyWage(49);
				
		m.addMenu(menu1, f1);
		m.addMenu(menu1, f2);
		m.addMenu(menu1, f3);
		
		m.addMenu(menu2, f4);
		m.addMenu(menu2, f5);
		m.addMenu(menu2, f6);
		m.addMenu(menu2, f7);
		
		if (Restaurant.loadData() != null) {
			r = Restaurant.loadData();
		}
		new RestaurantGUI("Restaurant System", r);
	}
}
