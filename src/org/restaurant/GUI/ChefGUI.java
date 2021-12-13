package org.restaurant.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.restaurant.people.Chef;
import org.restaurant.people.Customer;
import org.restaurant.product.Food;
import org.restaurant.product.Menu;
import org.restaurant.product.Order;

public class ChefGUI extends JFrame {
	
	// Human-related objects and Display-related objects
	private RestaurantGUI mainDisplay;
	private EmployeeGUI loginDisplay;
	private Chef chef;
	
	// Welcome text and Buttons
	private JLabel welcomeText;
	private Button logOutButton;
	private Button viewInfo;
	private Button viewOrder;
	private Button prepareOrder;
	private Button changePassword;
	
	public ChefGUI(String windowName, RestaurantGUI mainDisplay, EmployeeGUI loginDisplay, Chef chef) {
		super(windowName);
		this.mainDisplay = mainDisplay;
		this.loginDisplay = loginDisplay;
		this.chef = chef;
		
		welcomeText = new JLabel("Welcome back, chef " + this.chef.getName(), SwingConstants.CENTER);
		
		setSize(500,500);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		add(welcomeText, BorderLayout.NORTH);
		
		
		buildGridButtons();	// make the 4-button grid
		buildSignOutButton(); // make the Sign Out button
		mainDisplay.buildWindowListener(this);
	}
	
	// Configure the Sign Out button
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
		viewInfo = new Button("Profile");
		viewOrder = new Button("View Orders");
		prepareOrder = new Button("Prepare order");
		changePassword = new Button("Change Password");
        JPanel buttonPanel = new JPanel();
        JPanel containerPanel = new JPanel();
        
        // Implement action listener
		viewInfo.addActionListener(new ButtonListener());
		viewOrder.addActionListener(new ButtonListener());
		prepareOrder.addActionListener(new ButtonListener());
		changePassword.addActionListener(new ButtonListener());
        
        // Add everything together
        buttonPanel.setLayout(new GridLayout(2,2));
        buttonPanel.add(viewInfo);
        buttonPanel.add(viewOrder);
        buttonPanel.add(prepareOrder);
        buttonPanel.add(changePassword);
        buttonPanel.setPreferredSize(new Dimension(400, 400));
        containerPanel.add(buttonPanel);
        getContentPane().add(containerPanel, BorderLayout.CENTER);
	}
	
	
	private class ButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Button source = (Button)(e.getSource());
			
			if(source.equals(viewInfo))
			{
				handleViewInfo();	
			}
			else if(source.equals(viewOrder))
			{
				handleViewOrder();
			}
			else if(source.equals(prepareOrder))
			{
				handlePrepareOrder();
			}
			else if(source.equals(changePassword))
			{
				handleChangePassword();
			}
		}

		// Fire an action when the chef clicks on Change Password
		private void handleChangePassword() {
			JTextField password = new JTextField(20);
			Object[] msg = {
					"New password", password
			};
			int option = JOptionPane.showConfirmDialog(null, msg, "Change password", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				if (password.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "Please enter a new password", 
							"Error", JOptionPane.PLAIN_MESSAGE); 
				}
				else {
					if (chef.getPassword().equals(password.getText())) {
						JOptionPane.showMessageDialog(null, "Password in use! Please try again", 
								"Error", JOptionPane.PLAIN_MESSAGE); 
					} else {
						chef.setPassword(password.getText());
						JOptionPane.showMessageDialog(null, "Password updated!", 
								"Success", JOptionPane.PLAIN_MESSAGE); 
					}
				}
			}
		}

		// Fire an action when chef clicks on Prepare Order
		private void handlePrepareOrder() {
			
			if (mainDisplay.getRestaurant().getOrders().size() == 0) {
				JOptionPane.showMessageDialog(null, "No order to display", 
						"Status", JOptionPane.PLAIN_MESSAGE); 
			}
			else {
				JPanel container = new JPanel(); // contains the whole display
				container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
				
				HashMap<Customer, ArrayList<JCheckBox>> groups = new HashMap<>();
				
				for (Order order: mainDisplay.getRestaurant().getOrders()) {
					JPanel orderGroup = new JPanel(); // A group holds an order information
					orderGroup.setLayout(new BoxLayout(orderGroup, BoxLayout.Y_AXIS));
					JLabel orderName = new JLabel("Order of " + order.getCustomer().getName());
					orderGroup.add(orderName);
					
					ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
					for (Food f: order.getCheck()) {
						JCheckBox checkBox = new JCheckBox(f.getName()); // Create a new check box
						checkBoxes.add(checkBox); // Adding to the list of all check boxes
						orderGroup.add(checkBox); // Add to menuGroup
					}
					groups.put(order.getCustomer(), checkBoxes);
					container.add(orderGroup);
				}
				
				int option = JOptionPane.showConfirmDialog(null, container, "Prepare", JOptionPane.OK_CANCEL_OPTION);
				
				if (option == JOptionPane.OK_OPTION) {
					boolean selected = false;
					for (Customer customer: groups.keySet()) {
						for (JCheckBox chosenFood: groups.get(customer)) {
							if (chosenFood.isSelected()) {
								selected = true;
								Food f = getFood(chosenFood.getText());
								chef.cookFood(customer, f);
							}
						}
					}
					if (selected) {
						JOptionPane.showMessageDialog(null, "Success!", 
								"Status", JOptionPane.PLAIN_MESSAGE); 
					}
					else {
						JOptionPane.showMessageDialog(null, "Must choose an option!", 
								"Error", JOptionPane.PLAIN_MESSAGE); 
					}
				}
			}
		}

		// Fire an action when the chef clicks on View Order
		private void handleViewOrder() {
			ByteArrayOutputStream bstream = new ByteArrayOutputStream();
			PrintStream myPS = new PrintStream(bstream);
			mainDisplay.getRestaurant().printOrdersGUI(myPS);
			String msg = new String(bstream.toByteArray());
			JOptionPane.showMessageDialog(null, msg, 
					"View All Orders", JOptionPane.PLAIN_MESSAGE); 

		}

		// Fire an action when the chef clicks on View Info
		private void handleViewInfo() {
			ByteArrayOutputStream bstream = new ByteArrayOutputStream();
			PrintStream myPS = new PrintStream(bstream);
			chef.viewInfoGUI(myPS);
			String msg = new String(bstream.toByteArray());
			JOptionPane.showMessageDialog(null, msg, 
					"View Profile", JOptionPane.PLAIN_MESSAGE); 
		}
		
		// HELPER METHODS
		
		// Retrieve a Food object from the system
		public Food getFood(String name) {
			for (Menu m: mainDisplay.getRestaurant().getMenuList()) {
				for (Food f: m.getFoodList()) {
					if (f.getName().equals(name)) return f;
				}
			}
			return null;
		}
	}
}
