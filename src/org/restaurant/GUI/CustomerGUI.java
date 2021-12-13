package org.restaurant.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.restaurant.people.Customer;
import org.restaurant.product.Food;
import org.restaurant.product.Menu;
import org.restaurant.product.Review;

public class CustomerGUI extends JFrame{
	
	// Human-related objects and Display-related objects
	private RestaurantGUI mainDisplay;
	private CustomerLoginGUI loginDisplay;
	private Customer customer;
	
	// Welcome text and buttons
	private JLabel welcomeText;
	private Button logOutButton;
	private Button viewMenu;
	private Button makeOrder;
	private Button payBill;
	private Button writeReview;
	
	public CustomerGUI(String windowName, RestaurantGUI mainDisplay, CustomerLoginGUI loginDisplay, Customer customer) {
		super(windowName);
		this.mainDisplay = mainDisplay;
		this.loginDisplay = loginDisplay;
		this.customer = customer;
		
		welcomeText = new JLabel("Welcome back, " + this.customer.getName(), SwingConstants.CENTER);
		setSize(500,500);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		add(welcomeText, BorderLayout.NORTH);
		
		buildSignOutButton(); // make the Sign Out button
		buildGridButtons(); // make the 4-button grid
		mainDisplay.buildWindowListener(this);
	}
	
	// Getters and Setters
	public RestaurantGUI getMainDisplay() {
		return mainDisplay;
	}
	
	
	public void setMainDisplay(RestaurantGUI mainDisplay) {
		this.mainDisplay = mainDisplay;
	}
	
