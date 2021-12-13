package org.restaurant.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.restaurant.people.Chef;
import org.restaurant.people.Employee;
import org.restaurant.people.Manager;
import org.restaurant.product.Food;
import org.restaurant.product.Menu;
import org.restaurant.software.EmployeeDatabase;

public class ManagerGUI extends JFrame {
	// Human-related objects and Display objects
	private RestaurantGUI mainDisplay;
	private EmployeeGUI loginDisplay;
	private Manager manager;
	
	// Text and buttons
	private JLabel welcomeText;
	private Button hireEmployee;
	private Button fireEmployee;
	private Button addMenuItem;
	private Button removeMenuItem;
	private Button printCustomerList;
	private Button printEmployeeList;
	private Button printMenus;
	private Button printOrders;
	private Button adjustPay;
	private Button logOutButton;
	
	public ManagerGUI(String windowName, RestaurantGUI mainDisplay, EmployeeGUI loginDisplay, Manager manager) {
		super(windowName);
		this.mainDisplay = mainDisplay;
		this.loginDisplay = loginDisplay;
		this.manager = manager;
		
		welcomeText = new JLabel("Welcome back, manager " + this.manager.getName(), SwingConstants.CENTER);
		
		setSize(500,500);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		add(welcomeText, BorderLayout.NORTH);
		
		
		buildGridButtons(); // make the 9-button grid
		buildSignOutButton(); // make the Sign Out Button
		mainDisplay.buildWindowListener(this);
	}
	
	// Getters and Setters
	public RestaurantGUI getMainDisplay() {
		return mainDisplay;
	}
	
	public void setMainDisplay(RestaurantGUI mainDisplay) {
		this.mainDisplay = mainDisplay;
	}
	
	// Configure the Sign Out Button
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

	// Configure the grid of 9 buttons
	private void buildGridButtons() {
		hireEmployee = new Button("Hire");
		fireEmployee = new Button("Fire");
		addMenuItem = new Button("Add Food");
		removeMenuItem = new Button("Remove Food");
		adjustPay = new Button("Adjust Pay");
		printCustomerList = new Button("View Customers");
		printEmployeeList = new Button("View Employees");
		printMenus = new Button("View Menus");
		printOrders = new Button("View Orders");
		
        JPanel buttonPanel = new JPanel();
        JPanel containerPanel = new JPanel();
        
        printCustomerList.addActionListener(new ButtonListener());
        printOrders.addActionListener(new ButtonListener());
        printMenus.addActionListener(new ButtonListener());
        
        printEmployeeList.addActionListener(new ButtonListener());
        addMenuItem.addActionListener(new ButtonListener());
        removeMenuItem.addActionListener(new ButtonListener());
        
        hireEmployee.addActionListener(new ButtonListener());
        fireEmployee.addActionListener(new ButtonListener());
        adjustPay.addActionListener(new ButtonListener());
        
        // Add everything together
        buttonPanel.setLayout(new GridLayout(3,3));
        buttonPanel.add(printCustomerList);
        buttonPanel.add(printOrders);
        buttonPanel.add(printMenus);
        
        buttonPanel.add(printEmployeeList);
        buttonPanel.add(addMenuItem);
        buttonPanel.add(removeMenuItem);
        
        buttonPanel.add(hireEmployee);
        buttonPanel.add(fireEmployee);
        buttonPanel.add(adjustPay);
        
        buttonPanel.setPreferredSize(new Dimension(400, 400));
        containerPanel.add(buttonPanel);
        getContentPane().add(containerPanel, BorderLayout.CENTER);
	}
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Button source = (Button)(e.getSource());
			
