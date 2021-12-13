package org.restaurant.product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.*;

import org.restaurant.people.Customer;
import org.restaurant.people.Employee;
import org.restaurant.software.EmployeeDatabase;
import org.restaurant.software.PhoneDatabase;

public class Restaurant implements Serializable {
    
    private String name;
    private String address;
    private HashSet<Menu> menuList;
    private String schedule;
    private String phoneNum;
    private double averageRating;
    private ArrayList<Employee> employeeList;
    private HashSet<Customer> customerList;
    private ArrayList<Review> reviews;
    private ArrayList<Order> foodOrders;
    
    private PhoneDatabase customerDatabase;
    private EmployeeDatabase employeeDatabase;
    
    
    
    public Restaurant() {
    	name = "unknown";
    	address = "unknown";
    	menuList = new HashSet<Menu>();
    	schedule = "unknown";
    	phoneNum = "unknown";
    	employeeList = new ArrayList<Employee>();
    	customerList = new HashSet<Customer>();
    	reviews = new ArrayList<Review>();
    	foodOrders = new ArrayList<Order>();
    	averageRating = 0.0;
    	customerDatabase = new PhoneDatabase();
    	employeeDatabase = new EmployeeDatabase();
    }
    
    // Getters and setter
    public PhoneDatabase getDatabase() {
    	return this.customerDatabase;
    }
    
    public EmployeeDatabase getEmployeeDatabase() {
    	return this.employeeDatabase;
    }

    public void setName(String n) {
        this.name = n;
    }

    public String getName() {
        return this.name;
    }

    public void setAddress(String a) {
        this.address = a;
    }

    public String getAddress() {
        return this.address;
    }

    public void setSchedule(String s) {
    	this.schedule = s;
    }
    
    public String getSchedule() {
    	return this.schedule;
    }
    
    public void setPhoneNum(String p) {
    	this.phoneNum = p;
    }
    
    public String getPhoneNum() {
    	return this.phoneNum;
    }
    
    public void addMenu(Menu m) {
        menuList.add(m);
    }

    public HashSet<Menu> getMenuList() {
        return this.menuList;
    }
    
    public ArrayList<Order> getOrders() {
    	return this.foodOrders;
    }
    
    public ArrayList<Employee> getEmployeeList() {
    	return this.employeeList;
    }
    
    public HashSet<Customer> getCustomerList() {
    	return this.customerList;
    }

    // Methods
    // Hire a new employee
    public void hireEmployee(Employee e) {
        employeeList.add(e);        
        employeeDatabase.addMember(e.getUsername(), e);
    }

    // Fire an employee
    public void fireEmployee(Employee e) {
        employeeList.remove(e);
        employeeDatabase.removeMember(e.getUsername());
    }

    // Print Employee list
    public void printEmployees() {
    	printEmployeesGUI(System.out);
    }
    
    // GUI version
    public void printEmployeesGUI(PrintStream s) {
        for (Employee e: employeeList) {
            s.println(e.getName() + " - " + e.getType());
        }
    }

    // Add a customer to list
    public void takeCustomer(Customer c) {
        this.customerList.add(c);
        this.customerDatabase.addMember(c.getPhoneNum(), c);
        c.setRestaurant(this);
    }

    // Print the customer list
    public void printCustomers() {
    	printCustomersGUI(System.out);
    }
    
    // GUI version
    public void printCustomersGUI(PrintStream s) {
    	for (Customer c: customerList) {
    		s.println(c.getName());
    	}
    }
    
    // Add a review to the system
    public void addReview(Review newReview) {
		this.reviews.add(newReview);
		
	}
	
    // Remove the review from the system
	public void removeReview(Review newReview) {
		this.reviews.remove(newReview);
	}
	
	// Print all reviews
	public void showReviews() {
		showReviewsGUI(System.out);
	}
	
	public void showReviewsGUI(PrintStream s) {
		if (reviews.size() > 0) {
			s.println("Average rating: " + calcAvgRating() + "/5\n");
			for (Review r: reviews) {
				s.println("Customer " + r.getReviewer().getName());
				s.println('"' + r.getFeedback() + '"');
				s.println(r.getRating() + "/5");
				s.println();
			}
		}
		else {
			s.println("No review to show");
		}
	}
	
	// Receive an order from the customer
	public void receiveOrder(Order order) {
		this.foodOrders.add(order);
		
	}
	
	// Print all orders
	public void printOrders() {
		printOrdersGUI(System.out);
	}
	
	// GUI version
	public void printOrdersGUI(PrintStream s) {
		if (foodOrders.size() > 0) {
			for (Order order: foodOrders) {
				s.println("Order of customer " + order.getCustomer().getName() + ":");
				for (Food f: order.getCheck()) {
					s.println(f.getName());
				}
				s.println();
			}
		}
		else {
			s.println("There is currently no order placed.");
		}
	}
	
	// Calculate average rating 
	public double calcAvgRating() {
		int sum = 0;
		for (Review r: reviews) {
			sum += r.getRating();
		}
		averageRating = 1.0 * sum / reviews.size();
		return averageRating;
	}
	
	// Print all offered menus
	public void printAllMenus() {
		printAllMenusGUI(System.out);
	}
	
	// GUI version
	public void printAllMenusGUI(PrintStream s) {
		for (Menu menu: menuList) {
			s.println(menu.getName() + " Menu: ");
			for (Food f: menu.getFoodList()) {
				s.println("- " + f.getName()  + " / $" + f.getPrice());
			}
			s.println();
		}
	}

	// Save data to .per file
	public static void saveData(Restaurant res) {
		try {
			File file = new File("./root/restaurant.ser");
			FileOutputStream stream = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(stream);
			out.writeObject(res);
			out.close();
			stream.close();

		} 
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// Load data from .per file
	public static Restaurant loadData() {
		Restaurant res = null;
		try {
			File file = new File("./root/restaurant.ser");
			FileInputStream stream = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(stream);
			res = (Restaurant)in.readObject();
			in.close();
			stream.close();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return res;
	}
}