	// Configure the Sign Out button for the page
	public void buildSignOutButton() {
		logOutButton = new Button("Sign Out");
		
		logOutButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				setVisible(false);
				loginDisplay.setVisible(true);
			}  
		}); 
		add(logOutButton, BorderLayout.SOUTH);
	}
	
	// Configure the 4-button grid
	public void buildGridButtons() {
		
		// Initialize the buttons and the panels
		viewMenu = new Button("View Menu");
		makeOrder = new Button("Make Order");
		payBill = new Button("Make Payment");
		writeReview = new Button("Write Review");
        JPanel buttonPanel = new JPanel();
        JPanel containerPanel = new JPanel();
        
		viewMenu.addActionListener(new ButtonListener());
		makeOrder.addActionListener(new ButtonListener());
		payBill.addActionListener(new ButtonListener());
		writeReview.addActionListener(new ButtonListener());
        
        // Add everything together
        buttonPanel.setLayout(new GridLayout(2,2));
        buttonPanel.add(viewMenu);
        buttonPanel.add(makeOrder);
        buttonPanel.add(payBill);
        buttonPanel.add(writeReview);
        buttonPanel.setPreferredSize(new Dimension(400, 400));
        containerPanel.add(buttonPanel);
        getContentPane().add(containerPanel, BorderLayout.CENTER);
	}
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Button source = (Button)(e.getSource());
			
			if(source.equals(viewMenu))
			{
				handleViewMenu();	
			}
			else if(source.equals(makeOrder))
			{
				handleMakeOrder();
			}
			else if(source.equals(payBill))
			{
				handlePayBill();
			}
			else if(source.equals(writeReview))
			{
				handleWriteReview();
			}
		}

		// Fire an action when the customer clicks on Write Review
		private void handleWriteReview() {
			JTextField feedback = new JTextField(25);
			JSlider ratingSlider = new JSlider(0,5);
	        ratingSlider.setPaintTrack(true);
	        ratingSlider.setPaintTicks(true);
	        ratingSlider.setPaintLabels(true);
	        ratingSlider.setMajorTickSpacing(1);
			Object[] message = {
				    "Your feedback: ", feedback,
				    "Rating over 5: ", ratingSlider
			};
			int option = JOptionPane.showConfirmDialog(null, message, "Leave A Review", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				if (feedback.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "No feedback given! Please try again", 
							"Error", JOptionPane.PLAIN_MESSAGE); 
				}
				else {
					int customerRating = Integer.valueOf(ratingSlider.getValue());
					customer.writeReview(feedback.getText(), customerRating);
					JOptionPane.showMessageDialog(null, "Thank you for your feedback!", 
							"Success", JOptionPane.PLAIN_MESSAGE); 
				}
			}
		}

		// Fire an action when the customer clicks on Make Payment
		private void handlePayBill() {
			if (customer.getOrder().getCheck().size() > 0) {
				ByteArrayOutputStream bstream = new ByteArrayOutputStream();
				PrintStream myPS = new PrintStream(bstream);
				customer.printOrderGUI(myPS);
				String msg = new String(bstream.toByteArray());
				if (!customer.getBillPaid()) {
					msg += "\nClick OK to make payment";
					int option = JOptionPane.showConfirmDialog(null, msg, 
							"Order Information", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						customer.payBill();
						JOptionPane.showMessageDialog(null, "Thank you! The order has now been received", 
								"Order status", JOptionPane.PLAIN_MESSAGE);  
					}
				}
				else {
					msg += "\nOrder has been paid";
					JOptionPane.showMessageDialog(null, msg, 
							"Order status", JOptionPane.PLAIN_MESSAGE); 
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "No order has been made", 
						"Order status", JOptionPane.PLAIN_MESSAGE);
			}
		}

		// Fire an action when the customer clicks on Make Order
		private void handleMakeOrder() {
			JPanel checkBoxesGroup = new JPanel();
			checkBoxesGroup.setLayout(new BoxLayout(checkBoxesGroup, BoxLayout.Y_AXIS));
			ArrayList<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
			for (Menu menu: mainDisplay.getRestaurant().getMenuList()) {
				JPanel menuGroup = new JPanel();
				menuGroup.setLayout(new BoxLayout(menuGroup, BoxLayout.Y_AXIS));
				JLabel menuName = new JLabel(menu.getName());
				menuGroup.add(menuName);
				for (Food f: menu.getFoodList()) {
					JCheckBox checkBox = new JCheckBox(f.getName()); // Create a new check box
					checkBoxes.add(checkBox); // Adding to the list of all check boxes
					menuGroup.add(checkBox); // Add to menuGroup
				}
				checkBoxesGroup.add(menuGroup); // Add menuGroup to main display
			}
			
			int option = JOptionPane.showConfirmDialog(null, checkBoxesGroup, "Place an order", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				boolean selected = false;
				for (JCheckBox chosenFood: checkBoxes) {
					if (chosenFood.isSelected()) {
						selected = true;
						Food food = getFood(chosenFood.getText());
						ByteArrayOutputStream bstream = new ByteArrayOutputStream();
						PrintStream myPS = new PrintStream(bstream);
						customer.orderFoodGUI(food, myPS);
						String msg = new String(bstream.toByteArray());
						JOptionPane.showMessageDialog(null, msg, 
								"Order's status", JOptionPane.PLAIN_MESSAGE);  
					}
				}
				if (selected) {
					JOptionPane.showMessageDialog(null, "Thank you! Review the order in Make Payment", 
							"Order placed", JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "No option was chosen!", 
							"Order not placed", JOptionPane.PLAIN_MESSAGE);
				}
				
			}
		}

		// Fire an action when the customer clicks on View Menu
		private void handleViewMenu() {
			ByteArrayOutputStream bstream = new ByteArrayOutputStream();
			PrintStream myPS = new PrintStream(bstream);
			getMainDisplay().getRestaurant().printAllMenusGUI(myPS);
			String msg = new String(bstream.toByteArray());
			JOptionPane.showMessageDialog(null, msg, 
					"View All Menus", JOptionPane.PLAIN_MESSAGE);  
		}
	}
	
	// HELPER METHODS
	
	// Retrieve food from the system
	public Food getFood(String name) {
		for (Menu m: mainDisplay.getRestaurant().getMenuList()) {
			for (Food f: m.getFoodList()) {
				if (f.getName().equals(name)) return f;
			}
		}
		return null;
	}
}
