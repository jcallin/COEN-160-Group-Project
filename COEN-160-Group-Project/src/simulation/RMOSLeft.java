package simulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
	private JTextField newItemAvgWeight;
	private JButton newItemButton;
	
	// Changing existing item components
	private JLabel changeItemLabel;
	//private JTextField  changeItemName
	private JComboBox changeItemSelector;
	private JTextField changeItemPrice;
	private JButton changeItemButton;
	
	// Get RCM info components
	private JLabel infoLabel;
	private JTextField infoIdInput;
	private JCheckBox pounds, kilograms;
	private JButton getRCMInfoButton;
	
	//RCMempty components
	private JLabel emptyLabel;
	private JTextField emptyId;
	private JButton EmptyButton;
	
	//RCMmoney refill components
	private JLabel refillLabel;
	private JTextField RCMMoneyRefillId;
	private JButton moneyRefillButton;
	
	//Last RCMEmpty components
	private JLabel timeEmptyLabel;
	private JTextField timeEmptyId;
	private JButton timeEmptyButton;
	
	//Get Info Labels for output
	private JLabel infoTitle, infoId, infoLocation, infoCurrentMoney, infoCurrentWeight, infoAvailableCapacity;
	
	//Get Most Used Machine Button
	private JButton mostUsedButton;
	
	public RMOSLeft(RCM r1, RCM r2){
		// Create box layout inside for each row of buttons
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setPreferredSize(new Dimension(450,430));
		
		rcm1 = r1;
		rcm2 = r2;
		
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
		
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
		newItemLabel.setPreferredSize(new Dimension(68, 30));
		newItemName = new JTextField("Name");
		newItemName.setPreferredSize(new Dimension(85, 30));
		newItemPrice = new JTextField("Price");
		newItemPrice.setPreferredSize(new Dimension(85, 30));
		newItemAvgWeight = new JTextField("Avg Weight");
		newItemAvgWeight.setPreferredSize(new Dimension(100, 30));
		newItemButton = new JButton("Add");
		newItemButton.setPreferredSize(new Dimension(70, 30));
		newItemButton.addActionListener(this);
		this.add(newItemLabel);
		this.add(newItemName);
		this.add(newItemPrice);
		this.add(newItemAvgWeight);
		this.add(newItemButton);
		
		//Create inputs for changing price of a current item
		changeItemLabel = new JLabel("Change Item: ");
		changeItemLabel.setPreferredSize(new Dimension(90, 30));
		//changeItemName = new JTextField("Name");
		//changeItemName.setPreferredSize(new Dimension(130, 30));
		//Create the combo box to select a material, select the first Item
		ArrayList<String> itemStrings = new ArrayList<String>();
		for(int i = 0; i < this.rcm1.getItemsSize(); i++){
			itemStrings.add(this.rcm1.getItem(i).getName());
		}
		changeItemSelector = new JComboBox(itemStrings.toArray());
		changeItemSelector.setSelectedIndex(0);
		changeItemSelector.addActionListener(this);
		changeItemSelector.setPreferredSize(new Dimension(150, 30));
		changeItemPrice = new JTextField("New Price");
		changeItemPrice.setPreferredSize(new Dimension(100, 30));
		changeItemButton = new JButton("Change");
		changeItemButton.setPreferredSize(new Dimension(75, 30));
		changeItemButton.addActionListener(this);
		this.add(changeItemLabel);
		this.add(changeItemSelector);
		//this.add(changeItemName);
		this.add(changeItemPrice);
		this.add(changeItemButton);
		
		// Create inputs for getting RCM info
		infoLabel = new JLabel("Get Info of RCM:");
		infoLabel.setPreferredSize(new Dimension(120, 30));
		infoIdInput = new JTextField("ID");
		infoIdInput.setPreferredSize(new Dimension(50, 30));
		pounds = new JCheckBox("lbs");
		pounds.setPreferredSize(new Dimension(75, 30));
		kilograms = new JCheckBox("kgs");
		kilograms.setPreferredSize(new Dimension(75, 30));
		getRCMInfoButton = new JButton("Get Info");
		getRCMInfoButton.setPreferredSize(new Dimension(80, 30));
		getRCMInfoButton.addActionListener(this);
		this.add(infoLabel);
		this.add(infoIdInput);
		this.add(pounds);
		this.add(kilograms);
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
		
		//Most used Components
		mostUsedButton = new JButton("Get Most Used RCM");
		mostUsedButton.setPreferredSize(new Dimension(300, 30));
		mostUsedButton.addActionListener(this);
		this.add(mostUsedButton);
		
		//Get info Components
		infoTitle = new JLabel("Info for RCM X", SwingConstants.CENTER);
		infoTitle.setPreferredSize(new Dimension(425, 30));
		infoTitle.setFont(new Font("Sans Serif", Font.ITALIC, 24));
		infoId = new JLabel("ID: ", SwingConstants.CENTER);
		infoId.setPreferredSize(new Dimension(40, 30));
		infoLocation = new JLabel("Location: ", SwingConstants.CENTER);
		infoLocation.setPreferredSize(new Dimension(150, 30));
		infoCurrentMoney = new JLabel("Current Money: ", SwingConstants.CENTER);
		infoCurrentMoney.setPreferredSize(new Dimension(170, 30));
		infoCurrentWeight = new JLabel("Current Weight: ", SwingConstants.CENTER);
		infoCurrentWeight.setPreferredSize(new Dimension(190, 30));
		infoAvailableCapacity = new JLabel("Available Capacity: ", SwingConstants.CENTER);
		infoAvailableCapacity.setPreferredSize(new Dimension(190, 30));
		this.add(infoTitle);
		this.add(infoId);
		this.add(infoLocation);
		this.add(infoCurrentMoney);
		this.add(infoCurrentWeight);
		this.add(infoAvailableCapacity);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Login functionality
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
		
		//New Item functionality
		if (e.getSource() == this.newItemButton){
			if(loggedIn == false){
				JOptionPane.showMessageDialog(null, "Please login before proceeding");
			}
			else{
				Item newItem = new Item(newItemName.getText(), Double.parseDouble(newItemPrice.getText()), Double.parseDouble(newItemAvgWeight.getText()));
				this.rcm1.addItem(newItem);
				this.rcm2.addItem(newItem);
				
				//Add new item to the change price selector
				this.changeItemSelector.addItem(newItem.getName());
				
				newItemName.setText("Name");
				newItemPrice.setText("Price");
				newItemAvgWeight.setText("Avg Weight");
			}
		}
		
		//Change Item functionality
		if (e.getSource() == this.changeItemButton){
			if(loggedIn == false){
				JOptionPane.showMessageDialog(null, "Please login before proceeding");
			}
			else{
				if((this.rcm1.getRow(changeItemSelector.getSelectedItem().toString()) == -1)){
					JOptionPane.showMessageDialog(null, "This item does not exist");
				}
				else{
					//Change price is display table
					this.rcm1.changePrice(Double.parseDouble(changeItemPrice.getText()), this.rcm1.getRow(changeItemSelector.getSelectedItem().toString()));
					this.rcm2.changePrice(Double.parseDouble(changeItemPrice.getText()), this.rcm2.getRow(changeItemSelector.getSelectedItem().toString()));
					
				}
			}
		}
		
		//Get info functionality
		if (e.getSource() == this.getRCMInfoButton){
			if(loggedIn == false){
				JOptionPane.showMessageDialog(null, "Please login before proceeding");
			}
			else{
				String id = infoIdInput.getText();
				if(id.equals("1")){
					infoTitle.setText("Info for RCM: " + this.rcm1.getId());
					infoId.setText("ID: " + this.rcm1.getId());
					infoLocation.setText("Location: " + this.rcm1.getLocation_t());
				
					//Use DecimalFormat class to truncate repeating doubles
					String cMoney = new DecimalFormat("#.##").format(this.rcm1.getCurrentMoney());
					infoCurrentMoney.setText("Current Money: $" + cMoney);
					
					//Check to see which units the user wants
					if(pounds.isSelected() && kilograms.isSelected()){
						JOptionPane.showMessageDialog(null, "Please select only one unit");
					}
					else if(pounds.isSelected()){
						String cWeight = new DecimalFormat("#.##").format(this.rcm1.getCurrentWeight());
						infoCurrentWeight.setText("Current Weight: " + cWeight + " lbs   ");
						String aCapacity = new DecimalFormat("#.##").format(this.rcm1.getMaxWeight()-this.rcm1.getCurrentWeight());
						infoAvailableCapacity.setText("Available Capacity: " + aCapacity + " lbs");
					}
					else if(kilograms.isSelected()){
						double kiloConversion = 0.45359237;
						String cWeight = new DecimalFormat("#.##").format(this.rcm1.getCurrentWeight()*kiloConversion);
						infoCurrentWeight.setText("Current Weight: " + cWeight  + " kgs   ");
						String aCapacity = new DecimalFormat("#.##").format((this.rcm1.getMaxWeight()-this.rcm1.getCurrentWeight())*kiloConversion);
						infoAvailableCapacity.setText("Available Capacity: " + aCapacity + " kgs");
					}
					
					
					//If currentMoney is less than $5.00 -- recommend refill
					if(this.rcm1.getCurrentMoney() < 5.00){
						JOptionPane.showMessageDialog(null, "RCM: " + this.rcm1.getId() + " money is getting low. Consider refilling.");
					}
					
					//If currentCapacity is less than 5.00 lbs -- recommend an empty
					if((this.rcm1.getMaxWeight() - this.rcm1.getCurrentWeight()) < 5.00){
						JOptionPane.showMessageDialog(null, "RCM: " + this.rcm1.getId() + " is getting full. Consider emptying.");
					}
				}
				else if(id.equals("2")){
					infoTitle.setText("Info for RCM: " + this.rcm2.getId());
					infoId.setText("ID: " + this.rcm2.getId());
					infoLocation.setText("Location: " + this.rcm2.getLocation_t());
					
					//Use DecimalFormat class to truncate repeating doubles
					String cMoney = new DecimalFormat("#.##").format(this.rcm2.getCurrentMoney());
					infoCurrentMoney.setText("Current Money: $" + cMoney);
					//Check to see which units the user wants
					if(pounds.isSelected() && kilograms.isSelected()){
						JOptionPane.showMessageDialog(null, "Please select only one unit");
					}
					else if(pounds.isSelected()){
						String cWeight = new DecimalFormat("#.##").format(this.rcm2.getCurrentWeight());
						infoCurrentWeight.setText("Current Weight: " + cWeight + " lbs   ");
						String aCapacity = new DecimalFormat("#.##").format(this.rcm2.getMaxWeight()-this.rcm1.getCurrentWeight());
						infoAvailableCapacity.setText("Available Capacity: " + aCapacity + " lbs");
					}
					else if(kilograms.isSelected()){
						double kiloConversion = 0.45359237;
						String cWeight = new DecimalFormat("#.##").format(this.rcm2.getCurrentWeight()*kiloConversion);
						infoCurrentWeight.setText("Current Weight: " + cWeight  + " kgs   ");
						String aCapacity = new DecimalFormat("#.##").format((this.rcm2.getMaxWeight()-this.rcm2.getCurrentWeight())*kiloConversion);
						infoAvailableCapacity.setText("Available Capacity: " + aCapacity + " kgs");
					}
					
					//If currentMoney is less than $5.00 -- recommend refill
					if(this.rcm2.getCurrentMoney() < 5.00){
						JOptionPane.showMessageDialog(null, "RCM: " + this.rcm2.getId() + " money is getting low. Consider refilling.");
					}
					
					//If currentCapacity is less than 5.00 lbs -- recommend an empty
					if((this.rcm2.getMaxWeight() - this.rcm1.getCurrentWeight()) < 5.00){
						JOptionPane.showMessageDialog(null, "RCM: " + this.rcm2.getId() + " is getting full. Consider emptying.");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "RCM " + id + " does not exist within this system");
				}
				infoIdInput.setText("ID");
			}
		}
		
		//Empty RCM functionality
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
					this.rcm1.changeStatusFontAfterEmpty();
				}
				else if(id.equals("2")){
					this.rcm2.setCurrentWeight(0.0);
					JOptionPane.showMessageDialog(null, "RCM " + id + " has been emptied");
					this.rcm2.setLastEmptyDate(new Date());
					this.rcm2.changeStatusFontAfterEmpty();

				}
				else{
					JOptionPane.showMessageDialog(null, "RCM " + id + " does not exist within this system");
				}
				emptyId.setText("ID");
			}
		}
		
		//Refill Money for an RCM functionality
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
				else{
					JOptionPane.showMessageDialog(null, "RCM " + id + " does not exist within this system");
				}
				RCMMoneyRefillId.setText("ID");
			}
		}
		
		//Last time an RCM was emptied functionality
		if(e.getSource() == this.timeEmptyButton){
			if(loggedIn == false){
				JOptionPane.showMessageDialog(null, "Please login before proceeding");
			}
			else{
				String id = timeEmptyId.getText();
				if(id.equals("1")){
					if(this.rcm1.getLastEmptyDate() == null){
						JOptionPane.showMessageDialog(null, "RCM " + id + " has never been emptied");
					}
					else{
						JOptionPane.showMessageDialog(null, "RCM " + id + " last emptied: " + this.rcm1.getLastEmptyDate());
					}
				}
				else if(id.equals("2")){
					if(this.rcm2.getLastEmptyDate() == null){
						JOptionPane.showMessageDialog(null, "RCM " + id + "has never been emptied");
					}
					else{
						JOptionPane.showMessageDialog(null, "RCM " + id + " last emptied: " + this.rcm2.getLastEmptyDate());
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "RCM " + id + " does not exist within this system");
				}
				timeEmptyId.setText("ID");
			}
		}
		
		//Get Most Used RCM functionality
		if(e.getSource() == this.mostUsedButton){
			if(loggedIn == false){
				JOptionPane.showMessageDialog(null, "Please login before proceeding");
			}
			else{
				if(this.rcm1.getTotalWeightProcessed() > this.rcm2.getTotalWeightProcessed()){
					String tmp = new DecimalFormat("#.##").format(this.rcm1.getTotalWeightProcessed());
					JOptionPane.showMessageDialog(null, "RCM " + this.rcm1.getId() + " has been used the most. Total weight processed: " + tmp + " lbs");
				}
				else if(this.rcm1.getTotalWeightProcessed() < this.rcm2.getTotalWeightProcessed()){
					String tmp = new DecimalFormat("#.##").format(this.rcm2.getTotalWeightProcessed());
					JOptionPane.showMessageDialog(null, "RCM " + this.rcm2.getId() + " has been used the most. Total weight processed: " + tmp + " lbs");
				}
				else{
					JOptionPane.showMessageDialog(null, "RCMs have processed equal weights");
				}
			}
		}
	}
}
