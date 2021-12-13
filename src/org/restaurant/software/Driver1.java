package org.restaurant.software;

import org.restaurant.people.Chef;
import org.restaurant.people.Customer;
import org.restaurant.people.Manager;
import org.restaurant.product.Food;
import org.restaurant.product.Menu;
import org.restaurant.product.Restaurant;

public class Driver1 {
	public static void main(String[] Args) {
		
		// Create the restaurant and a manager
		Restaurant r = new Restaurant();
		Manager m = new Manager();
		Manager m2 = new Manager();
		
		//Set and Get Name
		r.setName("Krusty Krab");
		
		//Set and Get Address
		r.setAddress("831 Bottom Feeder Lane, Bikini Bottom");
		r.setPhoneNum("(555) 555-5555");

		
		m.setName("Wilson");
		m.setRestaurant(r);
		
		
		Food f1 = new Food();
		Food f2 = new Food();
		Food f3 = new Food();
		Food f4 = new Food();
		Food f5 = new Food();
		Food f6 = new Food();
		Food f7 = new Food();
		Food f8 = new Food();
		
		Menu menu1 = new Menu();
		Menu menu2 = new Menu();
		
		Customer cu1 = new Customer();
		Customer cu2 = new Customer();
		Customer cu3 = new Customer();
		
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
		
		
		Chef c1 = new Chef();
		Chef c2 = new Chef();
		Chef c3 = new Chef();
		
		c1.setName("Spongebob");
		c2.setName("Squidward");
		c3.setName("Mr.Krabs"); 
		
		c1.setRestaurant(r);
		c2.setRestaurant(r);
		c3.setRestaurant(r);
				
		cu1.setName("Sandy");
		cu2.setName("Patrick");
		cu3.setName("Plankton");
		
		cu1.setAge(30);
		cu2.setAge(19);
		cu3.setAge(24);
		
		
		System.out.println("Welcome to " + r.getName());
		System.out.println("We are located at " + r.getAddress());
		
		System.out.println("\nHired Employees:");
		r.printEmployees();
		System.out.println();

		
		//Fire c3
		System.out.println("Hired Employees After Firing " + c3.getName() + " :");
		m.fireEmployee(c3);
		r.printEmployees();
		System.out.println();

		
		//Set Hourly wages
		c1.setHourlyWage(1);
		c2.setHourlyWage(2);
		c3.setHourlyWage(3);
		
		//Set Hours worked
		c1.setHoursWorked(1);
		c2.setHoursWorked(2);
		c3.setHoursWorked(3);
		
		
		System.out.println("Amount Earned by Chefs");
		System.out.println("c1's earnings: " + c1.getAmountEarned());
		System.out.println("c2's earnings: " + c2.getAmountEarned());
		System.out.println("c3's earnings: " + c3.getAmountEarned());
		System.out.println();

		
		//Give Raise
		double c1Raise = 20;
		double c2Raise = 50;
		double c3Raise = 100;
		
		m.adjustPay(c1, c1Raise);
		m.adjustPay(c2, c2Raise);
		m.adjustPay(c3, c3Raise);
		
		System.out.println("After Raise");
		System.out.println("c1 was given a " + c1Raise * 100 + " percent raise");
		System.out.println("c2 was given a " + c2Raise * 100 + " percent raise");
		System.out.println("c3 was given a " + c3Raise * 100 + " percent raise");
		System.out.println("c1's earnings: " + c1.getAmountEarned());
		System.out.println("c2's earnings: " + c2.getAmountEarned());
		System.out.println("c3's earnings: " + c3.getAmountEarned());
		
		//Give Reduction 
		double c1Reduct = -20;
		double c2Reduct = -50;
		double c3Reduct = -100;
		
		m.adjustPay(c1, c1Reduct);
		m.adjustPay(c2, c2Reduct);
		m.adjustPay(c3, c3Reduct);
		
		System.out.println("\n\nAfter Reduction");
		System.out.println("c1 was given a " + c1Reduct * 100 + " percent reduction");
		System.out.println("c2 was given a " + c2Reduct * 100 + " percent reduction");
		System.out.println("c3 was given a " + c3Reduct * 100 + " percent reduction");
		System.out.println("c1's earnings: " + c1.getAmountEarned());
		System.out.println("c2's earnings: " + c2.getAmountEarned());
		System.out.println("c3's earnings: " + c3.getAmountEarned());
		
		// Manager add food to menus
		m.addMenu(menu1, f1);
		m.addMenu(menu1, f2);
		m.addMenu(menu1, f3);
		
		m.addMenu(menu2, f4);
		m.addMenu(menu2, f5);
		m.addMenu(menu2, f6);
		m.addMenu(menu2, f7);
		
		System.out.println("\nAll menus the Restaurant is offering: ");
		r.printAllMenus();
		
		r.takeCustomer(cu1);
		r.takeCustomer(cu2);
		r.takeCustomer(cu3);
		
		System.out.println("Customers cu1 cu2 cu3 are taken: ");
		r.printCustomers();
		
		// Customer making orders
		System.out.println("\nCustomer cu1 making orders ...");
		cu1.orderFood(f8);
		cu1.orderFood(f1);
		cu1.orderFood(f4);
		
		System.out.println("\nCustomer cu2 making orders ...");
		cu2.orderFood(f7);
		cu2.orderFood(f2);
		cu2.orderFood(f5);
		
		System.out.println("\nCustomer cu3 making orders ...");
		cu3.orderFood(f7);
		cu3.orderFood(f3);
		cu3.orderFood(f6);
		cu3.orderFood(f3);
		
		
		//Orders received
		System.out.println("\nThe orders have NOT been received by the restaurant ...");
		r.printOrders();
		
		System.out.println("\nThe customers making payment ...");
		cu1.payBill();
		cu2.payBill();
		cu3.payBill();
		
		System.out.println("\nThe orders have now been received by the restaurant ...");
		r.printOrders();
		
		System.out.println("\nThe chefs are preparing the order ...");
		c1.cookFood(cu1, f4);
		
		System.out.println("\nThe orders of the restaurant after the chef starts to prepare food: ");
		r.printOrders();
		
		
		// Reviews
		cu1.writeReview("ABSOLUTELY AMAZING RESTAURANT!!!", 4);
		cu2.writeReview("I had a good time here. The food was good but the drinks were just not there.", 3);
		cu3.writeReview("Not a great place.", 1);
		
		System.out.println("\nThe Restaurant's feedback page ...\n");
		r.showReviews();
				
		//Hire and Fire Employee, Print Employees
		
		m2.setName("Mr. Krabs");

		
		System.out.println("After Hiring " + m2.getName() + ":");
		m2.setRestaurant(r);
		System.out.print("Employee List: ");
		r.printEmployees();

		//add and remove items from the menu as a manager	
		System.out.println("\nAll menus the Restaurant is offering currently: ");
		r.printAllMenus();
		
		m.removeMenuItem(menu1,f1);
		System.out.println("\nAll menus the Restaurant is offering after removing " + f1.getName() + " from Menu " + menu1.getName() + ":");
		r.printAllMenus();

	}
}
