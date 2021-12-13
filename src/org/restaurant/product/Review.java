package org.restaurant.product;

import java.io.Serializable;

import org.restaurant.people.Customer;

public class Review implements Serializable {
	//characteristics
	private String feedback;
	private Customer reviewer;
	private int rating;

	public Review(){
		reviewer = null;
		feedback = "nothing";
		rating = 0;
	}
	
	public Review(Customer reviewer, String feedback, int rating) {
		this.reviewer = reviewer;
		this.feedback = feedback;
		this.rating = rating;
	}
	
	// Getters and setters
	public void setRating(int newRating) {
		if( (newRating > 5) || (newRating < 1) )
			System.out.println("not allowed");
		else
			this.rating =  newRating;
	}
	
	public double getRating() {
		return this.rating;
	}
	
	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Customer getReviewer() {
		return reviewer;
	}

	public void setReviewer(Customer reviewer) {
		this.reviewer = reviewer;
	}
}


