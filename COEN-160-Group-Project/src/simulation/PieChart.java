package simulation;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.TreeMap;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PieChart extends JPanel implements Observer {
	private RCM rcm;
	private Map<String, Double> itemTotals;
	private File fileName;
	
	public PieChart(RCM _rcm){
		rcm = _rcm;
		fileName = new File("RCM" + this.rcm.getId()+ ".txt"); 
		itemTotals = new HashMap<String, Double>();
	}
	
	public void loadFileIntoMap() throws IOException{
		Scanner freader = new Scanner(fileName);

		String line = "";
		double tmpWeight = 0;
		double lineWeight = 0;
		
		while (freader.hasNextLine()) {
			line = freader.nextLine();
			String []tokenArray = line.split("\\s");
			System.out.println(tokenArray[2] + " --- " + tokenArray[3]);
			lineWeight = Double.parseDouble(tokenArray[3]);
			
			if(this.itemTotals.containsKey(tokenArray[2])){ //Map already contains the item, edit current Key->Value pair
				tmpWeight = itemTotals.get(tokenArray[2]); //This can be throw NullPointer -- keep eye out
				itemTotals.put(tokenArray[2], lineWeight + tmpWeight);
			}
			else{ //Map does not contain the item, create new Key->Value pair
				itemTotals.put(tokenArray[2], lineWeight);
			}
			
		}

	    freader.close();  // Close to unlock.
	}
	
	public void toStrings(){
		for (String name: this.itemTotals.keySet()){

            String key = name;
            String value = itemTotals.get(name).toString();  
            System.out.println(key + " " + value); 
		}
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		//this.paintComponent(g, arg); //Pass in the file name to paint component
	}
	
	public void paintComponent(Graphics g, File txt){
		super.paintComponent(g);
		drawPieChart(g);
	    drawLegend(g);
	}
	
	private void drawPieChart(Graphics g){
		//double totalWeight = getTotalWeight();
		double percentage = 0.0;
		double startAngle = 0.0;
		double arcAngle = 0.0;
		
		//for(int i = 0; i < this.rcm.dict.size(); i++){
		//	
		//}
		
	}
	
	private void drawLegend(Graphics g){
		
	}
	
	public static void main(String[] args) throws IOException {
		ArrayList<Item> items = new ArrayList<Item>();
		items.add(new Item("Aluminum", 0.75, 6));
		items.add(new Item("Copper", 1.10, 8));
		
		RCM RCM1 = new RCM("1", "Swig", 100.00, 100.00, items);
		PieChart p = new PieChart(RCM1);
		
		p.loadFileIntoMap();
		p.toStrings();
	}
}
