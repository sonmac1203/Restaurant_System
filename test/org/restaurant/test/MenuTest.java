package org.restaurant.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.restaurant.product.Food;
import org.restaurant.product.Menu;

class MenuTest {

	@Test
	void testMenu() {
		Menu menu = new Menu();
		assertEquals("unknown", menu.getName());
	}

	@Test
	void testMenuString() {
		Menu menu = new Menu("Asian Cuisine");
		assertEquals("Asian Cuisine", menu.getName());
	}

	@Test
	void testSetName() {
		Menu menu = new Menu();
		assertEquals("unknown", menu.getName());
		
		menu.setName("Drinks");
		assertEquals("Drinks", menu.getName());
	}

	@Test
	void testAddFood() {
		Menu menu = new Menu();
		assertEquals(0, menu.getFoodList().size());
		
		Food f = new Food();
		menu.addFood(f);
		assertEquals(1, menu.getFoodList().size());
	}

	@Test
	void testRemoveFood() {
		Menu menu = new Menu();		
		Food f1 = new Food();
		Food f2 = new Food();
		menu.addFood(f1);
		menu.addFood(f2);
		assertEquals(2, menu.getFoodList().size());
		menu.removeFood(f2);
		assertEquals(1, menu.getFoodList().size());
	}

}