			if(source.equals(printCustomerList))
			{
				handlePrintCustomerList();	
			}
			else if(source.equals(printOrders))
			{
				handlePrintOrders();
			}
			else if(source.equals(printMenus))
			{
				handlePrintMenus();
			}
			else if(source.equals(printEmployeeList))
			{
				handlePrintEmployeeList();
			}
			else if(source.equals(addMenuItem))
			{
				handleAddMenuItem();
			}
			else if(source.equals(removeMenuItem))
			{
				handleRemoveMenuItem();
			}
			else if(source.equals(hireEmployee))
			{
				handleHireEmployee();
			}
			else if(source.equals(fireEmployee))
			{
				handleFireEmployee();
			}
			else if(source.equals(adjustPay))
			{
				handleAdjustPay();
			}
		}

		// Fire an action when the manager clicks Adjust Pay
		private void handleAdjustPay() {	
			JPanel employeeOptions = new JPanel();
			employeeOptions.setLayout(new BoxLayout(employeeOptions, BoxLayout.Y_AXIS));
			ArrayList<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
			for (Employee employee : mainDisplay.getRestaurant().getEmployeeList()) {
				if (employee.getType().equals("Chef")) {
					String employeeInfo  = employee.getName() + " - $" + String.format("%.2f",employee.getHourlyWage()) + "/h";
					JCheckBox checkBox = new JCheckBox(employeeInfo);
					checkBoxes.add(checkBox);
					employeeOptions.add(checkBox);
				}
			}
			JTextField newHourlyWage = new JTextField(25);
			
			Box box = Box.createHorizontalBox();
			ButtonGroup group = new ButtonGroup();
			JRadioButton increase = new JRadioButton("Increase");
			JRadioButton decrease = new JRadioButton("Decrease");
			group.add(increase);
			group.add(decrease);
			box.add(increase);
			box.add(decrease);
			Object[] message = {"Employee Options:", employeeOptions, 
								"New Hourly Earning Percentage: ", newHourlyWage,
								"Pay Increase or Decrease ", box
								};
			int option = JOptionPane.showConfirmDialog(null, message, "Adjust Pay", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				if (!hasError(checkBoxes, newHourlyWage, increase, decrease)) {
					int sign = increase.isSelected() ? 1 : -1;
					for (JCheckBox optionChef : checkBoxes) {
						if (optionChef.isSelected()) {
							String chefName = optionChef.getText().split("-")[0].trim();
							Chef chef = getChef(chefName);
							manager.adjustPay(chef, sign * Double.valueOf(newHourlyWage.getText()));
						}
					}
					String type = increase.isSelected() ? "increased" : "decreased";
					JOptionPane.showMessageDialog(null,
							"The chosen chefs' pay had " + type + " by " + newHourlyWage.getText() + "%", "Success",
							JOptionPane.PLAIN_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null,
							"One of the inputs is missing!", "Error", JOptionPane.PLAIN_MESSAGE);	
				}
			}
		}

		// Fire an action when the manager clicks Fire
		private void handleFireEmployee() {
			JPanel checkBoxesGroup = new JPanel();
			checkBoxesGroup.setLayout(new BoxLayout(checkBoxesGroup, BoxLayout.Y_AXIS));
			ArrayList<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
			for (Employee employee: mainDisplay.getRestaurant().getEmployeeList()) {
				if (employee.getType().equals("Chef")) {
					JCheckBox checkBox = new JCheckBox(employee.getName());
					checkBoxes.add(checkBox);
					checkBoxesGroup.add(checkBox);
				}
			}
			int option = JOptionPane.showConfirmDialog(null, checkBoxesGroup, "Fire a chef", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				boolean selected = false;
				for (JCheckBox optionChef: checkBoxes) {
					if (optionChef.isSelected()) {
						selected = true;
						Chef chef = getChef(optionChef.getText());
						manager.fireEmployee(chef);
					}
				}
				if (selected) {
					JOptionPane.showMessageDialog(null, "The chosen chefs have been fired!", 
							"Success", JOptionPane.PLAIN_MESSAGE); 
				} else {
					JOptionPane.showMessageDialog(null, "Please choose at least a chef!", 
							"Error", JOptionPane.PLAIN_MESSAGE); 
				}
				
			}
		}

		// Fire an action when the manager clicks Hire
		private void handleHireEmployee() {
			JTextField name = new JTextField(25);
			JTextField hourlyWage = new JTextField(25);
			JTextField username = new JTextField(25);
			JTextField password = new JTextField(25);
			ButtonGroup group = new ButtonGroup();
			JRadioButton manager = new JRadioButton("Manager");
			JRadioButton chef = new JRadioButton("Chef");
			group.add(manager);
			group.add(chef);
			
			Object[] message = {
				    "Name: ", name,
				    "Hourly earning: ", hourlyWage,
				    "Username", username,
				    "Password: ", password,
				    "Type: ", manager, chef
			};
			int option = JOptionPane.showConfirmDialog(null, message, "Hire an employee", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				if (missingTextError(name, hourlyWage, username, password)) {
					JOptionPane.showMessageDialog(null, "One of the inputs is missing!", 
							"Error", JOptionPane.PLAIN_MESSAGE); 
				} else {
					EmployeeDatabase db = mainDisplay.getRestaurant().getEmployeeDatabase();
					if (db.getMap().containsKey(username.getText())) {
						JOptionPane.showMessageDialog(null, "This username has already been registered!", 
								"Error", JOptionPane.PLAIN_MESSAGE); 
					}
					else {
						if (chef.isSelected()) {
							Chef newChef = new Chef(name.getText(), Double.valueOf(hourlyWage.getText()), username.getText(), password.getText());
							newChef.setRestaurant(mainDisplay.getRestaurant());
							JOptionPane.showMessageDialog(null, name.getText() + " has been hired as a new Chef!", 
									"Success", JOptionPane.PLAIN_MESSAGE); 
						}
						
						else if (manager.isSelected()) {
							Manager newManager = new Manager(name.getText(), Double.valueOf(hourlyWage.getText()), username.getText(), password.getText());
							newManager.setRestaurant(mainDisplay.getRestaurant());
							JOptionPane.showMessageDialog(null, name.getText() + " has been hired as a new Manager!", 
									"Success", JOptionPane.PLAIN_MESSAGE); 
						}
						else {
							JOptionPane.showMessageDialog(null, "Please choose a type for the new employee!", 
									"Error", JOptionPane.PLAIN_MESSAGE); 
						}
					}
				}
			}
		}

		// Fire an action when the manager clicks Remove Item
		private void handleRemoveMenuItem() {
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
						manager.removeMenuItem(food.getMenu(), food);
					}
				}
				if (selected) {
					JOptionPane.showMessageDialog(null, "Item has been removed!", 
							"Success", JOptionPane.PLAIN_MESSAGE); 
				}
				else {
					JOptionPane.showMessageDialog(null, "Please choose an option!", 
							"Error", JOptionPane.PLAIN_MESSAGE); 
				}
			}
		}

		// Fire an action when the manager clicks Add Menu Item
		private void handleAddMenuItem() {
			JTextField name = new JTextField(25);
			JTextField price = new JTextField(25);
			JTextField menu = new JTextField(25);
			ButtonGroup group = new ButtonGroup();
			JRadioButton over21 = new JRadioButton("Yes");
			JRadioButton notOver21 = new JRadioButton("No");
			group.add(over21);
			group.add(notOver21);
			Object[] message = {"Name: ", name, 
								"Price: ", price, 
								"Over 21?", over21, notOver21, 
								"Which Menu? (Menu will be added if not existed)", menu
								};

			int option = JOptionPane.showConfirmDialog(null, message, "Add Food", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				if (missingTextError(name,price,menu, new JTextField("dummy"))) {
					JOptionPane.showMessageDialog(null, "One of the inputs is missing!", 
							"Error", JOptionPane.PLAIN_MESSAGE); 
				}
				else {
					Food f = new Food(name.getText(), Double.valueOf(price.getText()));
					f.setRequire21(over21.isSelected());
					String menuName = menu.getText();
					Menu newMenu = getMenu(menuName);
					String msg = "Menu " + menu.getText() + " updated!";
					if (newMenu == null) {
						newMenu = new Menu(menuName);
						msg = "Menu " + menu.getText() + " added!";
					}
					manager.addMenu(newMenu, f);
					JOptionPane.showMessageDialog(null, msg, 
							"Success", JOptionPane.PLAIN_MESSAGE); 
				}
			}
		}

		// Fire an action when the manager clicks Print Employee
		private void handlePrintEmployeeList() {
			ByteArrayOutputStream bstream = new ByteArrayOutputStream();
			PrintStream myPS = new PrintStream(bstream);
			getMainDisplay().getRestaurant().printEmployeesGUI(myPS);
			String msg = new String(bstream.toByteArray());
			JOptionPane.showMessageDialog(null, msg, 
					"View All Employees", JOptionPane.PLAIN_MESSAGE); 
		}

		// Fire an action when the manager clicks Print Menus
		private void handlePrintMenus() {
			ByteArrayOutputStream bstream = new ByteArrayOutputStream();
			PrintStream myPS = new PrintStream(bstream);
			getMainDisplay().getRestaurant().printAllMenusGUI(myPS);
			String msg = new String(bstream.toByteArray());
			JOptionPane.showMessageDialog(null, msg, 
					"View All Menus", JOptionPane.PLAIN_MESSAGE); 
		}

		// Fire an action when the manager clicks Print Orders
		private void handlePrintOrders() {
			ByteArrayOutputStream bstream = new ByteArrayOutputStream();
			PrintStream myPS = new PrintStream(bstream);
			getMainDisplay().getRestaurant().printOrdersGUI(myPS);
			String msg = new String(bstream.toByteArray());
			JOptionPane.showMessageDialog(null, msg, 
					"View All Orders", JOptionPane.PLAIN_MESSAGE); 
		}

		// Fire an action when the manager clicks Customer List
		private void handlePrintCustomerList() {
			ByteArrayOutputStream bstream = new ByteArrayOutputStream();
			PrintStream myPS = new PrintStream(bstream);
			if (getMainDisplay().getRestaurant().getCustomerList().size() > 0) {				
				getMainDisplay().getRestaurant().printCustomersGUI(myPS);
				String msg = new String(bstream.toByteArray());
				JOptionPane.showMessageDialog(null, msg, 
						"View All Customers", JOptionPane.PLAIN_MESSAGE);  
			}
			else {
				JOptionPane.showMessageDialog(null, "No active customer at the moment", 
						"View All Customers", JOptionPane.PLAIN_MESSAGE);  
			}
		}
		
		// HELPER METHODS
		
		// Retrieve a chef from the system
		public Chef getChef(String name) {
			for (Employee employee: mainDisplay.getRestaurant().getEmployeeList()) {
				if (employee.getType().equals("Chef")) {
					if (employee.getName().equals(name))
						return (Chef)employee;
				}
			}
			return null;
		}
		
		// Retrieve food from the system
		public Food getFood(String name) {
			for (Menu m: mainDisplay.getRestaurant().getMenuList()) {
				for (Food f: m.getFoodList()) {
					if (f.getName().equals(name)) return f;
				}
			}
			return null;
		}
		
		// Retrieve a menu from the system
		public Menu getMenu(String name) {
			for (Menu m: mainDisplay.getRestaurant().getMenuList()) {
				if (m.getName().equals(name)) {
					return m;
				}
				
			}
			return null;
		}
		
		// Detect error in input fields
		public boolean hasError (ArrayList<JCheckBox> employees, JTextField amount, JRadioButton raise, JRadioButton cut) {
			boolean selected = false;
			for (JCheckBox option : employees) {
				if (option.isSelected()) {
					selected = true;
					break;
				}
			}
			return amount.getText().trim().equals("") || !(raise.isSelected() || cut.isSelected()) || !selected;
		}
		
		public boolean missingTextError(JTextField f1, JTextField f2, JTextField f3, JTextField f4) {
			return f1.getText().trim().length() == 0 || 
					f2.getText().trim().length() == 0 || 
					f3.getText().trim().length() == 0 ||
					f4.getText().trim().length() == 0;
		}
	}
}
