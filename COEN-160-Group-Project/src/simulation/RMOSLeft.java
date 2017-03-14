package simulation;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
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
	private JLabel newItemLabel;
	private JTextField newItemName, newItemPrice;
	private JButton newItemButton;
	
	// Changing existing item components
	// private JComboBox<String> itemSelector; -- Possibly have a selector to chose which item to change price of
	private JLabel changeItemLabel;
	private JTextField  changeItemName, changeItemPrice;
	private JButton changeItemButton;
	
	// Get RCM info components
	private JLabel infoLabel;
	private JTextField RCMIdInput;
	private JButton getRCMInfoButton;
	
	//RCMempty components
	private JLabel emptyLabel;
	private JTextField emptyId;
	private JButton EmptyButton;
	
	//RCMmoney refill components
	private JLabel refillLabel;
	private JTextField RCMMoneyRefillId;
	private JButton moneyRefillButton;
	
	//Last RCMempty components
	private JLabel timeEmptyLabel;
	private JTextField timeEmptyId;
	private JButton timeEmptyButton;
	
	public RMOSLeft(RCM r1, RCM r2){
		// Create box layout inside for each row of buttons
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setPreferredSize(new Dimension(450,450));
		
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
		
		//Create inputs for adding new item
		newItemLabel = new JLabel("New Item: ");
		newItemLabel.setPreferredSize(new Dimension(70, 30));
		newItemName = new JTextField("Name");
		newItemName.setPreferredSize(new Dimension(130, 30));
		newItemPrice = new JTextField("Price");
		newItemPrice.setPreferredSize(new Dimension(100, 30));
		newItemButton = new JButton("Add");
		newItemButton.setPreferredSize(new Dimension(75, 30));
		newItemButton.addActionListener(this);
		this.add(newItemLabel);
		this.add(newItemName);
		this.add(newItemPrice);
		this.add(newItemButton);
		
		//Create inputs for changing price of a current item
		changeItemLabel = new JLabel("Change Item: ");
		changeItemLabel.setPreferredSize(new Dimension(90, 30));
		changeItemName = new JTextField("Name");
		changeItemName.setPreferredSize(new Dimension(130, 30));
		changeItemPrice = new JTextField("New Price");
		changeItemPrice.setPreferredSize(new Dimension(100, 30));
		changeItemButton = new JButton("Change");
		changeItemButton.setPreferredSize(new Dimension(75, 30));
		changeItemButton.addActionListener(this);
		this.add(changeItemLabel);
		this.add(changeItemName);
		this.add(changeItemPrice);
		this.add(changeItemButton);
		
		// Create inputs for getting RCM info
		infoLabel = new JLabel("Get Info of Specific RCM: ");
		infoLabel.setPreferredSize(new Dimension(160, 30));
		RCMIdInput = new JTextField("ID");
		RCMIdInput.setPreferredSize(new Dimension(80, 30));
		getRCMInfoButton = new JButton("Get Info");
		getRCMInfoButton.setPreferredSize(new Dimension(130, 30));
		this.add(infoLabel);
		this.add(RCMIdInput);
		this.add(getRCMInfoButton);
		
		//RCM empty components
		emptyLabel = new JLabel("Empty Specific RCM: ");
		emptyLabel.setPreferredSize(new Dimension(150, 30));
		emptyId = new JTextField("ID");
		emptyId.setPreferredSize(new Dimension(80, 30));
		EmptyButton = new JButton("Empty");
		EmptyButton.setPreferredSize(new Dimension(130, 30));
		EmptyButton.addActionListener(this);
		this.add(emptyLabel);
		this.add(emptyId);
		this.add(EmptyButton);

		//RCM money refill components
		refillLabel = new JLabel("Refill a specific RCMs Funds: ");
		refillLabel.setPreferredSize(new Dimension(200, 30));
		RCMMoneyRefillId = new JTextField("ID");
		RCMMoneyRefillId.setPreferredSize(new Dimension(50, 30));
		moneyRefillButton = new JButton("Refill");
		moneyRefillButton.setPreferredSize(new Dimension(100, 30));
		moneyRefillButton.addActionListener(this);
		this.add(refillLabel);
		this.add(RCMMoneyRefillId);
		this.add(moneyRefillButton);
		
		//Last time an RCM was emptied components
		timeEmptyLabel =  new JLabel("Last time an RCM was emptied: ");
		timeEmptyLabel.setPreferredSize(new Dimension(200, 30));
		timeEmptyId = new JTextField("ID");
		timeEmptyId.setPreferredSize(new Dimension(50, 30));
		timeEmptyButton = new JButton("Get Time");
		timeEmptyButton.setPreferredSize(new Dimension(100, 30));
		timeEmptyButton.addActionListener(this);
		this.add(timeEmptyLabel);
		this.add(timeEmptyId);
		this.add(timeEmptyButton);
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
				Item newItem = new Item(newItemName.getText(), Double.parseDouble(newItemPrice.getText()), Math.random()*10);
				this.rcm1.addItem(newItem);
				this.rcm2.addItem(newItem);
			}
		}
		
		if (e.getSource() == this.changeItemButton){
			if(loggedIn == false){
				JOptionPane.showMessageDialog(null, "Please login before proceeding");
			}
			else{
				if(rcm1.getRow(changeItemName.getText()) == -1){
					JOptionPane.showMessageDialog(null, "This item does not exist");
				}
				else{
					rcm1.deleteItem(changeItemName.getText());
					rcm2.deleteItem(changeItemName.getText());
					Item changedItem = new Item(changeItemName.getText(), Double.parseDouble(changeItemPrice.getText()), Math.random()*10);
					rcm1.addItem(changedItem);
					rcm2.addItem(changedItem);

				}
			}
		}
		if (e.getSource() == this.getRCMInfoButton){
			if(loggedIn == false){
				JOptionPane.showMessageDialog(null, "Please login before proceeding");
			}
			else {
				
			}
		}
		if(e.getSource() == this.EmptyButton){
			if(loggedIn == false){
				JOptionPane.showMessageDialog(null, "Please login before proceeding");
			}
			else{
				String id = emptyId.getText();
				if(id.equals("1")){
					this.rcm1.setCurrentWeight(0.0);
					JOptionPane.showMessageDialog(null, "RCM " + id + " has been emptied");
					this.rcm1.setLastEmptyDate(new Date());
				}
				else if(id.equals("2")){
					this.rcm2.setCurrentWeight(0.0);
					JOptionPane.showMessageDialog(null, "RCM " + id + " has been emptied");
					this.rcm2.setLastEmptyDate(new Date());
				}
				emptyId.setText("ID");
			}
		}
		
		if(e.getSource() == this.moneyRefillButton){
			if(loggedIn == false){
				JOptionPane.showMessageDialog(null, "Please login before proceeding");
			}
			else{
				String id = RCMMoneyRefillId.getText();
				if(id.equals("1")){
					this.rcm1.setCurrentMoney(this.rcm1.getRefillAmt());
					JOptionPane.showMessageDialog(null, "RCM " + id + "'s money has been refilled to " + this.rcm1.getCurrentMoney());
				}
				else if(id.equals("2")){
					this.rcm2.setCurrentMoney(this.rcm2.getRefillAmt());
					JOptionPane.showMessageDialog(null, "RCM " + id + "'s money has been refilled to " + this.rcm2.getCurrentMoney());
				}
				RCMMoneyRefillId.setText("ID");
			}
		}
		
		if(e.getSource() == this.timeEmptyButton){
			if(loggedIn == false){
				JOptionPane.showMessageDialog(null, "Please login before proceeding");
			}
			else{
				String id = timeEmptyId.getText();
				if(id.equals("1")){
					if(this.rcm1.getLastEmptyDate() == null){
						JOptionPane.showMessageDialog(null, "This RCM has never been emptied");
					}
					else{
						JOptionPane.showMessageDialog(null, "RCM " + id + " last emptied: " + this.rcm1.getLastEmptyDate());
					}
				}
				else if(id.equals("2")){
					if(this.rcm2.getLastEmptyDate() == null){
						JOptionPane.showMessageDialog(null, "This RCM has never been emptied");
					}
					else{
						JOptionPane.showMessageDialog(null, "RCM " + id + " last emptied: " + this.rcm2.getLastEmptyDate());
					}
				}
				timeEmptyId.setText("ID");
			}
		}
	}
}
