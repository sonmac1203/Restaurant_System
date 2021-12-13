package org.restaurant.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.restaurant.people.Chef;

class ChefTest {
	
	// Most functionalities have been tested in Manager Test
	@Test
	void testChef() {
		Chef chef = new Chef();
		assertEquals(0.0, chef.getHourlyWage(), 0.000001);
		assertEquals("Chef", chef.getType());
	}

	@Test
	void testChefStringDoubleStringString() {
		Chef chef = new Chef("Alex", 40.5, "alex", "password");
		assertEquals("Alex", chef.getName());
		assertEquals(40.5, chef.getHourlyWage(), 0.00001);
		assertEquals("alex", chef.getUsername());
		assertEquals("password", chef.getPassword());
	}

}
