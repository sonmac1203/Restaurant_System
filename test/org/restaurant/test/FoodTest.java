package org.restaurant.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.restaurant.product.Food;
import org.restaurant.product.Menu;

class FoodTest {

	@Test
	void testFood() {
		Food f = new Food();
		assertEquals("unknown food", f.getName());
		assertEquals(0.0, f.getPrice(), 0.0001);
		assertNull(f.getMenu());
		assertFalse(f.isRequire21());
	}

	@Test
	void testFoodStringDouble() {
		Food f = new Food("Orange chicken", 12.5);
		assertEquals("Orange chicken", f.getName());
		assertEquals(12.5, f.getPrice(), 0.0001);
	}

	@Test
	void testSetName() {
		Food f = new Food();
		assertEquals("unknown food", f.getName());
		
		f.setName("Cocktail");
		assertEquals("Cocktail", f.getName());
	}

	@Test
	void testSetPrice() {
		Food f = new Food();
		assertEquals(0.0, f.getPrice(), 0.0001);
		
		f.setPrice(9.99);
		assertEquals(9.99, f.getPrice(), 0.0001);
	}

	@Test
	void testSetMenu() {
		Menu menu = new Menu();
		Food f = new Food();
		assertNull(f.getMenu());
		
		f.setMenu(menu);
		assertNotNull(f.getMenu());
	}

	@Test
	void testSetRequire21() {
		Food f = new Food();
		assertFalse(f.isRequire21());
		
		f.setRequire21(true);
		assertTrue(f.isRequire21());
	}

}
