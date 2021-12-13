package org.restaurant.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.restaurant.people.Customer;
import org.restaurant.product.Review;

class ReviewTest {

	@Test
	void testReview() {
		Review r1 = new Review();
		assertNull(r1.getReviewer());
		assertEquals("nothing", r1.getFeedback());
		assertEquals(0, r1.getRating());
	}

	@Test
	void testReviewCustomerStringInt() {
		Customer cu = new Customer();
		cu.setName("Son");
		Review r1 = new Review(cu, "Great", 5);
		assertEquals("Son", r1.getReviewer().getName());
		assertEquals("Great", r1.getFeedback());
		assertEquals(5, r1.getRating());
	}

	@Test
	void testSetRating() {
		Review r1 = new Review();
		assertEquals(0, r1.getRating());

		r1.setRating(4);
		assertEquals(4, r1.getRating());
	}

	@Test
	void testSetFeedback() {
		Review r1 = new Review();
		assertEquals("nothing", r1.getFeedback());

		r1.setFeedback("Great");
		assertEquals("Great", r1.getFeedback());
	}

	@Test
	void testSetReviewer() {
		Customer cu = new Customer();
		cu.setName("Son");
		Review r1 = new Review();
		assertNull(r1.getReviewer());
		r1.setReviewer(cu);
		assertNotNull(r1.getReviewer());
	}
}
