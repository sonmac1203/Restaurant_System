package org.restaurant.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.restaurant.people.Customer;
import org.restaurant.product.Restaurant;

class CustomerTest {

	@Test
	void testCustomer() {
		Customer c1 = new Customer();
		assertNull(c1.getRestaurant());
		assertEquals("unknown", c1.getName());
		assertEquals("unknown", c1.getPhoneNum());
		assertEquals(0, c1.getAge());
		assertFalse(c1.getBillPaid());
	}

	@Test
	void testCustomerStringStringInteger() {
		Customer c1 = new Customer("Son", "1234567899", 20);
		assertEquals("Son", c1.getName());
		assertEquals("1234567899", c1.getPhoneNum());
		assertEquals(20, c1.getAge());
	}

	@Test
	void testSetName() {
		Customer c1 = new Customer();
		assertEquals("unknown", c1.getName());
		c1.setName("Son");
		assertEquals("Son", c1.getName());
	}

	@Test
	void testSetPhoneNum() {
		Customer c1 = new Customer();
		assertEquals("unknown", c1.getPhoneNum());
		c1.setPhoneNum("1234567899");
		assertEquals("1234567899", c1.getPhoneNum());
	}

	@Test
	void testSetAge() {
		Customer c1 = new Customer();
		assertEquals(0, c1.getAge());
		c1.setAge(20);
		assertEquals(20, c1.getAge());
	}

	@Test
	void testSetRestaurant() {
		Restaurant res = new Restaurant();
		Customer c1 = new Customer();
		assertNull(c1.getRestaurant());
		
		c1.setRestaurant(res);
		assertNotNull(c1.getRestaurant());
	}

}
