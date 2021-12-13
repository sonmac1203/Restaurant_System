package org.restaurant.software;

import java.io.Serializable;
import java.util.HashMap;

import org.restaurant.people.Customer;

public class PhoneDatabase implements Serializable {
	
	private HashMap<String, Customer> loginDatabase;
	
	public PhoneDatabase() {
		loginDatabase = new HashMap<>();
	}
	
	public void addMember(String phoneNum, Customer customer) {
		loginDatabase.put(phoneNum, customer);
	}
	
	public HashMap<String, Customer> getMap(){
		return this.loginDatabase;
	}
}
