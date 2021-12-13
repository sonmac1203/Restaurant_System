package org.restaurant.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.restaurant.people.Manager;
import org.restaurant.product.Restaurant;

class ManagerTest {

	@Test
	void testManager() {
		Manager manager = new Manager();
		assertNull(manager.getRestaurant());
		assertEquals("unknown", manager.getName());
		assertEquals("Manager", manager.getType());
		assertEquals(40.0, manager.getHourlyWage(), 0.00001);
		assertEquals("", manager.getUsername());
		assertEquals("", manager.getPassword());
	}

	@Test
	void testManagerStringDoubleStringString() {
		Manager manager = new Manager("Wilson", 42.0, "wilson", "password");
		assertEquals("Wilson", manager.getName());
		assertEquals(42.0, manager.getHourlyWage(), 0.000001);
		assertEquals("wilson", manager.getUsername());
		assertEquals("password", manager.getPassword());
	}

	@Test
	void testSetRestaurant() {
		Manager manager = new Manager();
		assertNull(manager.getRestaurant());
		
		Restaurant r = new Restaurant();
		manager.setRestaurant(r);
		assertNotNull(manager.getRestaurant());
	}
	
	@Test
	void testSetName() {
		Manager manager = new Manager();
		assertEquals("unknown", manager.getName());
		
		manager.setName("Son");
		assertEquals("Son", manager.getName());
	}
	
	@Test
	void testSetUsername() {
		Manager manager = new Manager();
		assertEquals("", manager.getUsername());
		
		manager.setUsername("thienson");
		assertEquals("thienson", manager.getUsername());
	}
	
	@Test
	void testSetPassword() {
		Manager manager = new Manager();
		assertEquals("", manager.getPassword());
		
		manager.setPassword("password");
		assertEquals("password", manager.getPassword());
	}
	
	@Test
	void testSetHourlyWage() {
		Manager manager = new Manager();
		assertEquals(40.0, manager.getHourlyWage(), 0.00001);
		
		manager.setHourlyWage(51.0);
		assertEquals(51.0, manager.getHourlyWage(), 0.00001);
	}
}
