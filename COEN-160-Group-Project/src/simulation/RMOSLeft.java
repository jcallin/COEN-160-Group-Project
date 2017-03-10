package simulation;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class RMOSLeft extends JPanel implements ActionListener{
	
	//RCMs that the RMOS is managing
	private RCM rcm1;
	private RCM rcm2;
	
	// Login components
	private ArrayList<String> users = new ArrayList<String>();
	private JTextField userNameInput;
	private JLabel loggedInAs;
	private JButton loginButton;
	private boolean loggedIn;
	
	// Adding new item components
	private JTextField newItemName, newItemPrice;
	private JButton newItemButton;
	private Item newItem;
	
	// Changing existing item components
	// private JComboBox<String> itemSelector; -- Possibly have a selector to chose which item to change price of
	private JTextField  changeItemName, changeItemPrice;
	private JButton changeItemButton;
	
	// Get RCM info components
	private JTextField RCMIdInput;
	private JButton getRCMInfoButton;
	
	public RMOSLeft(RCM r1, RCM r2){
		// Create box layout inside for each row of buttons
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setPreferredSize(new Dimension(400,450));
		
		rcm1 = r1;
		rcm2 = r2;
		
		// Add default admins
		users.add("Julian Callin");
		users.add("Jack Roof");
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
			System.out.println(this.userNameInput.getText());
			if(loginButton.getText() == "Log Out"){
				loggedIn = false;
				JOptionPane.showMessageDialog(null, "Logged Out");
				loginButton.setText("Log In");
				this.userNameInput.setText("Enter username");
				loggedInAs.setText("Not logged in");
			}
			else{
				for(int i = 0; i < this.users.size(); i++){
					if(this.users.get(i).equals(this.userNameInput.getText())){
						JOptionPane.showMessageDialog(null, "Welcome " + this.users.get(i));
						loggedInAs.setText("Currently Logged in as: " + this.userNameInput.getText());
						loggedIn = true;
						loginButton.setText("Log Out");
						return;
					}
				}
				JOptionPane.showMessageDialog(null, "Unknown user");
			}
		}
		if (e.getSource() == this.newItemButton){
			if(loggedIn == false){
				JOptionPane.showMessageDialog(null, "Please login before proceeding");
			}
			else{
				newItem = new Item(newItemName.getText(), Double.parseDouble(newItemPrice.getText()), Math.random()*10);
				this.rcm1.addItem(newItem);
				this.rcm2.addItem(newItem);
			}
		}
		else if (e.getSource() == this.changeItemButton){
			if(loggedIn == false){
				JOptionPane.showMessageDialog(null, "Please login before proceeding");
			}
			else{
				// Change the price of an item
			}
		}
		else if (e.getSource() == this.getRCMInfoButton){
			if(loggedIn == false){
				JOptionPane.showMessageDialog(null, "Please login before proceeding");
			}
			else {
				// Get RCM info and display in text popup
			}
		}
		
	}
	
}
