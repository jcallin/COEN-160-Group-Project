package simulation;

import java.awt.Dimension;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class RMOSLeft extends JPanel implements ActionListener{
	
	// Login components
	JTextField userNameInput;
	JLabel loggedInAs;
	JButton loginButton;
	
	// Adding new item components
	JTextField newItemName, newItemPrice;
	JButton newItemButton;
	
	// Changing existing item components
	JTextField  changeItemName, changeItemPrice;
	JButton changeItemButton;
	
	// Get RCM info components
	JTextField RCMIdInput;
	JButton getRCMInfoButton;
	
	public RMOSLeft(){
		// Create box layout inside for each row of buttons
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setPreferredSize(new Dimension(400,450));
		
		// Create inputs for logging in
		userNameInput = new JTextField("Enter Username");
		userNameInput.setPreferredSize(new Dimension(300, 30));
		this.add(userNameInput);
		
		loginButton = new JButton("Login");
		loginButton.addActionListener(this);
		loginButton.setPreferredSize(new Dimension(90, 30));
		this.add(loginButton);
		
		loggedInAs = new JLabel("Not logged in", SwingConstants.CENTER);
		loggedInAs.setPreferredSize(new Dimension(390, 30));
		this.add(loggedInAs);
		
		newItemName = new JTextField("New Item");
		newItemName.setPreferredSize(new Dimension(130, 30));
		newItemPrice = new JTextField("Price");
		newItemPrice.setPreferredSize(new Dimension(100, 30));
		newItemButton = new JButton("Add");
		newItemButton.setPreferredSize(new Dimension(130, 30));
		newItemButton.addActionListener(this);
		
		this.add(newItemName);
		this.add(newItemPrice);
		this.add(newItemButton);
		
		changeItemName = new JTextField("Item");
		changeItemName.setPreferredSize(new Dimension(130, 30));
		changeItemPrice = new JTextField("Price");
		changeItemPrice.setPreferredSize(new Dimension(100, 30));
		changeItemButton = new JButton("Change");
		changeItemButton.setPreferredSize(new Dimension(130, 30));
		changeItemButton.addActionListener(this);
		
		this.add(changeItemName);
		this.add(changeItemPrice);
		this.add(changeItemButton);
		
		// Create inputs for getting RCM info
		RCMIdInput = new JTextField("Enter ID of RCM");
		RCMIdInput.setPreferredSize(new Dimension(170, 30));
		getRCMInfoButton = new JButton("Get Info");
		getRCMInfoButton.setPreferredSize(new Dimension(190, 30));
		this.add(RCMIdInput);
		this.add(getRCMInfoButton);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.loginButton){
			// Add an item
		}
		if (e.getSource() == this.newItemButton){
			// Add an item
		}
		else if (e.getSource() == this.changeItemButton){
			// Change the price of an item
		}
		else if (e.getSource() == this.getRCMInfoButton){
			// Get RCM info and display in text popup
		}
		
	}
	
}
