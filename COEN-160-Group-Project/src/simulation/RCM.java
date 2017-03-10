package simulation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class RCM extends JPanel implements ActionListener{
	private String stringId, stringLocation, currentSession;
	protected ArrayList<Item> items;
	private Map<Item, Double> dict = new HashMap<Item, Double>();
	private JLabel location, id, fullBox;
	private JComboBox<String> itemSelector;
	private int itemWeight, currentMoneyOwed;
	private JLabel itemWeightLabel;
	private JButton add, sessionToggle;
	private boolean inSession;
	
	public RCM(String _id, String _location){
		super(new FlowLayout(FlowLayout.LEFT));
		
		// RCM specific
		stringId = _id;
		stringLocation = _location;
		
		// Session and recycling variables
		itemWeight = 0;
		currentMoneyOwed = 0;
		inSession = false;
		
		// This is a buffer for each transaction. When a session is ended, this gets flushed to a file
		currentSession = new String();
		setPreferredSize(new Dimension(600,450));
		
		// Default items accepted by the RCM
		items = new ArrayList<Item>();
		items.add(new Item("Aluminum", 3.0, 6));
		items.add(new Item("Copper", 3.0, 8));
		items.add(new Item("Plastic", 5.5, 3));
		
		// Labels to display the ID, location, and full status of the RCM
		id = new JLabel(_id);
		id.setPreferredSize(new Dimension(150, 100));
		this.add(id);
		
		location = new JLabel(stringLocation);
		location.setPreferredSize(new Dimension(150, 100));
		this.add(location);
		
		fullBox = new JLabel("Full");
		fullBox.setPreferredSize(new Dimension(150, 100));
		this.add(fullBox);
		
		// Create a container to hold the ScrollPane (which holds the table)
		JPanel tableContainer = new JPanel();
		// Create the table to display the accepted Items
		JTable table = new JTable() {
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
	    table.setModel(new DefaultTableModel(list.toArray(new Object[][] {}), new String[] {"Item", "Price"}));
		// Set scrolling to only be vertical
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		table.setFillsViewportHeight(true);
		tableContainer.setLayout(new BorderLayout());
		tableContainer.add(table.getTableHeader(), BorderLayout.PAGE_START);
		tableContainer.add(scrollPane, BorderLayout.CENTER);
		tableContainer.setPreferredSize(new Dimension(590, 100));
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
	
	public void addItem(Item i){
		items.add(i);
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
				// Get the name of the item to add from the combo box
				String itemName = (String)this.itemSelector.getSelectedItem();
				
				// Get the weight (random number scaled by the average weight 1-10)
				double averageWeight = 0;
				double currentPrice = 0;
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
				
				// Use a static month for test
				String month = "January";
				// Use the minute to make times unique
				// TODO: Decide on how to represent time
				int currentMin = calendar.get(Calendar.MINUTE); // gets hour in 24h format
				
				// Append to the transaction string
				this.currentSession += month + " "+ currentMin + " " + itemName + " " + weight + "\n";
				
				// Apply the amount added to the user's money owed
				this.currentMoneyOwed += weight * currentPrice;
				
				// Show the current transaction status for testing
				System.out.println(this.currentSession);
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
				// Show the user how much money they have
				JOptionPane.showMessageDialog(null, "You've received $" + this.currentMoneyOwed + " dollars");
				this.currentMoneyOwed = 0;
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
		String fileName = "RCM" + this.stringId + ".txt";
		try{
			PrintWriter writer = new PrintWriter(fileName, "UTF-8");
			writer.write(session);
			writer.close();
		} catch (IOException e) {
		    System.out.println("Error writing to session file " + fileName);
		}
	}
}