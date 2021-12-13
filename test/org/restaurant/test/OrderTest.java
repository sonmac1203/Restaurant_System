package org.restaurant.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.restaurant.people.Customer;
import org.restaurant.product.Food;
import org.restaurant.product.Order;

class OrderTest {

	@Test
	void testOrder() {
		Customer cu = new Customer();
		Order order = new Order(cu);
		assertEquals(0.0, order.getTotalPrice(), 0.0001);
		assertEquals(0, order.getCheck().size());
	}

	@Test
	void testSetCustomer() {
		Customer cu = new Customer();
		cu.setName("Son");
		Order order = new Order(cu);
		assertEquals("Son", order.getCustomer().getName());
		
		Customer cu1 = new Customer();
		cu1.setName("Ann");
		order.setCustomer(cu1);
		assertEquals("Ann", order.getCustomer().getName());
	}

	@Test
	void testAddFoodToOrder() {
		Customer cu = new Customer();
		cu.setName("Son");
		Order order = new Order(cu);
		assertEquals(0, order.getCheck().size());
		Food f = new Food();
		order.addFoodToOrder(f);
		assertEquals(1, order.getCheck().size());
	}

	@Test
	void testRemoveFoodFromOrder() {
		Customer cu = new Customer();
		cu.setName("Son");
		Order order = new Order(cu);
		Food f = new Food();
		order.addFoodToOrder(f);
		assertEquals(1, order.getCheck().size());
		
		order.removeFoodFromOrder(f);
		assertEquals(0, order.getCheck().size());
	}
}
