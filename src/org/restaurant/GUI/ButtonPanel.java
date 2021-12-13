package org.restaurant.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class ButtonPanel extends JPanel {
	private Button customerButton;
	private Button employeeButton;
	private RestaurantGUI mainDisplay;
	
	public ButtonPanel(RestaurantGUI mainDisplay) {
		this.setMainDisplay(mainDisplay);
		setVisible(true);
		buildGUI();
	}
	
	public void buildGUI() {
		customerButton = new Button("Customer");
		employeeButton = new Button("Employee");
		
		customerButton.addActionListener(new ButtonListener());
		employeeButton.addActionListener(new ButtonListener());
		
		add(customerButton);
		add(employeeButton);			
	}
	
	public RestaurantGUI getMainDisplay() {
		return mainDisplay;
	}
	
	public void setMainDisplay(RestaurantGUI mainDisplay) {
		this.mainDisplay = mainDisplay;
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Button source = (Button)(e.getSource());
			if(source.equals(customerButton))
			{
				handleCustomerButton();	
			}
			else if(source.equals(employeeButton))
			{
				handleEmployeeButton();
			}
		}

		private void handleEmployeeButton() {
			new EmployeeGUI("Employee", getMainDisplay());
			mainDisplay.setVisible(false);
		}

		private void handleCustomerButton() {
			new CustomerLoginGUI("Customer Login", getMainDisplay());
			mainDisplay.setVisible(false);
		}
	}
}
