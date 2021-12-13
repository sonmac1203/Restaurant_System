package org.restaurant.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.restaurant.people.Customer;
import org.restaurant.product.Restaurant;
import org.restaurant.software.PhoneDatabase;

public class CustomerLoginGUI extends JFrame {
	
	private RestaurantGUI mainDisplay;
	private JTextField phoneNumberField;
	private Button signInButton;
	private Button signUpButton;
	private Button backButton;
	
	public CustomerLoginGUI (String windowName, RestaurantGUI mainDisplay) {
		super(windowName);
		this.setMainDisplay(mainDisplay);
		setSize(500,500);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

		buildLoginField();		
		buildBackButton();
		mainDisplay.buildWindowListener(this);
	}




	// Getters and Setters
	public RestaurantGUI getMainDisplay() {
		return mainDisplay;
	}
	
	
	public void setMainDisplay(RestaurantGUI mainDisplay) {
		this.mainDisplay = mainDisplay;
	}
	
	// Configure the Back button for the page
	public void buildBackButton() {
		backButton = new Button("Back");
		
		backButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				setVisible(false);
				getMainDisplay().setVisible(true);
			}  
		}); 
		add(backButton, BorderLayout.SOUTH);
	}
	
	// Configure Log In field
	private void buildLoginField() {
		JLabel label = new JLabel("Phone number");
        JPanel signInPanel = new JPanel();
        JPanel mainPanel = new JPanel();
        
        phoneNumberField = new JTextField(20);
		signUpButton = new Button("Sign Up");
        signInButton = new Button("Sign In");
        
		signUpButton.addActionListener(new ButtonListener());
		signInButton.addActionListener(new ButtonListener());
		

		signInPanel.add(phoneNumberField);
        signInPanel.add(signInButton);
        
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setAlignmentY(CENTER_ALIGNMENT);
        mainPanel.add(label);
        mainPanel.add(signInPanel);
        mainPanel.add(signUpButton);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
	}
		
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Button source = (Button)(e.getSource());
			if(source.equals(signInButton))
			{
				handleSignInButton();	
			}
			else if(source.equals(signUpButton))
			{
				handleSignUpButton();
			}
		}

		// Fire an action when the customer clicks on Sign Up
		private void handleSignUpButton() {
			JTextField customerName = new JTextField(25);
			JTextField customerAge = new JTextField(25);
			JTextField phoneNumber = new JTextField(25);
			Object[] message = {
				    "Name:", customerName,
				    "Age:", customerAge,
				    "Phone Number:", phoneNumber
			};
			int option = JOptionPane.showConfirmDialog(null, message, "Sign Up", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				PhoneDatabase db = getMainDisplay().getRestaurant().getDatabase();
				if (db != null && db.getMap().containsKey(phoneNumber.getText())) {
					JOptionPane.showMessageDialog(null, "This phone number has been registered. Please sign in!",
							"Phone number registered", JOptionPane.PLAIN_MESSAGE);
				}
				else {
					Customer newMember = new Customer(customerName.getText(), phoneNumber.getText(), Integer.valueOf(customerAge.getText()));
					db.addMember(newMember.getPhoneNum(), newMember);
					JOptionPane.showMessageDialog(null, "Success! You are now a member of " + getMainDisplay().getRestaurant().getName(),
							"Success", JOptionPane.PLAIN_MESSAGE);
				}
			}
		}

		// Fire an action when the customer clicks on Sign In
		private void handleSignInButton() {
			String phoneNumber = phoneNumberField.getText();
			PhoneDatabase db = getMainDisplay().getRestaurant().getDatabase();
			if (db.getMap().containsKey(phoneNumber)) {
				getMainDisplay().getRestaurant().takeCustomer(db.getMap().get(phoneNumber));
				setVisible(false);
				new CustomerGUI("Customer", getMainDisplay(), CustomerLoginGUI.this, db.getMap().get(phoneNumber));
			}
			else {
				JOptionPane.showMessageDialog(null, "Phone number not found! Please sign up and become a member of " + getMainDisplay().getRestaurant().getName(),
						"Not found", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
}
