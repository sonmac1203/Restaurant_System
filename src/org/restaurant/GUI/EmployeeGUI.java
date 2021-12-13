package org.restaurant.GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.restaurant.people.Chef;
import org.restaurant.people.Manager;
import org.restaurant.software.EmployeeDatabase;


public class EmployeeGUI extends JFrame {
	// Human-related objects and Display objects
	private RestaurantGUI mainDisplay;
	
	// Buttons and text fields
	private Button backButton;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private Button signInButton;
	
	public EmployeeGUI(String windowName, RestaurantGUI mainDisplay) {
		super(windowName);
		setMainDisplay(mainDisplay);
		setSize(500,500);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);	
		setVisible(true);
		
		buildLoginField(); // build the log in field
		buildBackButton(); // make the Back button
		mainDisplay.buildWindowListener(this);
	}


	// Getters and Setters
	public RestaurantGUI getMainDisplay() {
		return mainDisplay;
	}

	public void setMainDisplay(RestaurantGUI mainDisplay) {
		this.mainDisplay = mainDisplay;
	}
	
	// Configure Log in field and handle entered data
	private void buildLoginField() {
		
        JPanel signInPanel = new JPanel();
        usernameField = new JTextField(17);
        passwordField = new JPasswordField(17);
        signInButton = new Button("Sign In");
     
		signInButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				String username = usernameField.getText();
				String password = new String(passwordField.getPassword());
				EmployeeDatabase db = mainDisplay.getRestaurant().getEmployeeDatabase();
				if (db != null && db.getMap().containsKey(username) && db.getMap().get(username).getPassword().equals(password)) {
					setVisible(false);
					if(db.getMap().get(username).getType().equals("Manager")) {
						new ManagerGUI("Manager", getMainDisplay(), EmployeeGUI.this, (Manager)db.getMap().get(username));
					}
					else if(db.getMap().get(username).getType().equals("Chef")) {
						new ChefGUI("Chef", getMainDisplay(), EmployeeGUI.this, (Chef)db.getMap().get(username));
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Incorrect username or password! Please try again",
							"Error", JOptionPane.PLAIN_MESSAGE);
				}	
			}  
		}); 
		signInPanel.add(usernameField);
		signInPanel.add(passwordField);
		signInPanel.add(signInButton);
        getContentPane().add(signInPanel, BorderLayout.CENTER);
		
	}
	
	// Configure the Back button for the display
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
}
