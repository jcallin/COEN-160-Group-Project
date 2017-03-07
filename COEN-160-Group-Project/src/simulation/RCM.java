package simulation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class RCM extends JPanel implements ActionListener{
	private String stringId, stringLocation;
	ArrayList<Item> items;
	Map<Item, Double> dict = new HashMap();
	JLabel location, id, fullBox;
	JComboBox itemSelector;
	private int itemWeight;
	JLabel itemWeightLabel;
	JButton add, sessionToggle;
	
	public RCM(String _id, String _location){
		super(new FlowLayout(FlowLayout.LEFT));
		stringId = _id;
		stringLocation = _location;
		itemWeight = 0;
		setPreferredSize(new Dimension(600,450));
		
		items = new ArrayList<Item>();
		items.add(new Item("Aluminum", 3.0));
		items.add(new Item("Copper", 3.0));
		items.add(new Item("Plastic", 5.5));
		
		id = new JLabel(_id);
		id.setPreferredSize(new Dimension(150, 100));
		this.add(id);
		
		location = new JLabel(stringLocation);
		location.setPreferredSize(new Dimension(150, 100));
		this.add(location);
		
		fullBox = new JLabel("Full");
		fullBox.setPreferredSize(new Dimension(150, 100));
		this.add(fullBox);
		
		// Create the table to display the accepted Items
		JPanel tableContainer = new JPanel();
		JTable table = new JTable();
		
		ArrayList<Object[]> list = new ArrayList<Object[]>();
	    for (int i = 0; i < items.size(); i++) {
	        list.add(new Object[] { 
	                                  items.get(i).name, 
	                                  items.get(i).value
	                              });

	    }
	    table.setModel(new DefaultTableModel(list.toArray(new Object[][] {}), 
	                        new String[] {"Item", "Price"}));
		
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		tableContainer.setLayout(new BorderLayout());
		tableContainer.add(table.getTableHeader(), BorderLayout.PAGE_START);
		tableContainer.add(table, BorderLayout.CENTER);
		tableContainer.setPreferredSize(new Dimension(590, 50));
		this.add(tableContainer);

		//Create the combo box, select item at index 0.
		ArrayList<String> itemStrings = new ArrayList<String>();
		for(int i = 0; i < items.size(); i++){
			itemStrings.add(items.get(i).name);
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
		
		add = new JButton("Add");
		add.setPreferredSize(new Dimension(100, 30));
		this.add(add);
		
		sessionToggle = new JButton("Start Session");
		sessionToggle.setPreferredSize(new Dimension(180, 30));
		this.add(sessionToggle);
		

	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.add){
			// Add an Item
		}
		else if (e.getSource() == this.sessionToggle){
			// Start/stop a session
		}
	}
}