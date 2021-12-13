package org.restaurant.software;

import java.io.Serializable;
import java.util.HashMap;

import org.restaurant.people.Employee;

public class EmployeeDatabase implements Serializable {
	
	private HashMap<String, Employee> loginDatabase;
	
	public EmployeeDatabase() {
		loginDatabase = new HashMap<>();
	}
	
	public void addMember(String username, Employee employee) {
		loginDatabase.put(username, employee);
	}
	
	public void removeMember(String username) {
		loginDatabase.remove(username);
	}
	
	public HashMap<String, Employee> getMap(){
		return this.loginDatabase;
	}
}
