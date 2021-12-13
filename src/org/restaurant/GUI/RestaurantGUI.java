package org.restaurant.GUI;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.restaurant.product.Restaurant;

public class RestaurantGUI extends JFrame {
	
	Restaurant restaurant;
	private JLabel welcome;
	private Button feedback;
	private ButtonPanel buttonPanel;
	
	private ImageIcon image;
	private JLabel background;
	
	
	//constructor
	public RestaurantGUI(String windowName, Restaurant restaurant) {
		super(windowName);
		this.restaurant = restaurant;
		setSize(500,600);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		buildInformationZone();
		buttonPanel = new ButtonPanel(this);
		add(buttonPanel, BorderLayout.SOUTH);
		
		//Background image
		image = new ImageIcon(getClass().getResource("krustykrab.png"));
		background = new JLabel(image);
		add(background);
		
		setLocationRelativeTo(null);
		setVisible(true);
		buildWindowListener(this);	
	}
	
	// Getters and Setters
	public Restaurant getRestaurant() {
		return this.restaurant;
	}

	private void buildInformationZone() {
		JPanel feedbackZone = new JPanel();
		
		
		// Configure the layout for feedbackZone
		feedbackZone.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        
		welcome = new JLabel("<HTML><strong><center><h2>Welcome to " + restaurant.getName() + "</h2>" + 
				"<h3>" + restaurant.getAddress() + 
				"<BR>" + restaurant.getPhoneNum() + "</h3></center></strong></HTML>", SwingConstants.CENTER);
		feedbackZone.add(welcome, gbc);
		
		
		feedback = new Button("Reviews");
		feedback.setPreferredSize(new Dimension(40, 40));
		feedbackZone.add(feedback, gbc);
		
		feedback.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				ByteArrayOutputStream bstream = new ByteArrayOutputStream();
				PrintStream myPS = new PrintStream(bstream);
				restaurant.showReviewsGUI(myPS);
				String msg = new String(bstream.toByteArray());
				JOptionPane.showMessageDialog(null, msg, 
						"Reviews", JOptionPane.PLAIN_MESSAGE); 
			}  
		}); 
		add(feedbackZone, BorderLayout.NORTH);	
	}

	// Build a window listener to detect window closing
	public void buildWindowListener(JFrame frame) {
		frame.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent windowEvent) {
		    	Restaurant.saveData(restaurant);
		    }
		});
	}
}