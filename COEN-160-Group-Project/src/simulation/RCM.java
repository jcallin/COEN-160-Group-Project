package simulation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class RCM extends JPanel implements ActionListener {
	private String id, location, currentSession;
	private double currentWeight, currentMoney, maxWeight, refillAmt, totalWeightProcessed;
	private ArrayList<Item> items;
	private JLabel locationLabel, idLabel, fullBox, title;
	private JComboBox<String> itemSelector;
	private double itemWeight, currentMoneyOwed, couponTotal;
	private JLabel itemWeightLabel;
	private JButton add, sessionToggle;
	private boolean inSession;
	private JTable table;
	private Date lastEmptyDate;
	
	public RCM(String _id, String _location, double capacity, double totalFunds, ArrayList<Item> itemsAccepted){
		super(new FlowLayout(FlowLayout.LEFT));
		
		// RCM specific
		id = _id;
		location = _location;
		currentWeight = 0.0;
		maxWeight = capacity;
		currentMoney = totalFunds;
		refillAmt = totalFunds;
		items = itemsAccepted;
		lastEmptyDate = null;
		totalWeightProcessed = 0;
		couponTotal = 0;
		
		this.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
		
		// Session and recycling variables
		itemWeight = 0;
		currentMoneyOwed = 0;
		inSession = false;
		
		// This is a buffer for each transaction. When a session is ended, this gets flushed to a file
		currentSession = new String();
		setPreferredSize(new Dimension(615,240));
		
		//Title of RCM
		title = new JLabel("RCM: " + this.id, SwingConstants.CENTER);
		title.setFont(new Font("Sans Serif", Font.BOLD, 18));
		title.setPreferredSize(new Dimension(615, 30));
		this.add(title);
		
		Font f = new Font("San Serif", Font.PLAIN, 14);
		
		// Labels to display the ID, location, and full status of the RCM
		idLabel = new JLabel("ID: " + this.id, SwingConstants.CENTER);
		idLabel.setFont(f);
		idLabel.setPreferredSize(new Dimension(180, 35));
		this.add(idLabel);
		
		locationLabel = new JLabel("Location: " + this.location, SwingConstants.CENTER);
		locationLabel.setFont(f);
		locationLabel.setPreferredSize(new Dimension(180, 35));
		this.add(locationLabel);
		
		fullBox = new JLabel("OPEN", SwingConstants.CENTER);
		fullBox.setFont(f);
		fullBox.setForeground(Color.GREEN);
		fullBox.setPreferredSize(new Dimension(180, 35));
		this.add(fullBox);
		
		// Create a container to hold the ScrollPane (which holds the table)
		JPanel tableContainer = new JPanel();
		// Create the table to display the accepted Items
		this.table = new JTable()  {
		    public boolean isCellEditable(int row, int column)
		    {
		      return false; //This causes all cells to be not editable
		    }
		  }; 
		  
		// Create a list from the ArrayList of Items so we can use the list to populate the table
		ArrayList<Object[]> list = new ArrayList<Object[]>();
	    for (int i = 0; i < items.size(); i++) {
	        list.add(new Object[] { 
	                                  items.get(i).getName(), 
	                                  items.get(i).getValue()
	                              });

	    }
	    
	    // Specify the data to use for the columns and the strings for the column names
	    table.setModel(new DefaultTableModel(list.toArray(new Object[][] {}), new String[] {"Item", "Price/lb"}));
		
	    // Set scrolling to only be vertical
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		table.setFillsViewportHeight(true);
		table.setFocusable(false);
		table.setRowSelectionAllowed(false);
		tableContainer.setLayout(new BorderLayout());
		tableContainer.add(table.getTableHeader(), BorderLayout.PAGE_START);
		tableContainer.add(scrollPane, BorderLayout.CENTER);
		tableContainer.setPreferredSize(new Dimension(590, 80));
		this.add(tableContainer);

		//Create the combo box to select a material, select the first Item
		ArrayList<String> itemStrings = new ArrayList<String>();
		for(int i = 0; i < items.size(); i++){
			itemStrings.add(items.get(i).getName());
		}
		itemSelector = new JComboBox(itemStrings.toArray());
		itemSelector.setSelectedIndex(0);
		itemSelector.addActionListener(this);
		itemSelector.setPreferredSize(new Dimension(200, 30));
		this.add(itemSelector);
		
		// Create the label for weight, change content (randomized) on end session press
		itemWeightLabel = new JLabel("Weight: " + String.valueOf(itemWeight));
		itemWeightLabel.setPreferredSize(new Dimension(100, 30));
		this.add(itemWeightLabel);
		
		// Create a button for adding a weight of a material to be recycled
		add = new JButton("Add");
		add.setPreferredSize(new Dimension(100, 30));
		add.addActionListener(this);
		this.add(add);
		
		// Create the toggle button for a session
		sessionToggle = new JButton("Start Session");
		sessionToggle.setPreferredSize(new Dimension(180, 30));
		sessionToggle.addActionListener(this);
		this.add(sessionToggle);
	}
	
	//Setters & Getters for use by RMOS
	public double getCurrentWeight() {
		return currentWeight;
	}

	public void setCurrentWeight(double currentWeight) {
		this.currentWeight = currentWeight;
	}

	public double getCurrentMoney() {
		return currentMoney;
	}

	public void setCurrentMoney(double currentMoney) {
		this.currentMoney = currentMoney;
	}

	public double getMaxWeight() {
		return maxWeight;
	}

	public double getRefillAmt() {
		return refillAmt;
	}
	
	public Date getLastEmptyDate(){
		return lastEmptyDate;
	}
	
	public void setLastEmptyDate(Date d){
		this.lastEmptyDate = d;
	}
	
	public String getId() {
		return id;
	}

	public String getLocation_t() {
		return location;
	}
	
	public double getTotalWeightProcessed(){
		return this.totalWeightProcessed;
	}
	
	public void addToTotalWeightProccesed(double weight){
		this.totalWeightProcessed += weight;
	}
	
	public Item getItem(int index){
		return this.items.get(index);
	}
	
	public int getItemsSize(){
		return items.size();
	}

	//Method to add an item -- changes: item, item selector, table
	public void addItem(Item i){
		items.add(i);
		itemSelector.addItem(i.getName());
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(new Object[]{i.getName(), i.getValue()});
	}

	//Method to get the row in the display table of a specific item (used in changePrice)
	public int getRow(String name){
		int row = -1;
		for(int i = 0; i < this.items.size(); i++){
			if(name == items.get(i).getName()){
				row = i;
				break;
			}
		}
		return row;
	}
	
	//Change price method
	public void changePrice(double price, int row){
		//Change price of item in items array
		this.items.get(row).setValue(price);
		
		//Change price in UI table
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setValueAt(price, row, 1);
	}
	
	public void changeStatusFontAfterEmpty(){
		this.fullBox.setForeground(Color.GREEN);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Set up the datetime stuff
		Date date = new Date();   // given date
		Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
		calendar.setTime(date);   // assigns calendar to given date 
		
		// TODO Auto-generated method stub
		if (e.getSource() == this.add){
			if(this.inSession){
				if(this.currentWeight >= this.maxWeight){
					JOptionPane.showMessageDialog(null, "RCM is at capacity. Please contact Admin to empty");
					fullBox.setForeground(Color.red);
					fullBox.setText("FULL");
				}
				else{
				
					// Get the name of the item to add from the combo box
					String itemName = (String)this.itemSelector.getSelectedItem();
				
					// Get the weight (random number scaled by the average weight 1-10)
					double averageWeight = 0.0;
					double currentPrice = 0.0;
					for(int i = 0; i < items.size(); i++){
							if(items.get(i).getName() == itemName){
								averageWeight = items.get(i).getAverageWeight();
								currentPrice = items.get(i).getValue();
								break;
						}
					}
				
					// Use a random number 0-1 and multiply by average weight to get a weight for the added Item
					double weight = Math.random() * averageWeight;
					
					// Round to 2 decimal places
					weight = (double)Math.round(weight * 100d) / 100d;
				
					//Check to see if this item can fit
					if((currentWeight + weight) >= this.getMaxWeight()){
						JOptionPane.showMessageDialog(null, "Insufficient capacity for this item. Please try another");
					}
					else{
						this.setCurrentWeight(currentWeight + weight);
						this.addToTotalWeightProccesed(weight);
				
				
						//Check to see if we have enough money to dispense
						if((currentMoney - (weight*currentPrice)) <= 0){
							//Apply amount to Coupon total
							couponTotal += weight*currentPrice;
						}
						else{
							this.setCurrentMoney(currentMoney - (weight*currentPrice));
							
							// Apply the amount added to the user's money owed
							this.currentMoneyOwed += weight * currentPrice;
						}
						
							// Use a static month for test
							String month = "January";
				
							// Use the minute to make times unique
							// TODO: Decide on how to represent time
							int currentMin = calendar.get(Calendar.MINUTE); // gets hour in 24h format
				
							// Append to the transaction string
							this.currentSession += month + " "+ currentMin + " " + itemName + " " + weight + "\n";
						
							//Output what they deposited and their current total (truncate)
							String tTot = new DecimalFormat("#.##").format(weight*currentPrice);
							String tCurrentWeight = new DecimalFormat("#.##").format(weight);
							String tCurrMoneyOwed = new DecimalFormat("#.##").format(this.currentMoneyOwed+this.couponTotal);
							JOptionPane.showMessageDialog(null, "You deposited " + tCurrentWeight + " lbs of " + itemName + " for $" + tTot + "\n Total for this Session: $" + tCurrMoneyOwed);
						
							// Show the current transaction status for testing
							System.out.println(this.currentSession);
						}
					}
				}
				
				else{
						JOptionPane.showMessageDialog(null, "Please start a session before adding items");
				}
			}
		
		else if (e.getSource() == this.sessionToggle){
			// Start/stop a session
			if(!this.inSession){
				this.inSession = true;
				this.sessionToggle.setText("Stop Session");
			}
			else{
				this.inSession = false;
				this.sessionToggle.setBackground(null);
				// Show the user how much money/coupon they have (truncate)
				if(this.couponTotal == 0){
					JOptionPane.showMessageDialog(null, "You've received $" + new DecimalFormat("#.##").format(this.currentMoneyOwed) + "! Thank for being green!");
				}
				else{
					JOptionPane.showMessageDialog(null, "You've received $" + new DecimalFormat("#.##").format(this.currentMoneyOwed) + " and $" + new DecimalFormat("#.##").format(this.couponTotal) + " worth of Coupons! Thanks for being green!");
				}
				this.currentMoneyOwed = 0;
				this.couponTotal = 0;
				// Write the transactions list to a file
				writeSessionToBuffer(this.currentSession);
				
				//Switch button text back to Start Session once a session is completed
				this.sessionToggle.setText("Start Session");
			}
		}
	}
	
	/**
	 * Writes a session to a file for persistence
	 * @param session	String: the multi-line current session
	 */
	private void writeSessionToBuffer(String session){
		String fileName = "RCM" + this.id + ".txt";
		try{
			PrintWriter writer = new PrintWriter(fileName, "UTF-8");
			writer.write(session);
			writer.close();
		} catch (IOException e) {
		    System.out.println("Error writing to session file " + fileName);
		}
	}
}