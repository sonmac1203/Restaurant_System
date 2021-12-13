package org.restaurant.GUI;

import java.awt.Dimension;

import javax.swing.JButton;

public class Button extends JButton {
	private final int BUTTON_WIDTH = 100;
	private final int BUTTON_HEIGHT = 50;
	
	public Button(String buttonName) {
		super(buttonName);
		this.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
	}
}
